import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.util.StringConverter;

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

            List<String> employeeInfo = Arrays.asList(
                    "Employee ID: " + selectedEmployee.getID(),
                    "Role: " + selectedEmployee.getRole(),
                    "Salary: $" + selectedEmployee.getSalary()
            );
            ticketListView.getItems().addAll(employeeInfo);

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
