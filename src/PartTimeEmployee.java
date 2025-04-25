public class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private int workday;

    public PartTimeEmployee(String name, int id, Role role, double salary, int hoursWorked, int workday, AccessLevel accessLevel) {
        super(name, id, role, salary, accessLevel);
        this.hoursWorked = hoursWorked;
        this.workday = workday;
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

}
