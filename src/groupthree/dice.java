package groupthree;

/**
 *
 */
public class dice {

    /** value of the dice */
    public int val;

    /** is the dice held? */
    public boolean hold;

    /*************************************
     * Obtains current value of dice
     *
     * @return dice value
     *************************************/
    public int getVal(){
        return val;
    }

    /**************************************
     * setDice changes the value of the dice to a new value
     *
     * @param diceVal is the value that the dice is to be set as
     **************************************/
    public void setDice(int diceVal){
        val = diceVal;
    }

    /**************************************
     * Hold dice sets dice being held to TRUE
     **************************************/
    public void holdDice(){
        hold = true;
    }

    /**************************************
     *Release dice sets dice being held to FALSE
     **************************************/
    public void releaseDice(){
        hold = false;
    }

    /**
     * Tells if the dice is currently held
     *
     * @return hold value
     */
    public boolean isHeld(){
        return hold;
    }

    /**********************************
     * Rolls the dice
     * @return new value of the dice
     **********************************/
    public int roll(){

        if(!isHeld()) {
            val = (int) (Math.random() * 6 + 1);
        }
        return val;
    }

}
