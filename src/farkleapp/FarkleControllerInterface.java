package farkleapp;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


/**
 * This specifies our MV Controller Interface layout.
 */
public interface FarkleControllerInterface {

    /**
     * Implementation explained in farkleapp.Controller.
     */
    void exitHandler();
    /**
     * Implementation explained in farkleapp.Controller.
     * @param event ActionEvent.
     */
    void enterGameScreenButtonPushed(ActionEvent event);
    /**
     * Implementation explained in farkleapp.Controller.
     * @param event ActionEvent.
     */
    void rollTheDiceButtonPushed(ActionEvent event);
    /**
     * Implementation explained in farkleapp.Controller.
     * @param event ActionEvent.
     */
    void bankPointsButtonPushed(ActionEvent event);
    /**
     * Implementation explained in farkleapp.Controller.
     * @param event ActionEvent.
     */
    void holdRectangles(MouseEvent event);

    /**
     * Implementation explained in farkleapp.Controller.
     * @return ArrayList of rectangles to be passed to a model.
     */
    ArrayList<Rectangle> getRectangles();

}

