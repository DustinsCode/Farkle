package testing;


import farkleapp.FarkleControllerInterface;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Fake Controller for testing.
 */
public class FakeController implements FarkleControllerInterface {

    /**
     * Fake rectangle.
     */
    private final Rectangle rect1 = new Rectangle();
    /**
     * Fake rectangle.
     */
    private final Rectangle rect2 = new Rectangle();
    /**
     * Fake rectangle.
     */
    private final Rectangle rect3 = new Rectangle();
    /**
     * Fake rectangle.
     */
    private final Rectangle rect4 = new Rectangle();
    /**
     * Fake rectangle.
     */
    private final Rectangle rect5 = new Rectangle();
    /**
     * Fake rectangle.
     */
    private final Rectangle rect6 = new Rectangle();
    /**
     * Fake rectangle.
     */
    private ArrayList<Rectangle> rectangles = new ArrayList<>();


    @Override
    public void exitHandler() {

    }

    @Override
    public void enterGameScreenButtonPushed(final ActionEvent event) {

    }

    @Override
    public void rollTheDiceButtonPushed(final ActionEvent event) {

    }

    @Override
    public void bankPointsButtonPushed(final ActionEvent event) {

    }

    @Override
    public void holdRectangles(final MouseEvent event) {

    }



    @Override
    public ArrayList<Rectangle> getRectangles() {
        setUp();
        return rectangles;

    }

    /**
     * Fake setup.
     */
    public void setUp() {
        rectangles.clear();
        rectangles.add(rect1);
        rectangles.add(rect2);
        rectangles.add(rect3);
        rectangles.add(rect4);
        rectangles.add(rect5);
        rectangles.add(rect6);
    }

}
