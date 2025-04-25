import java.time.LocalTime;


public class AufgabeTicket {
    private static  int taskcounter= 1;
    private String beschreibung;
    private boolean fertiggestellt;
    private Employee mitarbeiter;
    private String Status;
    private int taskid;


    private LocalTime erstellungsDatum;
    private LocalTime faelligkeitsDatum;
    private LocalTime erledigt;


    public AufgabeTicket(String beschreibung, Employee mitarbeiter, LocalTime faelligkeitsDatum) {
        this.beschreibung = beschreibung;
        this.fertiggestellt = false;
        this.mitarbeiter = mitarbeiter;
        this.erstellungsDatum = LocalTime.now();
        this.faelligkeitsDatum = faelligkeitsDatum;
        this.Status = "In Prozess";
        this.taskid = taskcounter++;
    }




    public String getBeschreibung() {
        return beschreibung;

    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;

    }

    public boolean isFertiggestellt() {
        return fertiggestellt;

    }

    public void setFertiggestellt(boolean fertiggestellt) {

        this.fertiggestellt = fertiggestellt;
        if (fertiggestellt) {
            this.erledigt = LocalTime.now();
            this.Status = " Erledigt ";
        } else {
            this.Status= "  In prozess  ";

        }
    }

    public String getStatus () {
        return Status;
    }

        public LocalTime getErledigt() {
            return erledigt;

    }

    public  Employee getMitarbeiter () {
        return mitarbeiter;

    }

    public void setMitarbeiter (Employee mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public int getTaskid(){
        return taskid;
    }
}

