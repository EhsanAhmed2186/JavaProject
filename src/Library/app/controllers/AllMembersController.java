package Library.app.controllers;

import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AllMembersController{

    @FXML
    private TableView<Member> memberTable;

    @FXML
    private TableColumn<Member, String> nameColumn;

    @FXML
    private TableColumn<Member, String> idColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        // Load saved library data
        Library library = DataHandler.loadLibrary();

        // Bind columns to Member fields
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMemberId()));

        // Load members
        ObservableList<Member> memberList = FXCollections.observableArrayList(library.getMembers());
        memberTable.setItems(memberList);

        // Setup search filter
        FilteredList<Member> filteredData = new FilteredList<>(memberList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(member -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return member.getName().toLowerCase().contains(lowerCaseFilter)
                        || member.getMemberId().toLowerCase().contains(lowerCaseFilter);
            });
        });
        memberTable.setItems(filteredData);
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
