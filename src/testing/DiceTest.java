package testing;

import groupthree.dice;
import org.junit.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {

    private dice dice1 = new dice();

    @Test
    public void rollTest(){
        dice1.releaseDice();
        int val = dice1.roll();
        assertTrue(val >=1 && val <=6);

        dice1.holdDice();
        int val2 = dice1.roll();
        assertTrue(val2 == val);
    }
/* @TODO This dice test class needs to be repaired with the intended results that includes the new format of the dice class.


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
