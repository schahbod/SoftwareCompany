import java.time.LocalTime;

public class AufgabeTicket {
    private String description;
    private Employee assignedEmployee;
    private LocalTime dueTime;
    private boolean fertiggestellt;

    public AufgabeTicket(String description, Employee assignedEmployee, LocalTime dueTime) {
        this.description = description;
        this.assignedEmployee = assignedEmployee;
        this.dueTime = dueTime;
        this.fertiggestellt = false;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getDueTime() {
        return dueTime;
    }

    public void setFertiggestellt(boolean fertiggestellt) {
        this.fertiggestellt = fertiggestellt;
    }

    public boolean getFertiggestellt() {
        return fertiggestellt;
    }
}