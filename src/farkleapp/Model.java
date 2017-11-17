package farkleapp;

import farklegame.Dice;
import farklegame.FarkleDiceLogic;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.*;

/**
 * The Model class interfaces with Controller and
 * FarkleDiceLogic to map the numerical logic done on our dice to
 * a visual representation useable by our JavaFX controller.
 */
public class Model {
    /**
     * The array list of images representing dice faces from 1-6.
     */
    private static final List<Image> DICE_IMAGES = new ArrayList<>(6);
    /**
     * The hash map that contains our Images and integers from 1-6.
     */
    private static final Map<Integer, Image> IMAGE_HASH_MAP = new HashMap<>(6);
    /**
     * ArrayList of JavaFX rList.
     */
    private List<Rectangle> rList = new ArrayList<>(6);
    /**
     * This method gets the list of rectangles.
     * @return The list of current JavaFX rectangles.
     */
    public List<Rectangle> getrList() {
        return this.rList;
    }
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
     *
     */
    public final LinkedHashMap< Rectangle, Dice> rMap = new LinkedHashMap<>();

    private int rollCount = 0;

    final Image d1 = new Image("d1.png");
    final Image d2 = new Image("d2.png");
    final Image d3 = new Image("d3.png");
    final Image d4 = new Image("d4.png");
    final Image d5 = new Image("d5.png");
    final Image d6 = new Image("d6.png");


    /**
     * Constructor that adds all the Dice images to an arrayList and maps them to integers from 1-6,
     * as well as obtains the rectangle of javafx objects.
     */
     public Model(FarkleControllerInterface controller){

        DICE_IMAGES.add(d1);
        DICE_IMAGES.add(d2);
        DICE_IMAGES.add(d3);
        DICE_IMAGES.add(d4);
        DICE_IMAGES.add(d5);
        DICE_IMAGES.add(d6);

        for (int i = 0; i < DICE_IMAGES.size(); i++){
            IMAGE_HASH_MAP.put(i + 1 , DICE_IMAGES.get(i));
        }

        rList = controller.getRectangles();

        for(int i = 0; i < 6; i++) {
            hand.add(new Dice());
        }
     }


    /**
     * This method simultaneously sets the fill for each die within the ArrayList to a specific image. It
     * also checks if any of the Dice in the current hand are held, if so, it will skip over animating these rList.
     * @param dnum the Image being passed that the rList will be set to.
     */
    public void setRectFill(Image dnum) {
        for (int i = 0; i < hand.size(); i++) {

            // Checks if the dice is held before setting the new fill property.
            if (!hand.get(i).isHeld()) {
                rList.get(i).setFill(new ImagePattern(dnum));
            }
        }
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
     * This method takes the hand of Dice objects and rolls the values using the game instance of the FarkleDiceLogic class.
     */
    public void setHand(){
        logic.rollHandStatus(hand);
    }


    /**
     * This method sets the fill of our rList to the correct image based on if it's not held (if Dice are rolled
     * again, then it will need to update the pictures).
     * @param rect An ArrayList of Rectangles that will have their fill property updated.
     */

    public void getHandFill(final ArrayList<Rectangle> rect) {
        for (int i = 0; i < hand.size(); i++) {
            if (!hand.get(i).isHeld()) {
               rect.get(i).setFill(new ImagePattern(IMAGE_HASH_MAP.get(hand.get(i).getVal())));

            }
        }
    }

    /**
     * This method determines what state the rectangle that was clicked was in.
     * @param r the Rectangle being passed to check.
     */
    public void setHoldStatus(final Rectangle r) {

        int depth = 70;

        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.YELLOW);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);

        for (Rectangle rect: rList) {



            if (rect.equals(r)) {


                // Sets the glow and attributes of the dice corresponding with it's status when clicked.
                if ( rMap.get(r).isHeld() && !rMap.get(r).isInactive() ) {
                    rMap.get(r).releaseDice();
                    r.setEffect(null);

                } else if (!rMap.get(r).isHeld() && !rMap.get(r).isInactive() ) {
                    r.setEffect(borderGlow);
                    rMap.get(r).holdDice();


                } else if (rMap.get(r).isHeld() && rMap.get(r).isInactive()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(FarkleApp.getPrimaryStage());
                    alert.setTitle("Action Not Allowed");
                    alert.setHeaderText("Unable to Release Dice");
                    alert.setContentText("You are not allowed to release a dice after rolling!");
                    alert.show();
                }
            }

        }
    }

    /**
     * This method checks to see if we have rolled or not yet for the first time since the game start.
     * If it has, then you are not allowed to un-hold a dice, and it prompts with an alert.
     */
   public void checkRolled() {

        if (getRollCount() < 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(FarkleApp.getPrimaryStage());
            alert.setTitle("Action Not Allowed");
            alert.setHeaderText("Must Roll Dice");
            alert.setContentText("You are not allowed to select a dice before rolling. Please roll first.");
            alert.show();
        }
    }

    /**
     * This method checks our logic to see if we've Farkled.
     * @return the boolean representing whether or not we Farkle.
     */
    boolean isFarkle() {
        return logic.isFarkle(hand);
    }

    /**
     * This method keeps track of how many times we've rolled the hand in a round.
     */
    public void setRolled() {
        rollCount++;
    }

    /**
     * This method calls bankPoints in our logic instance of the FarkleDiceLogic class.
     */
    public void setBankScore() {
        logic.bankPoints();
    }

    /**
     * This method calls a logic method that tallies up and returns the current bank score.
     * @return the Integer value representing the bank score.
     */
   public int getBankScore() {
        return logic.getBankedPoints();
    }

    /**
     * This method calls logic.resetRound and sets all of the rList' effects to null (not held or clicked anymore)
     */
    void resetHand() {
        logic.resetRound(hand);
        for (Rectangle rectangle : rList) {
            rectangle.setEffect(null);
        }
    }

    /**
     * This method tallies up and returns the current round score.
     * @return the Integer value representing the current score of your hand.
     */
    public int getRoundScore() {
        logic.tallyRoundPoints(hand);
        return logic.getRoundPoints();
    }

    /**
     * This is a pass-through for logic.wonGameStatus that determines if we've won the game.
     * @return yes or no if we've won the game.
     */
    boolean wonGameStatus() {
        return logic.wonGameStatus();
    }

    /**
     * This accesses the number of farkles in our current round.
     * @return The number of farkles in the current round.
     */
    @SuppressWarnings("unused")
    int getFarkleCount() {
        return logic.getFarkle();
    }

    /**
     * Method for setting the variable that keeps track of how many rolls we've performed.
     * @param count the variable that will pass on how many rolls we have.
     */
    public void setRollCount(final int count) {
        rollCount = count;
    }

    /**
     * Returns the rollCount variable;
     * @return the int count of our rolls.
     */
    int getRollCount() {
        return rollCount;
    }

    /**
     * Gets hand.
     * @return current array list representing the hand of dice
     */
    public ArrayList<Dice> getHand() {
        return hand;
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
}


