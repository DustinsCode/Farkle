package testing;

import farkleapp.Controller;
import farkleapp.Model;
import farklegame.Dice;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.atMost;

/**
 * This Runner enables us to inject mock dependencies into our objects.
 */
@RunWith(MockitoJUnitRunner.class)
public class FarkleModelTest extends Application {

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
                Application.launch(FarkleModelTest.class, new String[0]);

            }
        };
        t.setDaemon(true);
        t.start();
        System.out.printf("FX App thread started\n");
        Thread.sleep(500);
    }

    /**
     * Mocks our FarkleDiceLogic class so we can verify
     * the proper methods are being called from our test class.
     */
    @Mock
    private final farklegame.FarkleDiceLogic logic = new farklegame.FarkleDiceLogic();

    /**
     * This is the injection target for our mocked dependencies.
     */
    @InjectMocks
    private Model game = new Model(new Controller());

    /** arraylist of dice to test on. */
    private ArrayList<Dice> diceList = new ArrayList<>();

    /**
     * This method verifies that the constructor
     * is working for Model testing.
     */
    @Test
    public void constructorTest() {

        assertEquals("hand size", 6, game.getHand().size());
        assertEquals("Rectangles", 6, game.getrList().size());
    }

    /**
     * This test ensures that our mapDice method creates
     * rMap and make sure it's the correct size.
     */
    @Test
    public void mapDiceTest() {
        //TODO: Last assert is failing.  Expects 6, is actually 1???
        diceList = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            diceList.add(new Dice());
        }
        game.getLogic().rollHandStatus(diceList);
        game.mapDice();
        assertEquals("The dice array is not equal to what it should be.", 6, game.getHand().size());
        assertEquals("The rectangle array is not what it should be.", 6, game.getrList().size());
        assertEquals("Dice -> Rectangle map is not set properly", 6, game.getrMap().size());
    }

    /**
     * This method tests our setHand method in Model
     * and verifies that it's calling rollHandStatus in FarkleDiceLogic.
     */
    @Test
    public void setHandTest() {
        game.setHand();
        verify(logic, atMost(1)).rollHandStatus(game.getHand());
    }

    /**
     * This method tests our BankScore method and verifies
     * that we're calling this method from our logic instance.
     */
    @Test
    public void setBankScoreTest() {
        assertFalse(game.getHand().isEmpty());
        game.setBankScore();
        verify(logic, atMost(1)).bankPoints();
    }

    /**
     * This method tests setRollCount method in the Model.
     */
    @Test
    public void setRollCountTest() {
        game.setRollCount(3);
        assertTrue(game.getRollCount() == 3);
    }

    /**
     * This method tests setrLIst method in the Model.
     */
    @Test
    public void setrListTest() {
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();

        ArrayList<Rectangle> testrList = new ArrayList<>();
        testrList.add(r1);
        testrList.add(r2);
        game.setrList(testrList);

        assertTrue(game.getrList().equals(testrList));
    }

    /**
     * This method tests resetHand method in Model.
     */
    @Test
    public void resetHandTest() {
        //TODO: This test is broken
        diceList = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            diceList.add(new Dice());
        }
        logic.rollHandStatus(diceList);
        game.resetHand();
        List<Rectangle> rList = game.getrList();
        assertEquals(rList.get(0).getEffect(), null);
    }

    /**
     * This method tests getHandFill method in Model.
     */
    @Test
    public void getHandFillTest() {
        Rectangle r1 = new Rectangle();
        List<Rectangle> testList= Arrays.asList(r1, r1, r1, r1, r1, r1);
        game.getHandFill(testList);
    }

    /**
     * This method tests modHoldStatus method in Model.
     */
    @Test
    public void modHoldStatusTest() {
        //TODO: This test is also broken
        List<Rectangle> rList = game.getrList();
        game.modHoldStatus(rList.get(0));
    }

    /**
     * This method tests getBankScore in Model.
     */
    @Test
    public void getBankScoreTest() {
        assertEquals(game.getBankScore(), 0);
    }

    /**
     * This method tests getEstRoundScore in Model.
     */
    @Test
    public void getEstRoundScoreTest() {
        diceList = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            diceList.add(new Dice());
        }
        game.getLogic().rollHandStatus(diceList);
        assertTrue(game.getEstRoundScore() >= 0);
    }

    /**
     * This method tests wonGameStatus method in Model.
     */
    @Test
    public void wonGameStatusTest() {
        game.getLogic().setScore(3);
        assertEquals(false, game.wonGameStatus());
        game.getLogic().setScore(12000);
        //Alerts fails because of JavaFX thread not being present.
        //Confirmed that this works manually.
        assertEquals(true, game.wonGameStatus());
    }

    /**
     * This method tests getFarkleCount method in Model.
     */
    @Test
    public void getFarkleCountTest() {
        assertEquals(0, game.getFarkleCount());
    }
}

