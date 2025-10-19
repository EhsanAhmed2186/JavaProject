package Library.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;

public class LibraryMainController {
    public static final Logger logger = Logger.getLogger(LibraryMainController.class.getName());

    @FXML
    private void onAdminClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Library/app/views/admin_login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) javafx.stage.Window.getWindows().stream()
                    .filter(Window::isShowing)
                    .findFirst()
                    .orElse(null);

            if (stage != null) {
                stage.setScene(new Scene(root));
                stage.setTitle("Admin Login");
                stage.show();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
    }
    @FXML
    private void onMemberClick()  {
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Library/app/views/member_login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) javafx.stage.Window.getWindows().stream()
                .filter(Window::isShowing)
                .findFirst()
                .orElse(null);
        if (stage != null) {
            stage.setScene(new Scene(root));
            stage.setTitle("Member Login");
            stage.show();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    }
}
