import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AsiaBranchEmployee extends Employee {
    private String timeZone = "Asia/Calcutta";

    public AsiaBranchEmployee(int ID, String name, Employee.Role role, double salary, Employee.AccessLevel accessLevel) {
        super(name, ID, role, salary, accessLevel);
    }

    @Override
    public void work() {
        System.out.println(getName() + " is working as a " + getRole().toString() + " in the Asia branch.");
    }

    @Override
    public void showTimeZoneDetails() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(timeZone));
        System.out.println("Employee " + getName() + "'s current time in " + timeZone + ": " + now);
    }

    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public boolean hasAccessTo(String resource) {
        return getAccessLevel().hasAccessTo(resource);
    }
}
