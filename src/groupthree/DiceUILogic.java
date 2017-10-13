package groupthree;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

public class DiceUILogic {
    Image d1 = new Image("d1.png");
    Image d2 = new Image("d2.png");
    Image d3 = new Image("d1.png");
    Image d4 = new Image("d1.png");
    Image d5 = new Image("d1.png");
    Image d6 = new Image("d1.png");

    static ArrayList<dice> hand = new ArrayList<>();
    static ArrayList<Image> diceImages = new ArrayList<>();
    static HashMap<Integer,Image> mapImages = new HashMap<>();
    static gameLogic game = new gameLogic();

    /**
     * @Override Default constructor override that adds all the dice images to an arraylist and maps them to integers from 1-6.
     */
    public DiceUILogic(){

        diceImages.add(d1);
        diceImages.add(d2);
        diceImages.add(d3);
        diceImages.add(d4);
        diceImages.add(d5);
        diceImages.add(d6);

        for (int i = 0; i < diceImages.size(); i++){
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
            if ( !hand.get(i).isHeld() ){
               rect.get(i).setFill(new ImagePattern(mapImages.get(hand.get(i).val)));

            }
        }
    }



}
