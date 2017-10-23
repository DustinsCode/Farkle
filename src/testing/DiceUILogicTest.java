package testing;


import com.sun.javafx.application.PlatformImpl;
import groupthree.DiceUILogic;
import javafx.scene.image.Image;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.concurrent.CountDownLatch;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DiceUILogicTest {


    public final Image d1 = new Image("d1.png");
    public final Image d2 = new Image("d2.png");
    public final Image d3 = new Image("d3.png");
    public final Image d4 = new Image("d4.png");
    public final Image d5 = new Image("d5.png");
    public final Image d6 = new Image("d6.png");

   // @Spy private final GameLogic logic = new GameLogic();
   // @Mock private final ArrayList<Dice> hand = new ArrayList<>();



    static DiceUILogic game = new DiceUILogic(new FakeController());

   //@InjectMocks DiceUILogic game = new DiceUILogic(new FarkleController());

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


    @Test
    public void constructorTest(){

        assertEquals("hand size", 6, game.getHand().size());
        assertEquals("Rectangles",6 , game.rectangles.size());
    }

  @Test
    public void mapDiceTest() {

  }

}
