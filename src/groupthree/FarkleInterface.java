package groupthree;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * This interface allowed us to test certain classes by replicating a controller.
 */
@SuppressWarnings("unused")
public interface FarkleInterface {


    /**
     * exitHandler is when the game is exited.
     */
    void exitHandler();

    /**
     * enterGameScreenbuttonPushed is for when the gamescreen button is pushed, specifically farkle.
     * @param event
     */
    void enterGameScreenButtonPushed(final ActionEvent event);

    /**
     * SetRectangleArray builds the array of dice.
     */
    void setRectangleArray();

    /**
     * rollTheDiceButtonPushed is for when the roll button is clicked.
     * @param event
     */
    void rollTheDiceButtonPushed( final ActionEvent event );

    /**
     * bankPointsButtonPushed is for when the bank points button is pushed.
     * @param event
     */
    void bankPointsButtonPushed( final ActionEvent event );

    /**
     * holdRectangles is for when the dice objects are clicked.
     * @param event
     */
    void holdRectangles(MouseEvent event);

    ArrayList<Rectangle> getRectangles ();

}
