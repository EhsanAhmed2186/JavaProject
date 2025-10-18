package Library.app.controllers;

import Library.data.DataHandler;
import Library.model.Library;
import Library.model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddmemberController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private Label messageLabel;

    private Library library;

    @FXML
    public void initialize() {
        library = DataHandler.loadLibrary();
    }

    @FXML
    private void handleAddMember() {
        String name = nameField.getText().trim();
        String id = idField.getText().trim();

        if (name.isEmpty() || id.isEmpty()) {
            messageLabel.setText("Please enter both name and ID.");
            return;
        }

        if (library.findMember(id) != null) {
            messageLabel.setStyle("-fx-text-fill: black;");
            messageLabel.setText("Member with this ID already exists.");
            return;
        }


        library.addMember(name,id);

        DataHandler.saveLibrary(library);
        messageLabel.setStyle("-fx-text-fill: black;");
        messageLabel.setText("Member added successfully!");
        System.out.println("All Members in Library:");
        for (Member m : library.getMembers()) {
            System.out.println(" - " + m);
        }
        nameField.clear();
        idField.clear();
    }

    @FXML
    private void handleBack(ActionEvent event) {
        switchScene("/Library/app/views/admin_dashboard.fxml", (Node) event.getSource());
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
