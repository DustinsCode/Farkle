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

    /**
     * Tells if the dice is currently held
     *
     * @return hold value
     */
    public boolean isHeld(){
        return hold;
    }

    /** Setters */
    public void setVal(int val){
        this.val = val;
    }

    public void setHold(boolean toHold){
        hold = toHold;
    }

    /**
     * Holds or un-holds the dice
     */
    public void hold(){
        if(hold){
            hold = false;
        }else{
            hold = true;
        }
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

    /**
     * Checks if value is 1 or 5 to be held
     *
     * @return true or false
     */
    public boolean validMove(){
        if(val == 1 || val == 5){
            return true;
        }else{
            return false;
        }
    }


}
