import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Pos;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        Label backgroundLabel = new Label("RieckSoftwareCompany");
        backgroundLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        backgroundLabel.setStyle("-fx-text-fill: DodgerBlue;");
        StackPane.setAlignment(backgroundLabel, Pos.TOP_CENTER);

        ComboBox<String> branchSelector = new ComboBox<>();
        branchSelector.getItems().addAll("Main Branch", "Asia Branch", "HR Department");
        branchSelector.setMaxWidth(200);

        TextField searchField = new TextField();
        searchField.setPromptText("Search by name");

        Button showInfoButton = new Button("Show Employee Info");

        showInfoButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), showInfoButton);
                scaleTransition.setToX(1.1);
                scaleTransition.setToY(1.1);
                scaleTransition.play();
            }
        });

        showInfoButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), showInfoButton);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                scaleTransition.play();
            }
        });

        VBox vbox = new VBox(10, branchSelector, searchField, showInfoButton);
        vbox.setStyle("-fx-padding: 20;");
        vbox.setAlignment(Pos.CENTER);
        StackPane.setAlignment(vbox, Pos.CENTER);

        root.getChildren().addAll(backgroundLabel, vbox);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Employee GUI");
        primaryStage.setScene(scene);
        primaryStage.show();

        showInfoButton.setOnAction(e -> {
            String selectedBranch = branchSelector.getValue();
            String searchText = searchField.getText().toLowerCase().trim();
            if (selectedBranch == null) {
                showPopup("Error", "Please select a branch.");
                return;
            }
            if ("HR Department".equals(selectedBranch)) {
                showHRLogin();
            } else {
                List<Employee> filteredList = filterEmployees(
                        selectedBranch.equals("Asia Branch") ? AsiaBranchEmployee.class : Employee.class,
                        searchText
                );
                showPopup(selectedBranch + " Employees", formatEmployeeList(filteredList));
            }
        });
    }

    private List<Employee> filterEmployees(Class<? extends Employee> employeeType, String searchText) {
        System.out.println("Filtering employees of type: " + employeeType.getSimpleName());

        List<Employee> filteredList = Employee.employees.stream()
                .filter(emp -> {
                    System.out.println("Checking employee: " + emp.getName());
                    if (employeeType == AsiaBranchEmployee.class) {
                        return emp instanceof AsiaBranchEmployee;
                    } else if (employeeType == Employee.class) {
                        return !(emp instanceof AsiaBranchEmployee) && emp.getAccessLevel() != Employee.AccessLevel.HR_MANAGER;
                    }
                    return false;
                })
                .filter(emp -> searchText.isEmpty() || emp.getName().toLowerCase().contains(searchText))
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());

        System.out.println("Filtered employees count: " + filteredList.size());
        return filteredList;

    }

    private String formatEmployeeList(List<Employee> employeeList) {
        if (employeeList.isEmpty()) {
            return "No employees found for the given search criteria.";
        }
        StringBuilder content = new StringBuilder();
        for (Employee emp : employeeList) {
            content.append(emp.getName()).append(": ")
                    .append(emp.getRole()).append(", $")
                    .append(emp.getSalary()).append(", ID: ")
                    .append(emp.getID()).append("\n");
        }
        return content.toString();
    }

    private void showHRLogin() {
        Stage loginStage = new Stage();
        loginStage.setTitle("HR Login");

        Label userLabel = new Label("Username: ");
        TextField usernameField = new TextField();

        Label passLabel = new Label("Password: ");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Label messageLabel = new Label();

        loginButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            public void handle(javafx.event.ActionEvent event) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (username.equals("Admin") && password.equals("hr123")) {
                    messageLabel.setText("Login successful");
                    loginStage.close();

                    List<Employee> hrEmployees = Employee.employees.stream()
                            .filter(emp -> emp.getAccessLevel() == Employee.AccessLevel.HR_MANAGER)
                            .collect(Collectors.toList());

                    if (hrEmployees.isEmpty()) {
                        showPopup("HR Department", "No HR employees found.");
                    } else {
                        showPopup("HR Department", formatEmployeeList(hrEmployees));
                    }

                } else {
                    messageLabel.setText("Login failed. Try again.");
                }
            }
        });

        VBox vbox = new VBox(10, userLabel, usernameField, passLabel, passwordField, loginButton, messageLabel);
        vbox.setStyle("-fx-padding: 20;");
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 250);
        loginStage.setScene(scene);
        loginStage.show();
    }

    private void showPopup(String title, String content) {
        System.out.println("Showing popup with title: " + title);
        System.out.println("Popup content:\n" + content);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}