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
        for (Employee employee : employeeHashMap.values()) {
            System.out.println("Employee ID: " + employee.getId() + ", Name: " + employee.getName() + ", Role: " + employee.getRole() + "  Salary = " + employee.getSalary());
        }
    }
}
