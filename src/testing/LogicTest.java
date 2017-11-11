package testing;

import groupthree.GameLogic;
import groupthree.Dice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test case for the logic in the game.
 */
class LogicTest {

    private final Dice d1 = new Dice();
    private final Dice d2 = new Dice();
    private final Dice d3 = new Dice();
    private final Dice d4 = new Dice();
    private final Dice d5 = new Dice();
    private final Dice d6 = new Dice();
    private final ArrayList<Dice> hold = new ArrayList<>();

    private final GameLogic gl = new GameLogic();

    @Test
    void scoringTest(){

        /**
         * Tests to make sure a straight is scored correctly.
         */
        d1.setDice(1);
        d2.setDice(2);
        d3.setDice(3);
        d4.setDice(4);
        d5.setDice(5);
        d6.setDice(6);
        d1.holdDice();
        d2.holdDice();
        d3.holdDice();
        d4.holdDice();
        d5.holdDice();
        d6.holdDice();
        hold.add(d1);
        hold.add(d2);
        hold.add(d3);
        hold.add(d4);
        hold.add(d5);
        hold.add(d6);

        int score = gl.scoreHand(hold);
        assertEquals(1000, score);

        /**
         * Tests for correct three pair score
         */
        hold.get(0).setDice(2);
        hold.get(1).setDice(2);
        hold.get(2).setDice(3);
        hold.get(3).setDice(3);
        hold.get(4).setDice(4);
        hold.get(5).setDice(4);

        score = gl.scoreHand(hold);

        assertEquals(500,score);

        /**
         * Tests for correct one one-dice score.
         */
        hold.get(0).setDice(1);

        score = gl.scoreHand(hold);
        assertEquals(100, score);

        /**
         * Tests for correct one five-dice score.
         */
        hold.get(0).setDice(5);

        score = gl.scoreHand(hold);
        assertEquals(50, score);
    }
}
