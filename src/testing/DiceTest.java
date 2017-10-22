package testing;

import groupthree.Dice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {

    private Dice dice1 = new Dice();

    @Test
    public void rollTest(){
        dice1.releaseDice();
        int val = dice1.roll();
        assertTrue(val >=1 && val <=6);

        dice1.holdDice();
        int val2 = dice1.roll();
        assertTrue(val2 == val);
    }

    @Test
    public void holdTests(){
        dice1.holdDice();
        assertTrue(dice1.isHeld() == true);
        dice1.releaseDice();
        assertTrue(dice1.isHeld() == false);
    }

    @Test
    public void setTest(){
        dice1.setDice(4);
        assertTrue(dice1.getVal() == 4);
        dice1.setDice(10);
        assertTrue(dice1.getVal() == 4);
    }

}
