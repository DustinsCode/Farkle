package farkleapp;

import farklegame.Dice;
import farklegame.FarkleDiceLogic;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.*; // We use every class inside of util.



/**
 * The Model class interfaces with Controller and
 * FarkleDiceLogic to map the numerical logic done on our dice to
 * a visual representation usable by our JavaFX controller.
 */
public class Model {

    /**
     * This contains our dice images in Java.
     */
    private DiceImages images = new DiceImages();
    /**
     * The array list of images representing dice faces from 1-6.
     */
    private static final List<Image> DICE_IMAGES = new ArrayList<>(6);
    /**
     * The hash map that contains our DiceImages and integers from 1-6.
     */
    private static final Map<Integer, Image> IMAGE_HASH_MAP = new HashMap<>(6);
    /**
     * ArrayList of JavaFX rList.
     */
    private List<Rectangle> rList = new ArrayList<>(6);

    /**
     * This is an instance of our FarkleDiceLogic class so we can run
     * computations in the data.
     */
    private final FarkleDiceLogic logic = new FarkleDiceLogic();
    /**
     * List of Dice representing our hand.
     */
    private final ArrayList<Dice> hand = new ArrayList<>(6);

    /**
     * HashMap of Rectangle objects and their appropriate Dice objects.
     */
    private final LinkedHashMap<Rectangle, Dice> rMap = new LinkedHashMap<>();

    /**
     * The variable the keeps track of how many rolls we've performed.
     */
    private int rollCount = 0;

    /**
     * The alerts class of our application (visual).
     */
    private GameAlerts alerts = new GameAlerts();

    /**
     * Constructor that adds all the Dice images
     * to an arrayList and maps them to integers from 1-6,
     * as well as obtains the rectangle of javafx objects.
     * @param  controller that will be communicating with the model.
     */
     public Model(final FarkleControllerInterface controller) {

        DICE_IMAGES.add(images.getD1());
        DICE_IMAGES.add(images.getD2());
        DICE_IMAGES.add(images.getD3());
        DICE_IMAGES.add(images.getD4());
        DICE_IMAGES.add(images.getD5());
        DICE_IMAGES.add(images.getD6());

        for (int i = 0; i < DICE_IMAGES.size(); i++) {
            IMAGE_HASH_MAP.put(i + 1, DICE_IMAGES.get(i));
        }

        rList = controller.getRectangles();

        for (int i = 0; i < 6; i++) {
            hand.add(new Dice());
        }
     }
    /**
     * Just defining a default constructor for use by child classes.
     */
    public Model() {

     }
    /**
     * This method simultaneously sets the fill for each
     * die within the ArrayList to a specific image. It
     * also checks if any of the Dice in the current hand
     * are held, if so, it will skip over animating these rList.
     * @param dnum the Image being passed that the rList will be set to.
     */
    public void setRectFill(final Image dnum) {
        for (int i = 0; i < hand.size(); i++) {

            // Checks if the dice is held before setting the new fill property.
            if (!hand.get(i).isHeld()) {
                rList.get(i).setFill(new ImagePattern(dnum));
            }
        }
    }

