import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;

public class Employee extends Person {
    private Role role;
    private double salary;
    private int hendynummer;
    private static LinkedList<Employee> employeeLinkedList = new LinkedList<>();

    public enum Role {
        DEVELOPER, DESIGNER, MANAGER, TESTER
    }

    public Employee(String name, int id, Role role, double salary) {
        super(name, id);
        this.role = role;
        this.salary = salary;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getHendynummer() {
        return hendynummer;
    }

    public void setHendynummer(int hendynummer) {
        this.hendynummer = hendynummer;
    }

    public void showTimeZoneDetails() {
        ZoneId zoneId = ZoneId.of("Europe/Berlin"); // Example for EU
        ZonedDateTime currentTime = ZonedDateTime.now(zoneId);
        System.out.println("Time Zone: " + zoneId);
        System.out.println("Current UTC Offset: " + currentTime.getOffset());
    }

    @Override
    public String toString() {
        return getName();
    }

    public void work() {
        System.out.println(getName() + " is working as a " + role);
    }

    public static void addEmployee(Employee emp) {
        employeeLinkedList.add(emp);
    }

    public static void removeEmployee(Employee emp) {
        employeeLinkedList.remove(emp);
    }

    public void displayAllEmployee() {
        for (Employee emp : employeeLinkedList) {
            System.out.println("Employee name: " + emp.getName() + ", Role: " + emp.getRole());
        }
    }

    public static class EmployeeDetails {
        public void displayDetails(Employee emp) {
            System.out.println("Employee Name: " + emp.getName());
            System.out.println("Employee ID: " + emp.getId());
            System.out.println("Employee Role: " + emp.role);
            System.out.println("Employee Salary: " + emp.salary);
        }
    }
}
