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

    void exitHandler();

    void enterGameScreenButtonPushed(final ActionEvent event);

    void setRectangleArray();

    void rollTheDiceButtonPushed( final ActionEvent event );

    void bankPointsButtonPushed( final ActionEvent event );

    void holdRectangles(MouseEvent event);

    ArrayList<Rectangle> getRectangles ();

}
