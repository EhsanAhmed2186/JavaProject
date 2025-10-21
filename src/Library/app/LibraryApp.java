package Library.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;


public class LibraryApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Library/app/views/library_main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
