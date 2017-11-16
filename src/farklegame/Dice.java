package groupthree;

/**
 * The dice class represents what we use to keep track of "Dice". It has
 * certain attributes that allow us to replicate a dice numerically.
 */
public class Dice {

    /** value of the rectangles. */
    private int val;

    /** is the rectangles held? */
    private boolean hold;


    /** Was the dice held in the previous turn?*/
    private boolean inactive;

    /**
     * Default Constructor.
     */
    public Dice() {
        val = 1;
        hold = false;
        inactive = false;
    }
    /*************************************
     * Obtains current value of rectangles.
     *
     * @return dice value
     *************************************/
    public int getVal() {
        return val;
    }

    /**************************************
     * setDice changes the value of the rectangles to a new value.
     *
     * @param diceVal is the value that the dice is to be set as
     **************************************/
    public void setDice(final int diceVal) {
        if (diceVal >= 1 && diceVal <= 6) {
            val = diceVal;
        } else {
            val = roll();
        }
    }

    /**************************************
     * Hold rectangles sets rectangles being held to TRUE.
     **************************************/
    public void holdDice() {
        hold = true;
    }

    /**************************************
     *Release rectangles sets rectangles being held to FALSE.
     **************************************/
    public void releaseDice() {
        hold = false;
    }

    /**
     * Sets the dice to be active.
     */
    public void setActive() {
        inactive = false;
    }

    /**
     * Sets the dice to be inactive.
     */
    public void setInactive() {
        inactive = true;
    }

    /**
     * Is the dice inactive?
     * @return boolean value of inactive
     */
    public boolean isInactive() {
        return inactive;
    }

    /**
     * Tells if the rectangles is currently held.
     *
     * @return hold value
     */
    public boolean isHeld() {
        return hold;
    }

    /**********************************
     * Rolls the rectangles.
     * @return new value of the rectangles
     **********************************/
    public int roll() {
        if (!isHeld()) {
            val = (int) (Math.random() * 6 + 1);
        }
        return val;
    }
}
