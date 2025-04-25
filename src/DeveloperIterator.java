import java.util.List;
import java.util.NoSuchElementException;

public class DeveloperIterator implements EmployeeIterator {
    private List<Employee> employees;
    private int position = 0;

    public DeveloperIterator(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean hasNext() {
        while (position < employees.size()) {
            if (employees.get(position).getRole() == Employee.Role.DEVELOPER) {
                return true;
            }
            position++;
        }
        return false;
    }

    @Override
    public Employee next() {
        if (!hasNext()) throw new NoSuchElementException();
        return employees.get(position++);
    }
}
