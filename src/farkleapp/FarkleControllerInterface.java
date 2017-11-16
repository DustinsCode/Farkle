package farkleapp;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This specifies our MV Controller Interface layout.
 */
public interface FarkleControllerInterface {

    void exitHandler();

    void enterGameScreenButtonPushed(ActionEvent event);

    void setRectangleArray();

    void rollTheDiceButtonPushed( final ActionEvent event );

    void bankPointsButtonPushed( final ActionEvent event );

    void holdRectangles(MouseEvent event);

    ArrayList<Rectangle> getRectangles ();

}
