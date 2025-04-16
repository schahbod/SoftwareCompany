import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        Company myCompany = new Company();

        Employee emp1 = new Employee("Domenik", 201, Employee.Role.DEVELOPER, 3500, Employee.AccessLevel.Developer);
        Employee emp2 = new Employee("Shahbod", 485, Employee.Role.DEVELOPER, 4000, Employee.AccessLevel.Developer);
        PartTimeEmployee emp3 = new PartTimeEmployee("Anika", 500, Employee.Role.DESIGNER, 2500.0, 20, 5, Employee.AccessLevel.Employee);
        Employee emp4 = new Employee("Tom", 405, Employee.Role.MANAGER, 5500, Employee.AccessLevel.Manager);

        Employee.addEmployee(emp1);
        Employee.addEmployee(emp2);

        myCompany.addEmployee(emp1);
        myCompany.addEmployee(emp2);
        myCompany.addEmployee(emp3);
        myCompany.addEmployee(emp4);

        System.out.println("\n📋 EMPLOYEES SORTED BY SALARY:");
        myCompany.printAllEmployee();
        System.out.println("\n Working Status");

        emp1.work();
        emp2.work();
        emp3.work();
        emp4.work();

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

        System.out.println("Aufgabe ticket is for " + aufgabeTicket1.getTaskid() + " and he is working on " + emp1.getName() + " he is working on " + aufgabeTicket1.getBeschreibung());
        System.out.println("Aufgabe ticket is for " + aufgabeTicket2.getTaskid() + " and She is working on " + aufgabeTicket2.getBeschreibung());
        System.out.println("Aufgabe ticket is for " + aufgabe3.getTaskid() + " and he is working on " + aufgabe3.getBeschreibung());
        System.out.println("Aufgabe ticket is for " + aufgabeTicket4.getTaskid() + " and he is working on " + aufgabeTicket4.getBeschreibung());

        System.out.println("\n Aufgabe Details for Anika");
        System.out.println("✅ Fertiggestellt: " + aufgabe3.isFertiggestellt());
        System.out.println("Mitarbeiter: " + aufgabe3.getMitarbeiter().getName());
        System.out.println("\uD83D\uDCDE " + aufgabe3.getMitarbeiter().getName() + " HendyNummer: " + aufgabe3.getMitarbeiter().getHendynummer());
        System.out.println("✅ Fertiggestellt: " + aufgabe3.isFertiggestellt());
        System.out.println("⏰ Erledigt um: " + aufgabe3.getErledigt());
        System.out.println("\uD83D\uDCDD Status: " + aufgabe3.getStatus());
        System.out.println("CICD status: " + aufgabeTicket1.getStatus());

        emp1.showTimeZoneDetails();

        AsiaBranchEmployee asiaEmp1 = new AsiaBranchEmployee(601, "Hun Son", Employee.Role.DEVELOPER, 3000, Employee.AccessLevel.Developer);
        AsiaBranchEmployee asiaEmp2 = new AsiaBranchEmployee(602, "Xao", Employee.Role.MANAGER, 6000, Employee.AccessLevel.Manager);

        asiaEmp1.work();
        asiaEmp2.work();

        asiaEmp1.showTimeZoneDetails();
        asiaEmp2.showTimeZoneDetails();

        System.out.println("Can Domenik access AdminPanel? " + emp1.hasAccessTo("AdminPanel"));
        System.out.println("Can Tom access CodeBase? " + emp4.hasAccessTo("CodeBase"));
        System.out.println("Can Hun Son access AsiaPanel? " + asiaEmp1.hasAccessTo("AsiaPanel"));
        System.out.println("Can Xao access AsiaPanel? " + asiaEmp2.hasAccessTo("AsiaPanel"));

        EmployeeGUI.main(args);
    }
}
