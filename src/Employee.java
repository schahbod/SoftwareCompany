import java.util.List;
import java.util.ArrayList;

public class Employee {

    private String name;
    private int ID;
    private Role role;
    private double salary;
    private AccessLevel accessLevel;
    private  int hourspermonth = 160;

    /// to do
    public static List<Employee> employees = new ArrayList<>();




    public Employee(String name, int ID, Role role, double salary, AccessLevel accessLevel) {
        this.name = name;
        this.ID = ID;
        this.role = role;
        this.salary = salary;
        this.accessLevel = accessLevel;
    }

    public int getHourspermonth () {
        return hourspermonth;
    }

    public static void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public void work() {
        System.out.println(name + " is working as a " + role.toString());
    }

    public boolean hasAccessTo(String resource) {
        return accessLevel.hasAccessTo(resource, role, false);
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

    public void setHendynummer(int hendynummer) {
        this.ID = hendynummer;
    }

    public int getHendynummer() {
        return this.ID;
    }

    public void showTimeZoneDetails() {
        System.out.println(name + " is located in a timezone with a specific offset.");
    }

    public enum Role {
        DEVELOPER, DESIGNER, MANAGER, TESTER, HR
    }

    public enum AccessLevel {
        Admin, Manager, Developer, Employee, HR_MANAGER;

        public boolean hasAccessTo(String resource, Role role, boolean isAsiaBranch) {
            if (resource.equals("AdminPanel") && this == Admin) {
                return true;
            } else if (resource.equals("CodeBase") && (this == Developer || this == Admin)) {
                return true;
            } else if (resource.equals("Designs") && (role == Role.DESIGNER || this == Admin)) {
                return true;
            } else if (isAsiaBranch && resource.equals("AsiaPanel")) {
                return this == Admin || this == Manager;
            }
            return false;
        }
    }
}
