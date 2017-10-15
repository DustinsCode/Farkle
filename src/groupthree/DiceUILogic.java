package groupthree;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

public class DiceUILogic {
    Image d1 = new Image("d1.png");
    Image d2 = new Image("d2.png");
    Image d3 = new Image("d3.png");
    Image d4 = new Image("d4.png");
    Image d5 = new Image("d5.png");
    Image d6 = new Image("d6.png");

    static ArrayList<Dice> hand = new ArrayList<>();
    static ArrayList<Image> diceImages = new ArrayList<>();
    static HashMap<Integer,Image> mapImages = new HashMap<>();
    static GameLogic game = new GameLogic();

    /**
     * Default constructor override that adds all the Dice images to an arraylist and maps them to integers from 1-6.
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
     * This method simultaneously sets the fill for each die within the ArrayList to a specific image. It
     * also checks if any of the Dice in the current hand are held, if so, it will skip over animating these rectangles.
     * @param dnum the Image being passed that the rectangles will be set to.
     */
    public void setRectFill(Image dnum) {
        for (int i = 0; i < hand.size(); i++) {

            // Checks if the dice is held before setting the new fill property.
            if( !hand.get(i).isHeld() ){
                FarkleController.rectangles.get(i).setFill(new ImagePattern(dnum));

            }}}


    /**
     * This method sets the hand to 6 new Dice and rolls the values using the game instance of the GameLogic class.
     */
    public void setHand(){
        for(int i = 0; i < 6; i++) {
            hand.add(new Dice());
        }
        game.turn(hand);


    }

    /**
     * This method sets the fill of our rectangles to the correct image based on if it's not held (if Dice are rolled
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

    public void setHold(){

    }

    public void getHeld() {

    }



}
