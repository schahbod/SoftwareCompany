import java.util.ArrayList;
import java.util.List;

public class Company {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void printAllEmployee() {
        for (Employee emp : employees) {
            System.out.println(emp.getName() + " - " + emp.getRole());
        }
    }
}
