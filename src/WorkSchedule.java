import java.time.LocalTime;

public class WorkSchedule {
    private LocalTime[] starttimes;
    private LocalTime[] endtimes;

    public WorkSchedule() {
        this.starttimes = new LocalTime[5];
        this.endtimes = new LocalTime[5];
    }

    public void setWorkHours(int day, LocalTime startTime, LocalTime endTime) {
        if (day >= 0 && day < 5) {
            starttimes[day] = startTime;
            endtimes[day] = endTime;
        }
    }

    public void setStartTime(int day, LocalTime startTime) {
        if (day >= 0 && day < 5) {
            starttimes[day] = startTime;
        }
    }

    public void setEndTime(int day, LocalTime endTime) {
        if (day >= 0 && day < 5) {
            endtimes[day] = endTime;
        }
    }

    public LocalTime getStartTime(int day) {
        return starttimes[day];
    }

    public LocalTime getEndTime(int day) {
        return endtimes[day];
    }

    public void displaySchedule(String employeeName) {
        System.out.println(employeeName + "'s Work Schedule:");
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for (int i = 0; i < 5; i++) {
            String start = (starttimes[i] != null) ? starttimes[i].toString() : "Not Set";
            String end = (endtimes[i] != null) ? endtimes[i].toString() : "Not Set";
            System.out.println(days[i] + ": " + start + " to " + end);
        }
    }
}
