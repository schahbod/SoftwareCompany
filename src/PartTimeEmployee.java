public class PartTimeEmployee extends  Employee {
    private int hoursWorked;



    public PartTimeEmployee (String name, int id, Role role, double salary, int hoursWorked) {
        super (name, id, role, salary);
        this.hoursWorked = hoursWorked;

    }

    @Override
    public void work() {
        System.out.println(getName() + "is a part-time employee working" + hoursWorked + "hours per week");
    }
public int getHoursWorked (int hoursWorked) {
        return hoursWorked;
}

public void setHoursWorked (int hoursWorked) {
        this.hoursWorked = hoursWorked;
}

}
