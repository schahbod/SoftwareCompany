import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeParseException;

public class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private int workday;

    public PartTimeEmployee(String name, int id, Role role, double salary, int hoursWorked, int workday, AccessLevel accessLevel) {
        super(name, id, role, salary, accessLevel);
        this.hoursWorked = hoursWorked;
        this.workday = workday;
    }

    @Override
    public void assignWorkSchedule(String[] startInputs, String[] endInputs) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        double totalHours = 0.0;
        for (int i = 0; i < 5; i++) {
            if (startInputs[i].isEmpty() || endInputs[i].isEmpty()) {
                continue; // Skip days with empty inputs
            }
            try {
                LocalTime startTime = LocalTime.parse(startInputs[i]);
                LocalTime endTime = LocalTime.parse(endInputs[i]);
                if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
                    throw new IllegalArgumentException("End time must be after start time for " + days[i]);
                }
                Duration duration = Duration.between(startTime, endTime);
                double hours = duration.toHours() + (duration.toMinutes() % 60) / 60.0;
                totalHours += hours;
                System.out.println("PartTimeEmployee: " + days[i] + " hours = " + hours + ", Total so far = " + totalHours);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid time format for " + days[i] + ". Use HH:mm (e.g., 09:00).");
            }
        }
        if (totalHours > 20.0) {
            throw new IllegalArgumentException("Part-time employee cannot work more than 20 hours per week. Total: " + totalHours);
        }
        if (totalHours == 0.0) {
            throw new IllegalArgumentException("No valid hours entered. Please set at least one day's schedule.");
        }
        super.assignWorkSchedule(startInputs, endInputs);
    }

    @Override
    public void work() {
        System.out.println(getName() + " is a part-time employee working " + hoursWorked + " hours per week.");
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public int getWorkday() {
        return workday;
    }

    @Override
    public String getGuiInfo() {
        return super.getGuiInfo() + "\nHours worked" + getHoursWorked() + "\nWorkdays " + getWorkday();
    }
}