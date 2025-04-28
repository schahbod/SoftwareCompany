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

    public static List<Employee> employees = new ArrayList<>();

    public Employee(String name, int ID, Role role, double salary, AccessLevel accessLevel) {
        this.name = name;
        this.ID = ID;
        this.role = role;
        this.salary = salary;
        this.accessLevel = accessLevel;
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