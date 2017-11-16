package testing;

//@TODO Make sure Wes implements a new testing plan or javadoc comments for this.
import farkleapp.FarkleControllerInterface;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class FakeController implements FarkleControllerInterface {

    private Rectangle rect1 = new Rectangle();

    private Rectangle rect2 = new Rectangle();

    private Rectangle rect3 = new Rectangle();

    private Rectangle rect4 = new Rectangle();

    private Rectangle rect5 = new Rectangle();

    private Rectangle rect6 = new Rectangle();

    private ArrayList<Rectangle> rectangles = new ArrayList<>();

    @Override
    public void exitHandler() {

    }

    @Override
    public void enterGameScreenButtonPushed(final ActionEvent event) {

    }

    @Override
    public void setRectangleArray() {
        rectangles.clear();
        rectangles.add(rect1);
        rectangles.add(rect2);
        rectangles.add(rect3);
        rectangles.add(rect4);
        rectangles.add(rect5);
        rectangles.add(rect6);
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
        setRectangleArray();
        return rectangles;

    }
}
