package Library.app.controllers;

import Library.data.DataHandler;
import Library.model.Library;
import Library.model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MemberLoginController {

    @FXML
    private TextField memberIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private Library library;

    @FXML
    public void initialize() {
        library = DataHandler.loadLibrary();
    }

    @FXML
    private void handleLogin() {
        String id = memberIdField.getText().trim();
        String password = passwordField.getText().trim();

        if (id.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please fill in both fields.");
            return;
        }

        Member member = library.findMember(id);
        if (member == null) {
            messageLabel.setText("Member not found.");
            return;
        }

        // Verify password
        if (id.length() >= 3) {
            String lastThree = id.substring(id.length() - 3);
            if (password.equals(lastThree)) {
                messageLabel.setText("Login successful!");
            } else {
                messageLabel.setText("Incorrect password.");
            }
        } else {
            messageLabel.setText("Invalid ID format.");
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
