package groupthree;


import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class DiceUILogic {



    private static final ArrayList<Image> diceImages = new ArrayList<>();
    private static final HashMap<Integer,Image> mapImages = new HashMap<>();
    private ArrayList<Rectangle> rectangles = new ArrayList<>();
    private final GameLogic logic = new GameLogic();

    private final ArrayList<Dice> hand = new ArrayList<>();
    private final LinkedHashMap< Rectangle, Dice> rMap = new LinkedHashMap<>();
    private int rollCount = 0;
    private boolean isFarkles = false;

    final Image d1 = new Image("d1.png");
    final Image d2 = new Image("d2.png");
    final Image d3 = new Image("d3.png");
    final Image d4 = new Image("d4.png");
    final Image d5 = new Image("d5.png");
    final Image d6 = new Image("d6.png");


    /**
     * Constructor that adds all the Dice images to an arraylist and maps them to integers from 1-6,
     * as well as obtains the rectangle of javafx objects.
     */
     DiceUILogic(FarkleController controller){

        diceImages.add(d1);
        diceImages.add(d2);
        diceImages.add(d3);
        diceImages.add(d4);
        diceImages.add(d5);
        diceImages.add(d6);

        for (int i = 0; i < diceImages.size(); i++){
            mapImages.put(i + 1 ,diceImages.get(i));
        }

        rectangles = controller.getRectangles();
        for(int i = 0; i < 6; i++) {
            hand.add(new Dice());
        }


    }



    /**
     * This method simultaneously sets the fill for each die within the ArrayList to a specific image. It
     * also checks if any of the Dice in the current hand are held, if so, it will skip over animating these rectangles.
     * @param dnum the Image being passed that the rectangles will be set to.
     */
    void setRectFill(Image dnum) {
        for (int i = 0; i < hand.size(); i++) {

            // Checks if the dice is held before setting the new fill property.
            if( !hand.get(i).isHeld() ){
                rectangles.get(i).setFill(new ImagePattern(dnum));

            }}}

    /**
     * This maps the current hand of rectangles to dice objects with values.
     */
    public void mapDice (){

        for (int i = 0; i < hand.size(); i++){
            rMap.put(rectangles.get(i), hand.get(i) );

        }
            }

    /**
     * This method takes the hand of Dice objects and rolls the values using the game instance of the GameLogic class.
     */
    public void setHand(){

        logic.rollHandStatus(hand);

    }


    /**
     * This method sets the fill of our rectangles to the correct image based on if it's not held (if Dice are rolled
     * again, then it will need to update the pictures).
     * @param rect An ArrayList of Rectangles that will have their fill property updated.
     */
    void getHand(ArrayList<Rectangle> rect){
        for (int i = 0; i < hand.size(); i++){
            if ( !hand.get(i).isHeld() ){
               rect.get(i).setFill(new ImagePattern(mapImages.get(hand.get(i).getVal())));

            }


        }
    }

    void setHoldStatus(Rectangle r) {

        int depth = 70;
        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.YELLOW);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);

        for (Rectangle rect: rectangles) {


            if (rect.equals(r)){


                // Sets the glow and attributes of the dice corresponding with it's status when clicked.
                if ( rMap.get(r).isHeld() && !rMap.get(r).isInactive() ){
                    rMap.get(r).releaseDice();
                    r.setEffect(null);

                } else if (!rMap.get(r).isHeld() && !rMap.get(r).isInactive() ) {
                    r.setEffect(borderGlow);
                    rMap.get(r).holdDice();


                } else if (rMap.get(r).isHeld() && rMap.get(r).isInactive()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(MainUI.getPrimaryStage());
                    alert.setTitle("Action Not Allowed");
                    alert.setHeaderText("Unable to Release Dice");
                    alert.setContentText("You are not allowed to release a dice after rolling!");
                    alert.show();
                }
            }

    }
    }


    void checkRolled() {

        if (rollCount < 1 ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(MainUI.getPrimaryStage());
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
        return logic.isFarkle();
    }

    /**
     * This method keeps track of how many times we've rolled the hand in a round.
     */
    public void setRolled() {
        rollCount++;
    }


    public void setBankScore() {
        logic.bankPoints(hand);

    }

    /**
     * This method calls a logic method that tallies up and returns the current bank score.
     * @return the Integer value representing the bank score.
     */
   public int getBankScore() {
        return logic.getBankedPoints();
    }

    void resetHand() {
        logic.resetRound(hand);
        for (Rectangle rectangle : rectangles) {
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

    boolean wonGameStatus() {
        return logic.wonGameStatus();
    }

    int getFarkleCount() {
        return logic.farkleCounter;
    }

    public void setRollCount(int count) {
        rollCount = count;
    }
    int getRollCount() {
        return rollCount;
    }
}


