import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;


public class Company {
    private HashMap<Integer, Employee> employeeHashMap;

    public Company() {
        employeeHashMap = new HashMap<>();
    }

    public void addEmployee(Employee employee) {
        employeeHashMap.put(employee.getId(), employee);
    }

    public Employee getEmployee(int id) {
        return employeeHashMap.get(id);
    }

    public void printAllEmployee() {
        List<Employee> employeeList = new ArrayList<>(employeeHashMap.values());
        employeeList.sort(Comparator.comparingDouble(Employee::getSalary));

        for (Employee employee : employeeList) {
            System.out.println("Employee ID: " + employee.getId() +
                    ", Name: " + employee.getName() +
                    ", Role: " + employee.getRole() +
                    ", Salary = " + employee.getSalary());


        }
    }
}





