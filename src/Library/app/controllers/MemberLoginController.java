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
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberLoginController {

    private static final Logger logger = Logger.getLogger(MemberLoginController.class.getName());

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

        // Step 1: Check if ID field is empty
        if (id.isEmpty()) {
            messageLabel.setText("Please enter your Member ID.");
            return;
        }

        // Step 2: Admin login (ID only, no password)
        if (id.equals("admin1234")) {
            Member admin = new Member("admin1234", "Admin User");
            messageLabel.setText("Admin login successful!");
            loadMemberDashboard(admin);
            return;
        }

        // Step 3: Check if password is empty (for regular members)
        if (password.isEmpty()) {
            messageLabel.setText("Please enter your password.");
            return;
        }

        // Step 4: Find the member
        Member member = library.findMember(id);
        if (member == null) {
            messageLabel.setText("Member not found.");
            return;
        }

        // Step 5: Verify password (last 3 digits of ID)
        if (id.length() >= 3) {
            String lastThree = id.substring(id.length() - 3);
            if (password.equals(lastThree)) {
                messageLabel.setText("Login successful!");
                loadMemberDashboard(member);
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
            logger.log(Level.SEVERE, "Error loading scene: " + fxmlPath, e);
            messageLabel.setText("Error loading scene!");
        }
    }

    private void loadMemberDashboard(Member member) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Library/app/views/member_dashboard.fxml"));
            Parent root = loader.load();

            // Pass member info to dashboard
            MemberDashboardController controller = loader.getController();
            controller.setMember(member);

            Stage stage = (Stage) memberIdField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Library Management System");
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading member dashboard", e);
            messageLabel.setText("Error loading member dashboard!");
        }
    }
}
