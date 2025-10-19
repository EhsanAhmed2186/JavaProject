package Library.app.controllers;

import Library.model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberDashboardController {

    private static final Logger logger = Logger.getLogger(MemberDashboardController.class.getName());

    @FXML
    private Label messageLabel;

    private Member member;

    public void setMember(Member member) {
        this.member = member;
        updateGreeting();
    }

    private void updateGreeting() {
        if (member != null) {
            if (member.getMemberId().equals("admin1234")) {
                messageLabel.setText("Hi, Admin");
                messageLabel.setTextFill(Color.GOLD);
                messageLabel.setStyle("-fx-font-weight: bold; -fx-effect: dropshadow(gaussian, gold, 10, 0.3, 0, 0);");
            } else {
                messageLabel.setText("Hi, " + member.getName());
                messageLabel.setTextFill(Color.BLACK);
                messageLabel.setStyle("-fx-font-weight: normal;");
            }
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        switchScene("/Library/app/views/member_login.fxml", (Node) event.getSource());
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
}
