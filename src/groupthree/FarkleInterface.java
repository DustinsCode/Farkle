package groupthree;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;

public interface FarkleInterface {

    void exitHandler();

    void enterGameScreenButtonPushed(final ActionEvent event);

    void setRectangleArray();

    void rollTheDiceButtonPushed( final ActionEvent event );

    void bankPointsButtonPushed( final ActionEvent event );

    void holdRectangles(MouseEvent event);

    ArrayList<Rectangle> getRectangles ();

}
