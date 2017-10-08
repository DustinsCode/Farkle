package groupthree;

public class gameLogic {


    /**
     * Turn is at the end of the turn, when you've decided to hold or not, and when it rolls the dice.
     * The button that ends turn should be calling this.
     */
    public void turn(ArrayList hand()) {

        //loops through hand with temp var j
        for(dice j: hand){
            // If the dice isn't held, roll it
            if (!(j.isheld()){
                j.roll();
            }
        }


    }

    /**
     * Scoring determines points based off of which dice are held.
     * @param hand is the hand of dice
     */
    public int scoring(ArrayList hand()){


    }


    /**
     * bankPoints should end turn
     */
    public void bankPoints(){

    }










    /**
     * @Param d = dice
     * @Param hand is the list of dices, including both held and active dice.
     */
    public static void main() {
	    ArrayList<dice> hand = new ArrayList<dice>;
	    // Adding 6 dice to the arraylist hand. Might need to do this manually to sync it up with buttons.
	    for(int i = 0; i < 6; i++) {
            hand.add(new dice());
        }

    }
}
