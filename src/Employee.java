import javax.management.relation.Role;

public class Employee extends  Person {
    private String name;
    private int id;
    private Role role;
    private double salary;


    public enum Role {
        DEVELOPER, DESIGNER, MANAGER, TESTER
    }

    public Employee(String name, int id, Role role, double salary) {
        super(name, id);
        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
    }


    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }

 public double getSalary () {
        return salary;
 }
 public void setSalary (double salary) {
        this.salary = salary;
 }

public class EmployeeDetails {
            public void displayDetails() {
                System.out.println("Employee Name: " + name);
                System.out.println("Employee ID: " + id);
                System.out.println("Employee Role: " + role);
                System.out.println("Employee Salary: " + salary);
            }
        }
}

