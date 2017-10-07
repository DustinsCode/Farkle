package groupthree;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

public class farkleHomePage extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("farkleHomePage.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Farkle Alpha Release 1.0");
        primaryStage.show();







    }

    public static void main(String[] args) {
        launch(args);

    }
}
