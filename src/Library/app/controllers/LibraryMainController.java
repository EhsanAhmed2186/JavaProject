package Library.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LibraryMainController {

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
            e.printStackTrace();
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
