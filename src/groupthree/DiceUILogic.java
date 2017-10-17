package groupthree;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiceUILogic {



    private static ArrayList<Image> diceImages = new ArrayList<>();
    private static HashMap<Integer,Image> mapImages = new HashMap<>();
    private ArrayList<Rectangle> rectangles = new ArrayList<>();
    private GameLogic logic = new GameLogic();

    ArrayList<Dice> hand = new ArrayList<>();
    private LinkedHashMap< Rectangle, Dice> rMap = new LinkedHashMap<>();


    /**
     * Constructor that adds all the Dice images to an arraylist and maps them to integers from 1-6,
     * as well as obtains the rectangle of javafx objects.
     */
    public DiceUILogic(FarkleController controller){

        diceImages.add(controller.d1);
        diceImages.add(controller.d2);
        diceImages.add(controller.d3);
        diceImages.add(controller.d4);
        diceImages.add(controller.d5);
        diceImages.add(controller.d6);

        for (int i = 0; i < diceImages.size(); i++){
            mapImages.put(i,diceImages.get(i));
        }

        rectangles = controller.getRectangles();
        for(int i = 0; i < 6; i++) {
            hand.add(new Dice());
            System.out.print(1);
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
    void mapDice (){

        for (int i = 0; i < hand.size(); i++){
            rMap.put(rectangles.get(i), hand.get(i) );

        }
            }

    /**
     * This method takes the hand of Dice objects and rolls the values using the game instance of the GameLogic class.
     */
    void setHand(){

        logic.turn(hand);


    }


    /**
     * This method sets the fill of our rectangles to the correct image based on if it's not held (if Dice are rolled
     * again, then it will need to update the pictures).
     * @param rect An ArrayList of Rectangles that will have their fill property updated.
     */
    void getHand(ArrayList<Rectangle> rect){
        for (int i = 0; i < hand.size(); i++){
            if ( !hand.get(i).isHeld() ){
               rect.get(i).setFill(new ImagePattern(mapImages.get(hand.get(i).val)));

            }

        }
    }

    void setHoldStatus(Rectangle r){
        for (Rectangle rect: rectangles) {
            if (rect.equals(r)){

                if (rMap.get(r).isHeld()){
                    rMap.get(r).releaseDice();
                    r.setEffect(null);

                } else {

                    rMap.get(r).holdDice();

                }
            }
        }
    }

            public void getHeld() {

    }



}
