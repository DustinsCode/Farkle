package groupthree;

/**
 *
 */
public class Dice {

    /** value of the rectangles */
    int val;

    /** is the rectangles held? */
    private boolean hold;

    /*************************************
     * Obtains current value of rectangles
     *
     * @return rectangles value
     *************************************/
    int getVal(){
        return val;
    }

    /**************************************
     * setDice changes the value of the rectangles to a new value
     *
     * @param diceVal is the value that the rectangles is to be set as
     **************************************/
    public void setDice(int diceVal){
        val = diceVal;
    }

    /**************************************
     * Hold rectangles sets rectangles being held to TRUE
     **************************************/
    public void holdDice(){
        hold = true;
    }

    /**************************************
     *Release rectangles sets rectangles being held to FALSE
     **************************************/
    public void releaseDice(){
        hold = false;
    }

    /**
     * Tells if the rectangles is currently held
     *
     * @return hold value
     */
    boolean isHeld(){
        return hold;
    }

    /**********************************
     * Rolls the rectangles
     * @return new value of the rectangles
     **********************************/
    public int roll(){

        if(!isHeld()) {
            val = (int) (Math.random() * 6 + 1);
        }
        return val;
    }

}
