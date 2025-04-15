import java.util.ArrayList;
import java.util.List;

public class Company {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void printAllEmployee() {
        employees.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        for (Employee employee : employees) {
            System.out.println("Employee ID: " + employee.getID() + ", Name: " + employee.getName() +
                    ", Role: " + employee.getRole() + ", Salary = " + employee.getSalary());

            if (employee instanceof PartTimeEmployee) {
                PartTimeEmployee partTimeEmployee = (PartTimeEmployee) employee;
                System.out.println("Workdays: " + partTimeEmployee.getWorkday());
            } else {
                System.out.println(" workdays : Full time employee"  );
                System.out.println(" Hours per month : " + employee.getHourspermonth());
            }
        }
    }
}
