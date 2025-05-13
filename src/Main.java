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

        AufgabeTicket ticket1 = new AufgabeTicket("CICD Setup", emp1, duetime);
        ticket1.setFertiggestellt(true);
        emp1.addTicket(ticket1);

        AufgabeTicket ticket2 = new AufgabeTicket("API Development", emp2, duetime);
        ticket2.setFertiggestellt(true);
        emp2.addTicket(ticket2);

        AufgabeTicket ticket3 = new AufgabeTicket("UI-Design erstellen", emp3, duetime);
        ticket3.setFertiggestellt(true);
        emp3.addTicket(ticket3);

        AufgabeTicket ticket4 = new AufgabeTicket("Project Management", emp4, duetime);
        ticket4.setFertiggestellt(true);
        emp4.addTicket(ticket4);

        EmployeeGUI.main(args);
    }
}