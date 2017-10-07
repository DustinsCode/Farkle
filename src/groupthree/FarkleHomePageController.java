package groupthree;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FarkleHomePageController {

    /**
     * When this method is called, it will exit the application.
     */
    public void exitHandler() {
        System.exit(0);
    }
    /**
     * When this method is called, it will change the Scene to GameScreen.
     */

    public void enterGameScreenButtonPushed(ActionEvent event) throws IOException {

        Parent gameScreenParent = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
        Scene gameScreen = new Scene(gameScreenParent);

        Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(gameScreen);
        window.show();
    }


}
