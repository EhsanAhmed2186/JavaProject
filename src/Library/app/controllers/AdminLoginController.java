package Library.app.controllers;
import model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class AdminLoginController {

    @FXML
    private TextField adminIdField;

    @FXML
    private Label messageLabel;

    // Handle Login button
    @FXML
    private void handleLogin() {
        String enteredId = adminIdField.getText().trim();
        String ADMIN_ID = "admin2003";
        if (enteredId.isEmpty()) {
            messageLabel.setText("Please enter Admin ID.");
        } else if (enteredId.equals(ADMIN_ID)) {
            messageLabel.setText("Login successful!");
            messageLabel.setStyle("-fx-text-fill: lightgreen;");
            switchScene("/Library/app/views/admin_dashboard.fxml", adminIdField);
        } else {
            messageLabel.setText("Invalid Admin ID. Try again.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        switchScene("/Library/app/views/library_main.fxml", (Node) event.getSource());
    }

    private void switchScene(String fxmlPath, Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Library Management System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Error loading scene!");
        }
    }
}
