package groupthree;

/**
 *
 */
public class dice {

    /** value of the dice */
    public int val;

    /** is the dice held? */
    public boolean hold;


    public boolean isHeld(){
        if(hold){
            return true;
        }else{
            return false;
        }
    }

    /*************************************
     * Obtains current value of dice
     *
     * @return dice value
     *************************************/
    public int getVal(){
        return val;
    }

    /**********************************
     * Rolls the dice
     * @return new value of the dice
     **********************************/
    public int roll(){

        val = (int)(Math.random() * 6 + 1);

        return val;
    }
}
