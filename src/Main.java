import java.time.LocalTime;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        Company myCompany = new Company();

        Employee emp1 = new Employee("Domenik", 201, Employee.Role.DEVELOPER, 3500, Employee.AccessLevel.Developer);
        Employee emp2 = new Employee("Shahbod", 485, Employee.Role.DEVELOPER, 4000, Employee.AccessLevel.Developer);
        PartTimeEmployee emp3 = new PartTimeEmployee("Anika", 500, Employee.Role.DESIGNER, 2500.0, 20, 5, Employee.AccessLevel.Employee);
        Employee emp4 = new Employee("Tom", 405, Employee.Role.MANAGER, 5500, Employee.AccessLevel.Manager);
        AsiaBranchEmployee asiaEmp1 = new AsiaBranchEmployee(601, "Hun Son", Employee.Role.DEVELOPER, 3000, Employee.AccessLevel.Developer);
        AsiaBranchEmployee asiaEmp2 = new AsiaBranchEmployee(602, "Xao", Employee.Role.MANAGER, 6000, Employee.AccessLevel.Manager);
        Employee hrEmp = new Employee("Sabine", 701, Employee.Role.MANAGER, 5000, Employee.AccessLevel.HR_MANAGER);

        Employee.addEmployee(emp1);
        Employee.addEmployee(emp2);
        Employee.addEmployee(emp3);
        Employee.addEmployee(emp4);
        Employee.addEmployee(asiaEmp1);
        Employee.addEmployee(asiaEmp2);
        Employee.addEmployee(hrEmp);

        myCompany.addEmployee(emp1);
        myCompany.addEmployee(emp2);
        myCompany.addEmployee(emp3);
        myCompany.addEmployee(emp4);
        myCompany.addEmployee(asiaEmp1);
        myCompany.addEmployee(asiaEmp2);
        myCompany.addEmployee(hrEmp);

        Employee.employees.forEach(emp -> System.out.println(" - " + emp.getName() + ": " + emp.getRole() + ", $" + emp.getSalary() + ", ID: " + emp.getID() + ", AccessLevel: " + emp.getAccessLevel()));

        Application.launch(EmployeeGUI.class, args);

        myCompany.printAllEmployee();

        DeveloperIterator developerIterator = new DeveloperIterator(Employee.employees);
        while (developerIterator.hasNext()) {
            Employee dev = developerIterator.next();
            System.out.println(dev.getName() + " " + dev.getRole());
        }

        emp1.work();
        emp2.work();
        emp3.work();
        emp4.work();
        asiaEmp1.work();
        asiaEmp2.work();
        hrEmp.work();

        emp1.setHendynummer(152147485);

        LocalTime duetime = LocalTime.of(12, 0);

        AufgabeTicket aufgabeTicket1 = new AufgabeTicket("CICD Setup", emp1, duetime);
        aufgabeTicket1.setFertiggestellt(true);

        AufgabeTicket aufgabeTicket2 = new AufgabeTicket("API Development", emp2, duetime);
        aufgabeTicket2.setFertiggestellt(true);

        AufgabeTicket aufgabe3 = new AufgabeTicket("UI-Design erstellen", emp3, duetime);
        aufgabe3.setFertiggestellt(true);

        AufgabeTicket aufgabeTicket4 = new AufgabeTicket("Project Management", emp4, duetime);
        aufgabeTicket4.setFertiggestellt(true);

        emp1.showTimeZoneDetails();
        asiaEmp1.showTimeZoneDetails();
        asiaEmp2.showTimeZoneDetails();

        System.out.println("Can Domenik access AdminPanel? " + emp1.hasAccessTo("AdminPanel"));
        System.out.println("Can Tom access CodeBase? " + emp4.hasAccessTo("CodeBase"));
        System.out.println("Can Hun Son access AsiaPanel? " + asiaEmp1.hasAccessTo("AsiaPanel"));
        System.out.println("Can Xao access AsiaPanel? " + asiaEmp2.hasAccessTo("AsiaPanel"));
    }
}