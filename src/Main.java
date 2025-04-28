import java.time.LocalTime;

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

        LocalTime duetime = LocalTime.of(12, 0);

        AufgabeTicket aufgabeTicket1 = new AufgabeTicket("CICD Setup", emp1, duetime);
        aufgabeTicket1.setFertiggestellt(true);
        emp1.addTicket(aufgabeTicket1);
        System.out.println("Domenik tickets after add: " + emp1.getTickets());

        AufgabeTicket aufgabeTicket2 = new AufgabeTicket("API Development", emp2, duetime);
        aufgabeTicket2.setFertiggestellt(true);
        emp2.addTicket(aufgabeTicket2);
        System.out.println("Shahbod tickets after add: " + emp2.getTickets());

        AufgabeTicket aufgabe3 = new AufgabeTicket("UI-Design erstellen", emp3, duetime);
        aufgabe3.setFertiggestellt(true);
        emp3.addTicket(aufgabe3);
        System.out.println("Anika tickets after add: " + emp3.getTickets());

        AufgabeTicket aufgabeTicket4 = new AufgabeTicket("Project Management", emp4, duetime);
        aufgabeTicket4.setFertiggestellt(true);
        emp4.addTicket(aufgabeTicket4);
        System.out.println("Tom tickets after add: " + emp4.getTickets());

        EmployeeGUI.main(args);

        Employee.employees.forEach(emp -> System.out.println(" - " + emp.getName() + ": " + emp.getRole() + ", $" + emp.getSalary() + ", ID: " + emp.getID() + ", AccessLevel: " + emp.getAccessLevel()));
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

        emp1.showTimeZoneDetails();
        asiaEmp1.showTimeZoneDetails();
        asiaEmp2.showTimeZoneDetails();

        System.out.println("Can Domenik access AdminPanel? " + emp1.hasAccessTo("AdminPanel"));
        System.out.println("Can Tom access CodeBase? " + emp4.hasAccessTo("CodeBase"));
        System.out.println("Can Hun Son access AsiaPanel? " + asiaEmp1.hasAccessTo("AsiaPanel"));
        System.out.println("Can Xao access AsiaPanel? " + asiaEmp2.hasAccessTo("AsiaPanel"));
    }
}