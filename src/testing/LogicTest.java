package testing;

import farklegame.Dice;
import farklegame.FarkleDiceLogic;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.fail;
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
    private final Dice d7 = new Dice();
    private final ArrayList<Dice> hold = new ArrayList<>();

    FarkleDiceLogic gl = new FarkleDiceLogic();


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

    /**
     * oneDiceScoresTest tests to make sure that all possible
     * combinations of dice with a one on them give the
     * correct score.
     */
    @Test
    void oneDiceScoresTest(){
        d1.setDice(1);
        d2.setDice(1);
        d3.setDice(1);
        d4.setDice(1);
        d5.setDice(1);
        d6.setDice(1);
        d7.setDice(1);
        d1.holdDice();
        d2.holdDice();
        d3.holdDice();
        d4.holdDice();
        d5.holdDice();
        d6.holdDice();
        d7.holdDice();
        assertEquals(0,gl.scoreHand(hold));
        hold.add(d1);
        assertEquals(100,gl.scoreHand(hold));
        hold.add(d2);
        assertEquals(200,gl.scoreHand(hold));
        hold.add(d3);
        assertEquals(1000,gl.scoreHand(hold));
        hold.add(d4);
        assertEquals(1100,gl.scoreHand(hold));
        hold.add(d5);
        assertEquals(1200,gl.scoreHand(hold));
        hold.add(d6);
        assertEquals(2000, gl.scoreHand(hold));
        try{
            hold.add(d7);
            gl.scoreHand(hold);
            fail("Expected an IllegalArgumentException.");
        }catch(IllegalArgumentException e){
            assertEquals(e.getMessage(), "Number of dice must be between 0 and 6.");
        }

    }

    /**
     * fiveDiceScoresTest tests to make sure that all possible combinations
     * of dice with a 5 on them give the correct score
     */
    @Test
    void fiveDiceScoresTest(){
        d1.setDice(5);
        d2.setDice(5);
        d3.setDice(5);
        d4.setDice(5);
        d5.setDice(5);
        d6.setDice(5);
        d7.setDice(5);
        d1.holdDice();
        d2.holdDice();
        d3.holdDice();
        d4.holdDice();
        d5.holdDice();
        d6.holdDice();
        d7.holdDice();
        assertEquals(0,gl.scoreHand(hold));
        hold.add(d1);
        assertEquals(50,gl.scoreHand(hold));
        hold.add(d2);
        assertEquals(100,gl.scoreHand(hold));
        hold.add(d3);
        assertEquals(500,gl.scoreHand(hold));
        hold.add(d4);
        assertEquals(550,gl.scoreHand(hold));
        hold.add(d5);
        assertEquals(600,gl.scoreHand(hold));
        hold.add(d6);
        assertEquals(1000, gl.scoreHand(hold));
        try{
            hold.add(d7);
            gl.scoreHand(hold);
            fail("Expected an IllegalArgumentException.");
        }catch(IllegalArgumentException e){
            assertEquals(e.getMessage(), "Number of dice must be between 0 and 6.");
        }

    }



    /**
     * holdTest is used to make sure that when you hold a dice and release it,
     * the scoring will only count the dice once.
     */
    @Test
    void holdDiceScoreTest(){
        d1.setDice(1);
        hold.add(d1);
        for(int i = 0; i < 6; i++){
            d1.holdDice();
            d1.releaseDice();
        }
        int score = gl.scoreHand(hold);
        assertEquals(0, score);

        d1.holdDice();
        score = gl.scoreHand(hold);
        assertEquals(100,score);
    }

    /**
     * bankPointsTest tests to make sure that dice held are properly added
     * to the banked points.
     */
    @Test
    void bankPointsTest(){
        d1.setDice(1);
        d1.holdDice();
        hold.add(d1);
        gl.tallyRoundPoints(hold);
        gl.bankPoints();
        int banked = gl.getBankedPoints();
        assertEquals(100, banked);
    }

    /**
     *tallyRoundPoints tests to make sure that the tallyRoundPoints and
     * finalTallyRoundPoints properly updates roundpoints and estRoundPoints
     */
    @Test
    void tallyRoundPointsTest(){
        d1.setDice(1);
        d1.holdDice();
        hold.add(d1);
        gl.tallyRoundPoints(hold);
        assertEquals(100, gl.getEstRoundPoints());

        gl.finalTallyRoundPoints(hold);
        assertEquals(100, gl.getRoundPoints());
    }

    /**
     * farkleTest tests to make sure that farkles are properly detected
     * and when three farkles are detected points are reset.
     */
    @Test
    void farkleTest(){
        d1.setDice(1);
        d1.holdDice();
        hold.add(d1);
        gl.tallyRoundPoints(hold);
        assertEquals(false, gl.isFarkle(hold));
        gl.bankPoints();
        d1.setDice(3);
        d1.releaseDice();
        assertEquals(true,gl.isFarkle(hold));
        gl.isFarkle(hold);
        assertEquals(2, gl.getFarkle());
        gl.isFarkle(hold);
        assertEquals(-900, gl.getBankedPoints());
        assertEquals(0,gl.getFarkle());

    }
    /**
     * wonGameTest tests that when you have banked above 10000 points, you have
     * won the game.
     */
    @Test
    void wonGametest(){
        d1.setDice(1);
        d2.setDice(1);
        d3.setDice(1);
        hold.add(d1);
        hold.add(d2);
        hold.add(d3);
        d1.holdDice();
        d2.holdDice();
        d3.holdDice();

        gl.tallyRoundPoints(hold);
        for(int i = 0; i < 10; i++){
            gl.bankPoints();
        }
        assertEquals(true, gl.wonGameStatus());
    }

    /**
     * handStatusTest will go through rollHandStatus and
     * make sure that it turning held dice into inactive dice.
     */
    @Test
    void handStatusTest(){
        d1.setDice(1);
        d1.holdDice();
        hold.add(d1);
        gl.rollHandStatus(hold);
        assertEquals(true, d1.isInactive());
    }

}
