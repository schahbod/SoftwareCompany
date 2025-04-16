import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmployeeGUI extends Application {

    private String[] employeeNames = {"Domenik", "Shahbod", "Anika", "Tom"};

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox(10);

        for (String employeeName : employeeNames) {
            Button button = new Button("Click for " + employeeName + " Info");
            button.setOnAction(event -> showPopup(employeeName));
            vBox.getChildren().add(button);
        }

        StackPane root = new StackPane();
        root.getChildren().add(vBox);

        Scene scene = new Scene(root, 300, 300);

        primaryStage.setTitle("EmployeeGUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showPopup(String employeeName) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Employee Information");
        alert.setHeaderText(employeeName + " Information");
        alert.setContentText(employeeName + " is a developer with a salary of $3500.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
