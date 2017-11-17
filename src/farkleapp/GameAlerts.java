package farkleapp;

import javafx.scene.control.Alert;


/**
 * This class is an Alerts helper class for our model.
 *
 */
public class GameAlerts extends FarkleApp {

    /**
     * Method that displays alert window for
     * when a user tries to release after rolling.
     */
    void afterRollTriedRelease() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        // Static reference to getPrimaryStage() is
        // because we want the current running stage.
        alert.initOwner(super.getPrimaryStage());
        alert.setTitle("Action Not Allowed");
        alert.setHeaderText("Unable to Release Dice");
        alert.setContentText(
                "You are not allowed to release"
                        + " a dice after rolling!");
        alert.show();
    }

}
