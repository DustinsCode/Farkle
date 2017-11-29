package testing;

import farklegame.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiceTest {

    private final Dice dice1 = new Dice();

    /**
     * Tests that the dice is always between 1 and 6.
     * Tests that holding the dice doesn't change value.
     */
    @Test
    void rollTest() {
        dice1.releaseDice();
        int val = dice1.roll();
        assertTrue(val >=1 && val <=6);

        dice1.holdDice();
        int val2 = dice1.roll();
        assertTrue(val2 == val);
    }

    /**
     * Tests the getVal() method in the Dice class.
     */
    @Test
    void getValTest() {
        assertTrue(dice1.getVal() >= 1 && dice1.getVal() <= 6);
    }

    /**
     * Tests the setDice() method in the Dice class.
     */
    @Test
    void setDiceTest() {
        dice1.setDice(0);
        assertTrue(dice1.getVal() >= 1 && dice1.getVal() <= 6);
        dice1.setDice(10);
        assertTrue(dice1.getVal() >= 1 && dice1.getVal() <= 6);
        dice1.setDice(5);
        assertTrue(dice1.getVal() == 5);
    }

    /**
     * Tests the holdDice() method in the Dice class.
     * Also tests isHeld().
     */
    @Test
    void holdDiceTest() {
        dice1.holdDice();
        assertTrue(dice1.isHeld());
        dice1.holdDice();
        assertTrue(dice1.isHeld());
    }

    /**
     * Tests the releaseDice() method in the Dice class.
     * Also tests isHeld().
     */
    @Test
    void releaseDiceTest() {
        dice1.releaseDice();
        assertFalse(dice1.isHeld());
        dice1.releaseDice();
        assertFalse(dice1.isHeld());
    }

    /**
     * Tests setActive(), setInactive(), and isInactive() methods in the Dice class.
     */
    @Test
    void diceActiveTests() {
        dice1.setActive();
        assertFalse(dice1.isInactive());
        dice1.setInactive();
        assertTrue(dice1.isInactive());
    }
}
