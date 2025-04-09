
public class Main {
    public static void main(String[] args) {
        Company myCompany = new Company();

        Employee emp1 = new Employee("Domenik", 201, Employee.Role.DEVELOPER, 3500);
        Employee emp2 = new Employee("Shahbod", 485, Employee.Role.DEVELOPER,4000);
        Employee emp3 = new Employee("Anika", 500, Employee.Role.DESIGNER,2500);
        Employee emp4 = new Employee("Tom", 405, Employee.Role.MANAGER,5500);


        myCompany.addEmployee(emp1);
        myCompany.addEmployee(emp2);
        myCompany.addEmployee(emp3);
        myCompany.addEmployee(emp4);

        Employee.EmployeeDetails employeeDetails = emp1.new EmployeeDetails();
        employeeDetails.displayDetails();

    }
}
