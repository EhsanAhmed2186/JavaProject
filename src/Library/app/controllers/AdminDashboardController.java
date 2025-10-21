package Library.app.controllers;
import model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {
    @FXML
    private TextField adminIdField;

    @FXML
    private Label messageLabel;
    @FXML
    private void handleBack(ActionEvent event) {
        switchScene("/Library/app/views/admin_login.fxml", (Node) event.getSource());
    }
    @FXML
    private void handleAddMember(ActionEvent event){
        switchScene("/Library/app/views/add_member.fxml",(Node) event.getSource());
    }
    @FXML
    private void handleViewMembers(ActionEvent event) {
        switchScene("/Library/app/views/view_all_member.fxml", (Node) event.getSource());
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
