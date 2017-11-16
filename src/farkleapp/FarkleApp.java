package farkleapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FarkleApp is the base class for the Farkle game. This allows for the basic construction of an FXML-assisted GUI
 * development as afforded by JavaFX Scene Builder 9.0. The class extends javafx.application.Application,
 * thus the start method is overridden to follow syntax.
 * @author Wes Harrison
 * @version 1.0
 */
public class FarkleApp extends Application {

   private static Stage primary;

    /**
     *
     * @param primaryStage Default override parameter.
     * @see javafx.stage.Stage
     * @throws Exception If and only if the FXML file is not found. In this case, we know the files are implemented in src.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primary = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Home_View.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Farkle Alpha Release 1.0");
        primaryStage.show();

    }

    /**
     * This method gives up the Primary Stage of the application.
     * @return primary Stage
     */
    static Stage getPrimaryStage() {
        return primary;
    }

    /**
     * The main method for
     * @param args normal command line argument input
     */
    public static void main(String[] args) {
        launch(args);
    }
}
