package groupthree;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

@SuppressWarnings("unused")
interface FarkleInterface {

    void exitHandler();

    void enterGameScreenButtonPushed(final ActionEvent event);

    void setRectangleArray();

    void rollTheDiceButtonPushed(final ActionEvent event );

    void bankPointsButtonPushed( final ActionEvent event );

    void holdRectangles(MouseEvent event);

    ArrayList<Rectangle> getRectangles ();

}
