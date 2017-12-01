package testing;



import farkleapp.Controller;
import farkleapp.Model;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


/**
 * This is the test class for Controller.
 * It implements both JUnit 4 and Mockito 2.11.
 * It "mocks" the dependency object in the controller so I can verify
 * it's calling the proper methods inside of the Model class.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    /**
     * Mocked Model instance.
     */
    @Mock
    private final Model model = new Model(new FakeController());
    /**
     * This is a spy of the roundPoints Label.
     */
    @Spy
    private Label roundPoints;
    /**
     * This is a spy of the bankPoints Label.
     */
    @Spy
    private Label bankPoints;
    /**
     * Injected into our Controller object.
     */
    @InjectMocks
    private Controller controller = new Controller();


    /**
     * Initializes our sterile JavaFX thread and runtime environment.
     *
     * @throws InterruptedException If an internal exception occurs
     *                              within the JavaFX application thread.
     */
    @BeforeClass
    public static void setUp() throws InterruptedException {


        // Initialise Java FX

        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(FarkleModelTest.class);
            }
        };
        t.setDaemon(true);
        t.start();
        Thread.sleep(500);
    }


    /**
     * This test determines whether or not the rollDice
     * method used to capture ActionEvents is actually able to roll the dice.
     * It verifies that the rollCount variable in
     * Model is incremented
     * (thus, it's calling all of the methods in the class).
     */
    @Test
    public void rollTheDiceButtonPushedTest() {
        controller.rollTheDiceButtonPushed(new ActionEvent());
        verify(model, atMost(1)).getRollCount();
        verify(model, atMost(1)).isFarkle(any(ArrayList.class));

    }

    /**
     * This test verifies that the proper methods were called
     * in our UILogic class by the bankPoints method in the controller.
     */
    @Test
    public void bankPointsTest() {
        controller.bankPointsButtonPushed(new ActionEvent());
        verify(model, atMost(1)).setBankScore();
        verify(model, atMost(1)).setRollCount(any(Integer.class));
        verify(model, atMost(1)).wonGameStatus();

    }

    /**
     * This test determines if we're properly loading
     * the rectangles into the rectangles array.
     * It verifies that the size of the rectangle array is correct.
     */
    @Test
    public void setUpTest() {
        controller.setUp();
        assertTrue("The number of rectangles should be 6.", controller.getRectangles().size() == 6);

    }


}

