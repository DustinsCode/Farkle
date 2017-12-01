package farkleapp;

import javafx.scene.control.Alert;


/**
 * This class is a Model to View Alerts helper class for our model.
 *
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

    /**
     * Displays the alert notifying the user that there's been a farkle.
     */
    void farkleAlert() {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(super.getPrimaryStage());
        alert.setTitle("Farkle!");
        alert.setHeaderText("You have Farkled: Round Reset");
        alert.setContentText(
                "Try again! If you farkle 3 times,"
                        + " you lose 1,000 from bank!"
        );
        alert.show();
    }

    /**
     * This displays the alert for winning the game.
     */
    void wonGame() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(super.getPrimaryStage());
        alert.setTitle("You win!");
        alert.setHeaderText("Bank Reached 10,000!");
        alert.setContentText(
                "You have won the model, "
                        + "please exit and start a new model!");
        alert.show();
    }

   void notRolled() {
       Alert alert = new Alert(Alert.AlertType.WARNING);
       alert.initOwner(FarkleApp.getPrimaryStage());
       alert.setTitle("Action Not Allowed");
       alert.setHeaderText("Must Roll Dice");
       alert.setContentText(
               "You are not allowed to select a dice before rolling."
                       + " Please roll first.");
       alert.show();
   }

}
