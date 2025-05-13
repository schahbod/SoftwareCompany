import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Employee {

    private String name;
    private int ID;
    private Role role;
    private double salary;
    private AccessLevel accessLevel;
    private int hourspermonth = 160;
    private List<AufgabeTicket> tickets = new ArrayList<>();
    private int hendynummer;
    private WorkSchedule workSchedule;

    public static List<Employee> employees = new ArrayList<>();

    public Employee(String name, int ID, Role role, double salary, AccessLevel accessLevel) {
        this.name = name;
        this.ID = ID;
        this.role = role;
        this.salary = salary;
        this.accessLevel = accessLevel;
        this.workSchedule = new WorkSchedule();
    }

    public void assignWorkSchedule(String[] startInputs, String[] endInputs) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for (int i = 0; i < 5; i++) {
            if (startInputs[i].isEmpty() || endInputs[i].isEmpty()) {
                workSchedule.setWorkHours(i, null, null); // Clear unset days
                continue;
            }
            try {
                LocalTime startTime = LocalTime.parse(startInputs[i]);
                LocalTime endTime = LocalTime.parse(endInputs[i]);
                if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
                    throw new IllegalArgumentException("End time must be after start time for " + days[i]);
                }
                workSchedule.setWorkHours(i, startTime, endTime);
            } catch (DateTimeParseException e) {
                workSchedule.setWorkHours(i, LocalTime.of(9, 0), LocalTime.of(17, 0));
            }
        }
    }

    public double calculateTotalWorkHours() {
        double totalHours = 0.0;
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for (int i = 0; i < 5; i++) {
            LocalTime start = workSchedule.getStartTime(i);
            LocalTime end = workSchedule.getEndTime(i);
            if (start != null && end != null) {
                Duration duration = Duration.between(start, end);
                double hours = duration.toHours() + (duration.toMinutes() % 60) / 60.0;
                totalHours += hours;
                System.out.println("Employee: " + days[i] + " hours = " + hours + ", Total so far = " + totalHours);
            }
        }
        return totalHours;
    }

    public WorkSchedule getWorkSchedule() {
        return workSchedule;
    }

    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addTicket(AufgabeTicket ticket) {
        tickets.add(ticket);
    }

    public List<AufgabeTicket> getTickets() {
        return tickets;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public Role getRole() {
        return role;
    }

    public double getSalary() {
        return salary;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void work() {
        System.out.println(name + " is working on tasks.");
    }

    public void setHendynummer(int hendynummer) {
        this.hendynummer = hendynummer;
    }

    public int getHendynummer() {
        return hendynummer;
    }

    public void showTimeZoneDetails() {
        System.out.println(name + " is in the default time zone.");
    }

    public boolean hasAccessTo(String resource) {
        return accessLevel.hasAccessTo(resource);
    }

    public String getGuiInfo() {
        return "Name " + getName() + "\nID" + getID() + "\nRole" + getRole() +
                "\nSalary" + getSalary() + "\nAccess Level " + getAccessLevel();
    }

    public enum Role {
        DEVELOPER, DESIGNER, MANAGER, TESTER, HR
    }

    public enum AccessLevel {
        Admin, Manager, Developer, Employee, HR_MANAGER;

        public boolean hasAccessTo(String resource) {
            if (resource.equals("AdminPanel") && this == Admin) {
                return true;
            } else if (resource.equals("CodeBase") && (this == Developer || this == Admin)) {
                return true;
            } else if (resource.equals("Designs") && (this == Manager || this == Admin)) {
                return true;
            }
            return false;
        }
    }
}