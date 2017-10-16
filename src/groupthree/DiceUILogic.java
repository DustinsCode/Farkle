package groupthree;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiceUILogic {
    Image d1 = new Image("d1.png");
    Image d2 = new Image("d2.png");
    Image d3 = new Image("d3.png");
    Image d4 = new Image("d4.png");
    Image d5 = new Image("d5.png");
    Image d6 = new Image("d6.png");


    private static ArrayList<Image> diceImages = new ArrayList<>();
    private static HashMap<Integer,Image> mapImages = new HashMap<>();
    private GameLogic game = new GameLogic();

    private ArrayList<Dice> hand = new ArrayList<>();
    private LinkedHashMap< Rectangle, Dice> rMap = new LinkedHashMap<>();


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
    void setRectFill(Image dnum) {
        for (int i = 0; i < hand.size(); i++) {

            // Checks if the dice is held before setting the new fill property.
            if( !hand.get(i).isHeld() ){
                FarkleController.rectangles.get(i).setFill(new ImagePattern(dnum));

            }}}

    void mapDice (){
        for (int i = 0; i < hand.size(); i++){
            rMap.put(FarkleController.rectangles.get(i), hand.get(i) );

        }
            }

    /**
     * This method sets the hand to 6 new Dice and rolls the values using the game instance of the GameLogic class.
     */
    void setHand(){
        for(int i = 0; i < FarkleController.rectangles.size(); i++) {
            hand.add(new Dice());
        }

        game.turn(hand);


    }

    /**
     * This method sets the fill of our rectangles to the correct image based on if it's not held (if Dice are rolled
     * again, then it will need to update the pictures).
     * @param rect An ArrayList of Rectangles that will have their fill property updated.
     */
    void getHand(ArrayList<Rectangle> rect){
        for (int i = 0; i < hand.size(); i++){
            if ( !hand.get(i).isHeld() ){
                // Need a way to keep a reference of which rectangles are set to which numbers.
               rect.get(i).setFill(new ImagePattern(mapImages.get(hand.get(i).val)));

            }

        }
    }

    void setHoldStatus(Rectangle r){
        for (Rectangle rect: FarkleController.rectangles) {
            if (rect.equals(r)){
                System.out.println(r.getId());
                if (rMap.get(r).isHeld()){
                    rMap.get(r).releaseDice();
                    r.setEffect(null);
                    System.out.println(rMap.get(r).isHeld());
                } else {
                    rMap.get(r).holdDice();
                    System.out.println(rMap.get(r).isHeld());
                }
            }
        }
    }

    public void getHeld() {

    }



}
