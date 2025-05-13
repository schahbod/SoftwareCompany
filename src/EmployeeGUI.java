import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.util.Duration;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        Label backgroundLabel = new Label("RieckSoftwareCompany");
        backgroundLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        backgroundLabel.setStyle("-fx-text-fill: DodgerBlue;");

        ComboBox<String> branchSelector = new ComboBox<>();
        branchSelector.getItems().addAll("Main Branch", "Asia Branch", "HR Department");
        branchSelector.setPrefWidth(150);
        branchSelector.setPromptText("Select Branch");

        TextField searchField = new TextField();
        searchField.setPromptText("Search by name");
        searchField.setPrefWidth(150);

        ListView<String> ticketListView = new ListView<>();
        ticketListView.setPrefHeight(150);
        ticketListView.setPrefWidth(300);

        Button showInfoButton = new Button("Show Employee Info & Tickets");
        showInfoButton.setStyle("-fx-font-size: 12px; -fx-padding: 5 10;");
        styleButton(showInfoButton);

        HBox buttonBox = new HBox(10, showInfoButton);
        buttonBox.setAlignment(Pos.CENTER);

        showInfoButton.setOnAction(event -> {
            ticketListView.getItems().clear();
            String searchText = searchField.getText().toLowerCase().trim();
            Employee selectedEmployee = null;

            if (!searchText.isEmpty()) {
                List<Employee> matchingEmployees = Employee.employees.stream()
                        .filter(emp -> emp.getName().toLowerCase().contains(searchText))
                        .collect(Collectors.toList());
                if (!matchingEmployees.isEmpty()) {
                    selectedEmployee = matchingEmployees.get(0);
                }
            }

            if (selectedEmployee == null) {
                ticketListView.getItems().add("No matching employee found.");
                return;
            }

            String[] lines = selectedEmployee.getGuiInfo().split("\n");
            ticketListView.getItems().addAll(lines);

            List<AufgabeTicket> tickets = selectedEmployee.getTickets();
            if (tickets == null || tickets.isEmpty()) {
                ticketListView.getItems().add("No tickets found for " + selectedEmployee.getName());
            } else {
                List<String> ticketDescriptions = tickets.stream()
                        .map(ticket -> ticket.getDescription() + " - Due: " + ticket.getDueTime() +
                                (ticket.getFertiggestellt() ? " (Completed)" : ""))
                        .collect(Collectors.toList());
                ticketListView.getItems().addAll(ticketDescriptions);
            }

            WorkSchedule schedule = selectedEmployee.getWorkSchedule();
            if (schedule != null) {
                String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
                ticketListView.getItems().add("Work Schedule:");
                for (int i = 0; i < 5; i++) {
                    String start = (schedule.getStartTime(i) != null) ? schedule.getStartTime(i).toString() : "Not Set";
                    String end = (schedule.getEndTime(i) != null) ? schedule.getEndTime(i).toString() : "Not Set";
                    ticketListView.getItems().add(days[i] + ": " + start + " to " + end);
                }
                ticketListView.getItems().add("Total Work Hours: " + selectedEmployee.calculateTotalWorkHours() + " hours/week");
            }

            Employee finalSelectedEmployee = selectedEmployee;
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(primaryStage);
            dialog.setTitle("Enter Work Schedule for " + finalSelectedEmployee.getName());

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setHgap(10);
            grid.setVgap(10);

            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
            TextField[] startFields = new TextField[5];
            TextField[] endFields = new TextField[5];

            for (int i = 0; i < 5; i++) {
                startFields[i] = new TextField();
                startFields[i].setPromptText("HH:mm (e.g., 09:00)");
                endFields[i] = new TextField();
                endFields[i].setPromptText("HH:mm (e.g., 17:00)");
                grid.add(new Label(days[i] + " Start:"), 0, i);
                grid.add(startFields[i], 1, i);
                grid.add(new Label("End:"), 2, i);
                grid.add(endFields[i], 3, i);
            }

            Label errorLabel = new Label();
            errorLabel.setStyle("-fx-text-fill: red;");

            Button submitButton = new Button("Submit Schedule");
            submitButton.setOnAction(e -> {
                String[] startInputs = new String[5];
                String[] endInputs = new String[5];
                for (int i = 0; i < 5; i++) {
                    startInputs[i] = startFields[i].getText().trim();
                    endInputs[i] = endFields[i].getText().trim();
                }
                try {
                    finalSelectedEmployee.assignWorkSchedule(startInputs, endInputs);
                    dialog.close();

                    ticketListView.getItems().clear();
                    ticketListView.getItems().addAll(lines);
                    List<AufgabeTicket> updatedTickets = finalSelectedEmployee.getTickets();
                    if (updatedTickets != null && !updatedTickets.isEmpty()) {
                        List<String> updatedTicketDescriptions = updatedTickets.stream()
                                .map(ticket -> ticket.getDescription() + " - Due: " + ticket.getDueTime() +
                                        (ticket.getFertiggestellt() ? " (Completed)" : ""))
                                .collect(Collectors.toList());
                        ticketListView.getItems().addAll(updatedTicketDescriptions);
                    } else {
                        ticketListView.getItems().add("No tickets found for " + finalSelectedEmployee.getName());
                    }
                    ticketListView.getItems().add("Work Schedule:");
                    for (int i = 0; i < 5; i++) {
                        String start = (schedule.getStartTime(i) != null) ? schedule.getStartTime(i).toString() : "Not Set";
                        String end = (schedule.getEndTime(i) != null) ? schedule.getEndTime(i).toString() : "Not Set";
                        ticketListView.getItems().add(days[i] + ": " + start + " to " + end);
                    }
                    ticketListView.getItems().add("Total Work Hours: " + finalSelectedEmployee.calculateTotalWorkHours() + " hours/week");
                } catch (IllegalArgumentException ex) {
                    errorLabel.setText(ex.getMessage());
                }
            });

            VBox dialogVBox = new VBox(10, grid, errorLabel, submitButton);
            dialogVBox.setAlignment(Pos.CENTER);
            dialogVBox.setPadding(new Insets(10));

            Scene dialogScene = new Scene(dialogVBox, 400, 350);
            dialog.setScene(dialogScene);
            dialog.showAndWait();
        });

        VBox inputBox = new VBox(10, branchSelector, searchField);
        inputBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(backgroundLabel, inputBox, buttonBox, ticketListView);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Employee GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void styleButton(Button button) {
        button.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });

        button.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}