package testing;


import groupthree.FarkleInterface;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

@SuppressWarnings("Duplicates")
public class FakeController implements FarkleInterface {

    private final Rectangle rect1 = new Rectangle();

    private final Rectangle rect2 = new Rectangle();

    private final Rectangle rect3 = new Rectangle();

    private final Rectangle rect4 = new Rectangle();

    private final Rectangle rect5 = new Rectangle();

    private final Rectangle rect6 = new Rectangle();

    private final ArrayList<Rectangle> rectangles = new ArrayList<>();

    @Override
    public void exitHandler() {

    }

    @Override
    public void enterGameScreenButtonPushed(ActionEvent event) {

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
    public void rollTheDiceButtonPushed(ActionEvent event) {

    }

    @Override
    public void bankPointsButtonPushed(ActionEvent event) {

    }

    @Override
    public void holdRectangles(MouseEvent event) {

    }

    @Override
    public ArrayList<Rectangle> getRectangles() {
        setRectangleArray();
        return rectangles;

    }
}
