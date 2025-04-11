import javax.management.relation.Role;

public class Employee extends  Person {
    private Role role;
    private double salary;
    private int hendynummer;



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

    public int  gethendynummer () {
        return hendynummer;
    }

    public void setHendynummer (int hendynummer) {
        this.hendynummer = hendynummer;
    }




    public void work () {
        System.out.println(getName() + " is working as a" + role);
    }


@Override
    public String toString () {
        return getName();
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
