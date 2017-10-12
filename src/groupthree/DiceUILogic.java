package groupthree;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

public class DiceUILogic {

    static ArrayList<dice> hand = new ArrayList<>();
    static ArrayList<Image> diceImages = new ArrayList<>();
    static HashMap<Integer,Image> mapImages = new HashMap<>();
    static gameLogic game = new gameLogic();

    /**
     * @Override Default constructor override.
     */
    public DiceUILogic(){

        diceImages.add(FarkleController.d1);
        diceImages.add(FarkleController.d2);
        diceImages.add(FarkleController.d3);
        diceImages.add(FarkleController.d4);
        diceImages.add(FarkleController.d5);
        diceImages.add(FarkleController.d6);

        for (int i = 1; i <= 6; i++){
            mapImages.put(i,diceImages.get(i));
        }
    }


    /**
     * This method sets the hand to 6 new dice and rolls the values using gameLogic.turn();
     */
    public void setHand(){
        for(int i = 0; i < 6; i++) {
            hand.add(new dice());
        }
        game.turn(hand);


    }

    /**
     * This method sets the fill of our rectangles to the correct image based on if it's not held (if dice are rolled
     * again, then it will need to update the pictures).
     * @param rect An ArrayList of Rectangles that will have their fill property updated.
     */
    public void getHand(ArrayList<Rectangle> rect){
        for (int i = 0; i < hand.size(); i++){
            if (hand.get(i).isHeld() == false){
               rect.get(i).setFill(new ImagePattern(mapImages.get(hand.get(i).val)));

            }
        }
    }



}
