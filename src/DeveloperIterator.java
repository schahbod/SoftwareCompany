import java.util.Iterator;
import java.util.List;

public class DeveloperIterator implements Iterator<Employee> {

    private List<Employee> employees;
    private int currentIndex = 0;

    public DeveloperIterator(List<Employee> employees) {
        this.employees = employees;
        findNextDeveloper();
    }

    private void findNextDeveloper() {
        while (currentIndex < employees.size() && employees.get(currentIndex).getRole() != Employee.Role.DEVELOPER) {
            currentIndex++;
        }
    }

    @Override
    public boolean hasNext() {
        return currentIndex < employees.size();
    }

    @Override
    public Employee next() {
        Employee dev = employees.get(currentIndex);
        currentIndex++;
        findNextDeveloper();
        return dev;
    }
}