    /**
     * Animates the dice with our images in the view.
     */
    public void animateView() {
        Timeline diceAnimate = new Timeline(

                new KeyFrame(Duration.ZERO,
                        ae -> setRectFill(images.getD1())),
                new KeyFrame(Duration.millis(111),
                        ae -> setRectFill(images.getD2())),
                new KeyFrame(Duration.millis(222),
                        ae -> setRectFill(images.getD3())),
                new KeyFrame(Duration.millis(333),
                        ae -> setRectFill(images.getD4())),
                new KeyFrame(Duration.millis(444),
                        ae -> setRectFill(images.getD5())),
                new KeyFrame(Duration.millis(555),
                        ae -> setRectFill(images.getD6())),
                new KeyFrame(Duration.millis(777),
                        // Calls getHand from our model
                        // instance and sets the fills.
                        ae -> getHandFill(rList))
        );
        diceAnimate.setCycleCount(1);
        diceAnimate.play();
    }
    /**
     * This maps the current hand of rList to dice objects with values.
     */
    public void mapDice() {

        for (int i = 0; i < hand.size(); i++) {
             rMap.put(rList.get(i), hand.get(i));
        }
    }
    /**
     * This method takes the hand of Dice objects and rolls
     * the values using the game instance of the FarkleDiceLogic class.
     */
    public void setHand() {
        logic.rollHandStatus(hand);
    }
    /**
     * This method sets the fill of our rList to the correct
     * image based on if it's not held (if Dice are rolled
     * again, then it will need to update the pictures).
     * @param rect An ArrayList of Rectangles that will have
     *            their fill property updated.
     */
    public void getHandFill(final List<Rectangle> rect) {
        for (int i = 0; i < hand.size(); i++) {
            if (!hand.get(i).isHeld()) {

               rect.get(i).setFill(
                       new ImagePattern(
                               IMAGE_HASH_MAP.get(hand.get(i).getVal())
                       )
               );

            }
        }
    }
    /**
     * This method determines what state the rectangle that was clicked was in.
     * @param r the Rectangle being passed to check the hold status.
     */
    public void modHoldStatus(final Rectangle r) {

        int depth = 70;

        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.YELLOW);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);

        for (Rectangle rect: rList) {
            if (rect.equals(r)) {

                // Sets the glow and attributes of the dice
                // corresponding with it's status when clicked.
                if (rMap.get(r).isHeld() && !rMap.get(r).isInactive()) {
                    rMap.get(r).releaseDice();
                    r.setEffect(null);

                } else if (
                        !rMap.get(r).isHeld() && !rMap.get(r).isInactive()
                        ) {
                                r.setEffect(borderGlow);
                                rMap.get(r).holdDice();

                } else if (rMap.get(r).isHeld() && rMap.get(r).isInactive()) {
                    alerts.afterRollTriedRelease();
                }
            }

        }
    }
    /**
     * This method checks to see if we have rolled or
     * not yet for the first time since the game start.
     * If it has, then you are not allowed to
     * un-hold a dice, and it prompts with an alert.
     */
   public void checkRolled() {
        if (getRollCount() < 1) {
            alerts.notRolled();
        }
    }

    /**
     * This method checks our logic to see if we've Farkled.
     * If we do, it alerts view.
     * @param list is the hand being checked for farkle.
     * @return the boolean representing whether or not we Farkle.
     */
   public boolean isFarkle(final List<Rectangle> list) {

      if (logic.isFarkle(hand)) {
          for (Rectangle rect: list) {
              rect.setEffect(null);
          }
          alerts.farkleAlert();
          return true;

      } else {
          return false;
      }
    }
    /**
     * This method keeps track of how many times
     * we've rolled the hand in a round.
     */
    public void setRolled() {
        rollCount++;
    }
    /**
     * This method calls bankPoints in our logic
     * instance of the FarkleDiceLogic class.
     */
    public void setBankScore() {
        logic.bankPoints();
    }
    /**
     * This method calls a logic method that tallies
     * up and returns the current bank score.
     * @return the Integer value representing the bank score.
     */
   public int getBankScore() {
        return logic.getBankedPoints();
    }
    /**
     * This method calls logic.resetRound and sets all of the
     * rList' effects to null (not held or clicked anymore).
     */
    public void resetHand() {
        logic.resetRound(hand);
        setRectGlow();
    }

    /**
     * This method sets rectangle effects to null.
     */
    private void setRectGlow() {

        for (Rectangle rectangle : rList) {
            if (rectangle != null) {
                rectangle.setEffect(null);
            }
        }

    }
    /**
     * This method tallies up and returns the current round score.
     * @return the Integer value representing the current score of your hand.
     */
    public int getRoundScore() {
        logic.finalTallyRoundPoints(hand);
        return logic.getRoundPoints();
    }

    /**
     * Gets the amount of points you have with held dice.
     * @return the estimated round score.
     */
    public int getEstRoundScore() {
        logic.tallyRoundPoints(hand);
        return logic.getEstRoundPoints();
    }

    /**
     * This is a pass-through for logic.wonGameStatus
     * that determines if we've won the game.
     * @return yes or no if we've won the game.
     */
    public boolean wonGameStatus() {
        if (logic.wonGameStatus()) {
            alerts.wonGame();
            return true;
        } else {
            return false;
        }
    }
    /**
     * This accesses the number of farkles in our current round.
     * @return The number of farkles in the current round.
     */
    public int getFarkleCount() {
        return logic.getFarkle();
    }
    /**
     * Method for setting the variable that keeps
     * track of how many rolls we've performed.
     * @param count the variable that will pass on how many rolls we have.
     */
    public void setRollCount(final int count) {
        rollCount = count;
    }
    /**
     * Returns the rollCount variable.
     * @return The count of our rolls.
     */
    public int getRollCount() {
        return this.rollCount;
    }
    /**
     * This gives the mapping of the current dice and rectangles.
     * @return Linked Hash Map that contains the above.
     */
    public LinkedHashMap<Rectangle, Dice> getrMap() {
        return this.rMap;
    }
    /**
     * Gets hand.
     * @return current array list representing the hand of dice
     */
    public ArrayList<Dice> getHand() {
        return hand;
    }
    /**
     * This method gets the list of rectangles.
     * @return The list of current JavaFX rectangles.
     */
    public List<Rectangle> getrList() {
        return this.rList;
    }
    /**

     * This sets all of the rectangles in A source
     * View to the rectangles in RList.
     * @param rList the array list of javafx
     *             rectangles for the model to manipulate.
     */
    public void setrList(final ArrayList<Rectangle> rList) {
        this.rList = rList;
    }

    /**
     * Returns the game's reference to FarkleDiceLogic.
     * @return logic
     */
    public FarkleDiceLogic getLogic() { return this.logic; }

}


