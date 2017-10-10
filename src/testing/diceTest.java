package testing;

import groupthree.dice;
import org.junit.*;

import static org.junit.Assert.*;

public class diceTest {

    private dice d1 = new dice();

    @Test
    public void rollTest(){
        d1.setHold(false);
        int val = d1.roll();
        assertTrue(val >=1 && val <=6);

        d1.setHold(true);
        int val2 = d1.roll();
        assertTrue(val2 == val);
    }

    @Test
    public void validMoveTest(){
        d1.setVal(1);
        boolean valid = d1.validMove();
        assertTrue(valid);
        d1.setVal(5);
        valid = d1.validMove();
        assertTrue(valid);
        d1.setVal(3);
        assertTrue(valid);

    }
}
