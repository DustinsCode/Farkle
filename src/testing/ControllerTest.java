package testing;



import com.sun.javafx.application.PlatformImpl;
import groupthree.DiceUILogic;
import groupthree.FarkleController;
import javafx.event.ActionEvent;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.concurrent.CountDownLatch;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

/**
 * This is the test class for FarkleController. It implements both JUnit 4 and Mockito 2.11.
 * It "mocks" the dependency object in the controller so I can verify it's calling the proper methods inside of the DiceUILogic class.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Mock private DiceUILogic game;

    @InjectMocks private FarkleController controller = new FarkleController();



@BeforeClass
static public void setUp() throws InterruptedException {



        final CountDownLatch latch = new CountDownLatch( 1 );

        // initializes JavaFX environment
        PlatformImpl.startup( () ->
        {
      /* No need to do anything here */
        } );

        latch.countDown();

        latch.await();
    }


    /**
     * This test determines if we're properly loading the rectangles into the rectangles array.
     * It verifies that the size of the rectangle array is correct.
     */
    @Test
    public void setRectanglesTest() {
        controller.setRectangleArray();
        // Verifies that our rectangle array is equal to the proper size.

        assertTrue(controller.getRectangles().size() == 6);

    }

    /**
     * This test determines whether or not the rollDice method used to capture ActionEvents is actually able to roll the dice.
     *It verifies that the rollCount variable in DiceUILogic is incremented (thus, it's calling all of the methods in the class).
     */
    @Test
    public void rollTheDiceTest() {
        controller.rollTheDiceButtonPushed(new ActionEvent());
        verify(game).setHand();
        verify(game).setRolled();
        verify(game).mapDice();

    }

    /**
     * This test verifies that the proper methods were called in our UILogic class by the bankPoints method in the controller.
     */
    @Test
    public void bankPointsTest(){
        controller.bankPointsButtonPushed(new ActionEvent());
        verify(game).setBankScore();
        verify(game).getBankScore();
        verify(game).getRoundScore();
        verify(game).setRollCount(0);
    }

}

