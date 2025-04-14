import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Company myCompany = new Company();

        Employee emp1 = new Employee("Domenik", 201, Employee.Role.DEVELOPER, 3500);
        Employee emp2 = new Employee("Shahbod", 485, Employee.Role.DEVELOPER, 4000);
        PartTimeEmployee emp3 = new PartTimeEmployee("Anika", 500, Employee.Role.DESIGNER, 2500, 20);
        Employee emp4 = new Employee("Tom", 405, Employee.Role.MANAGER, 5500);

        Employee.addEmployee(emp1);
        Employee.addEmployee(emp2);

        myCompany.addEmployee(emp1);
        myCompany.addEmployee(emp2);
        myCompany.addEmployee(emp3);
        myCompany.addEmployee(emp4);

        System.out.println("Employees sorted by salary:");
        myCompany.printAllEmployee();

        emp1.work();
        emp2.work();
        emp3.work();
        emp4.work();

        emp1.setHendynummer(152147485);

        LocalTime duetime = LocalTime.of(12, 0);

        AufgabeTicket aufgabe1 = new AufgabeTicket("UI-Design erstellen", emp3, duetime);
        aufgabe1.setFertiggestellt(true);

        System.out.println("Fertiggestellt: " + aufgabe1.isFertiggestellt());
        System.out.println("Domnik HendyNummer: " + emp1.getHendynummer());
        System.out.println("Erledigt um: " + aufgabe1.getErledigt());
        System.out.println("Status: " + aufgabe1.getStatus());

        emp1.showTimeZoneDetails();

        AsiaBranchEmployee asiaEmp1 = new AsiaBranchEmployee(601, "Hun Son", Employee.Role.DEVELOPER, 3000);
        AsiaBranchEmployee asiaEmp2 = new AsiaBranchEmployee(602, "Xao", Employee.Role.MANAGER, 6000);

        asiaEmp1.work();
        asiaEmp2.work();

        asiaEmp1.showTimeZoneDetails();
        asiaEmp2.showTimeZoneDetails();
    }
}
