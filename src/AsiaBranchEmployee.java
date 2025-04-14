import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AsiaBranchEmployee {

    private int ID;
    private String name;
    private Employee.Role role;
    private double salary;
    private String timeZone = "Asia/Calcutta";

    public AsiaBranchEmployee(int ID, String name, Employee.Role role, double salary) {
        this.ID = ID;
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    public void work() {

        System.out.println(name + " is working as a " + role.toString() + " in the Asia branch.");
    }

    public void showTimeZoneDetails() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(timeZone));
        System.out.println("Employee " + name + "'s current time in " + timeZone + ": " + now);
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Employee.Role getRole() {
        return role;
    }

    public double getSalary() {
        return salary;
    }

    public String getTimeZone() {
        return timeZone;
    }
}
