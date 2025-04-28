import java.util.ArrayList;
import java.util.List;

public class Company {

    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public void printAllEmployee() {
        employees.forEach(emp -> System.out.println(" - " + emp.getName() + ": " + emp.getRole() + ", $" + emp.getSalary() + ", ID: " + emp.getID()));
    }
}