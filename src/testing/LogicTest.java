package testing;

import farklegame.Dice;
import farklegame.FarkleDiceLogic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LogicTest {

    Dice d1 = new Dice();
    Dice d2 = new Dice();
    Dice d3 = new Dice();
    Dice d4 = new Dice();
    Dice d5 = new Dice();
    Dice d6 = new Dice();
    ArrayList<Dice> hold = new ArrayList<>();

    FarkleDiceLogic gl = new FarkleDiceLogic();

    /*
    @Test
    public void scoringTest(){

        //Testing for straight
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

        int score = gl.scoring(hold);
        assertEquals(1000, score);

        //Three Pair Test
        hold.get(0).setDice(2);
        hold.get(1).setDice(2);
        hold.get(2).setDice(3);
        hold.get(3).setDice(3);
        hold.get(4).setDice(4);
        hold.get(5).setDice(4);

        score = gl.scoring(hold);

        assertEquals(500,score);

        //Just 1
        hold.get(0).setDice(1);

        score = gl.scoring(hold);
        assertEquals(100, score);

        //Just 5
        hold.get(0).setDice(5);

        score = gl.scoring(hold);
        assertEquals(50, score);
    }

    @TODO This test is invalid, needs to be redesigned with the current layout of FarkleDiceLogic class.
    @TODO Run Checkstyle rules on this to clean it up as well.

    */
}
