package testing;



import groupthree.DiceUILogic;
import groupthree.GameLogic;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.atMost;

/**
 * This Runner enables us to inject mock dependencies into our objects.
 */
@RunWith(MockitoJUnitRunner.class)
public class DiceUILogicTest extends Application {

    /**
     * Extends javafx.application.Application and implements
     * start so we can use a new thread javafx application.
     * @param primaryStage The primary application JavaFX stage.
     */
    @Override
    public void start(final Stage primaryStage) {
        //nope
    }

    /**
     * This initializes a javaFX application in the
     * background so we can test the usage of images.
     *
     * @throws InterruptedException If the thread is interrupted.
     */
    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        // Initialise Java FX

        System.out.printf("About to launch FX App\n");
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(DiceUILogicTest.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
        System.out.printf("FX App thread started\n");
        Thread.sleep(500);


    }


    /**
     * Mocks our GameLogic class so we can verify
     * the proper methods are being called from our test class.
     */
    @Mock
    private final GameLogic logic = new GameLogic();

    /**
     * This is the injection target for our mocked dependencies.
     */
    @InjectMocks
    private DiceUILogic game = new DiceUILogic(new FakeController());

    /**
     * This method verifies that the constructor
     * is working for DiceUILogic testing.
     */
    @Test
    public void constructorTest() {

        assertEquals("hand size", 6, game.getHand().size());
        assertEquals("Rectangles", 6, game.rectangles.size());
    }

    /**
     * This test ensures that our mapDice method creates
     * rMap and make sure it's the correct size.
     */
    @Test
    public void mapDiceTest() {
        game.mapDice();
        assertEquals("The dice array is not equal to what it should be.", 6, game.getHand().size());
        assertEquals("The rectangle array is not what it should be.", 6, game.rectangles.size());
        assertEquals("Dice -> Rectangle map is not set properly", 6, game.rMap.size());


    }

    /**
     * This method tests our setHand method in DiceUILogic
     * and verifies that it's calling rollHandStatus in GameLogic.
     */
    @Test
    public void setHandTest() {
        game.setHand();
        verify(logic, atMost(1)).rollHandStatus(game.getHand());

    }

    /**
     * This class tests our BankScore method and verifies
     * that we're calling this method from our logic instance.
     */
    @Test
    public void setBankScoreTest() {

        assertFalse(game.getHand().isEmpty());
        game.setBankScore();
        verify(logic, atMost(1)).bankPoints();
    }


}

