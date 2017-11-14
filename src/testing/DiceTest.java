package testing;

import groupthree.Dice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
// @TODO Dustin or Bryce, run CheckStyle config file on this and fix problems.
class DiceTest {

    private final Dice dice1 = new Dice();

    @Test
    void rollTest(){
        dice1.releaseDice();
        int val = dice1.roll();
        assertTrue(val >=1 && val <=6);

        dice1.holdDice();
        int val2 = dice1.roll();
        assertTrue(val2 == val);
    }
/* @TODO This Dice test class needs to be repaired with the intended results that includes the new format of the Dice class.


    @Test
    public void validMoveTest(){
        dice1.setDice(1);
        boolean valid = dice1.validMove();
        assertTrue(valid);
        dice1.setDice(5);
        valid = dice1.validMove();
        assertTrue(valid);
        dice1.setDice(3);
        assertTrue(valid);

    }
    */
}
