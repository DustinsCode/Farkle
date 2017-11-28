package farklegame;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * The FarkleDiceLogic class performs our actual game
 * logic on Dice class objects. The requirements
 * for this was obtained from an external source on Farkle game rules.
 */
public class FarkleDiceLogic extends Observable {

    /** bankedPoints is the amount of points you have in the bank.*/
    private int bankedPoints = 0;
    /** roundPoints is the amount of points you have in current round*/
    private int roundPoints = 0;
    /** estRoundPoints is estimated round points after you roll*/
    private int estRoundPoints = 0;
    /**
     * This keeps track of the number of farkles
     */
    private int farkleCounter = 0;
    /**
     * This keeps track of if it's currently farkled.
     */
    private boolean wonGame = false;

    final int farkle = 0;


    /**
     * isFarkle is called in order to detect if a Farkle has been rolled.
     * It will call upon scoreHandAll, and compare it to roundPoints, if they're
     * equal that means that zero points have been scored this turn, and that it
     * is a Farkle. If it is a Farkle, you add 1 to the Farkle counters, at 3
     * Farkles you remove 1000 points from bankedPoints and reset the counter.
     * After a Farkle has been detected, it resets the round, and returns true.
     * If there was no Farkle, it just returns false.
     *
     * @param hand is the arraylist of dice being passed to isFarkle
     * @return Returns whether or not there has been a farkle.
     */


    public boolean isFarkle(final ArrayList<Dice> hand) {
        if (scoreHand(hand) == farkle) {
            farkleCounter++;
            roundPoints = 0;
            if (farkleCounter >= 3) {
                bankedPoints -= 1000;
                farkleCounter = 0;
                }
            resetRound(hand);
            return true;

        } else {
            return false;
        }
    }




    /**
     * This tallies the current hand of dice and sets the roundPoints
     * variable accordingly.
     * @param hand The ArrayList<Dice> representing our current hand.
     */
    public void tallyRoundPoints(final ArrayList<Dice> hand) {
        estRoundPoints = (roundPoints + scoreHand(hand));
    }

    /**
     * This function tallies the current hand of dice and sets the roundPoints
     * variable to it.
     */
    public void finalTallyRoundPoints(final ArrayList<Dice> hand){
        roundPoints += scoreHand(hand);
    }


    /**
     * rollHandStatus will go through the hand of dice passed to it and change
     * held dice to inactive dice, and then roll active dice.
     * @param hand The ArrayList<Dice> representing our current hand.
     */
    public void rollHandStatus(final ArrayList<Dice> hand) {
        for (Dice j: hand) {
            if (!(j.isInactive())) {
                if (j.isHeld()) {
                    j.setInactive();
                } else {
                    j.roll();
                }
            }
        }
    }

    /**
     * ScoreHand determines points based off of which dice are held.
     * hande is first sent through an forloop, creating an array that has the
     * non-held number of each dice.
     * dice[0] = 4 would mean there are 4 "1" dice.
     * "1" dice and "5" dice are sent through their own switch-case, because
     * they are graded differently. While in the switch case they are counted
     * for pairs and straights. For details on specific score values read the
     * official Farkle rules. After going through the forloop they are compared
     * for which value is greater (but only for dice that can count for multiple
     * values), and the greater value is scored
     *
     * @param hand is the hand of dice passed to it.

     * @return Returns the score integer.
     */
    public int scoreHand(final ArrayList<Dice> hand) {
        int score = 0;
        int pairCount = 0;
        int straightCount = 0;
        int[] diceCount = new int[6];
        for (Dice j: hand) {
            if (j.isHeld() && !(j.isInactive())) {
                diceCount[j.getVal() - 1] += 1;
            }
        }
        for (int i = 0;  i < diceCount.length; i++) {
            if (i == 0) {
                switch (diceCount[0]) {
                    case 6: score += 2000;
                        break;
                    case 5: score += 1200;
                        break;
                    case 4: score += 1100;
                            pairCount += 2;
                        break;
                    case 3: score += 1000;
                        break;
                    case 2: score += 200;
                         pairCount++;
                        break;
                    case 1: score += 100;
                        straightCount++;
                        break;
                    case 0:
                        straightCount = 0;
                        break;
                    default: throw new IllegalArgumentException(
                            "Number of dice must be between 0 and 6.");
                }
            }
            if (i == 4) {
                switch (diceCount[4]) {
                    case 6: score += 1000;
                        break;
                    case 5: score += 600;
                     break;
                    case 4: score += 550;
                      pairCount += 2;
                     break;
                    case 3: score += 500;
                      break;
                    case 2: score += 100;
                       pairCount++;
                    case 1: score += 50;
                      straightCount++;
                    break;
                    case 0:
                        straightCount = 0;
                        break;
                    default: throw new IllegalArgumentException(
                            "Number of dice must be between 0 and 6.");
                }
            }
            if (i != 0 && i != 4) {
                switch (diceCount[i]) {
                    case 6: score += ((i + 1) * 100) * 2;
                        break;
                    case 5: score += ((i + 1) * 100);
                        break;
                    case 4: score += ((i + 1) * 100);
                        pairCount += 2;
                        break;
                    case 3:
                        score += ((i + 1) * 100);
                        break;
                    case 2:
                        pairCount++;
                        break;
                    case 1:
                        straightCount++;
                        break;
                    case 0:
                        straightCount = 0;
                        break;
                    default: throw new IllegalArgumentException(
                            "Number of dice must be between 0 and 6.");
            }
                if (pairCount == 3) {
                    if (score < 500) {
                        score = 500;
                    }
                }
                if (straightCount == 6) {
                    if (score < 1000) {
                        score = 1000;
                    }
                }
            }
        }
        return score;
    }


    /** bankPoints is used to take the current round points and point them into
     *  the total bankpoints. It will also reset your round points and check to
     *  see if points are over 10,000 which would be a victory
     */
   public void bankPoints() {

        bankedPoints += roundPoints; //Adds current round points to our bank.
        roundPoints = 0; //resets round points when you bank.
        // Checks to see if we've won
        if (bankedPoints >= 10000) {
            wonGame = true; //Sets to true if we win.
            // @Todo add observer
        }
    }

    /**
     * Returns all dice to default state, not held and active, and then rerolls
     * them.
     * @param hand is the current hand of dice
     */

    public void resetRound(final ArrayList<Dice> hand) {
        for (Dice j: hand) {
            j.releaseDice();
            j.setActive();
            j.roll();
        }
    }

    /**
     * Returns the current amount of banked points.
     * @return the current amount of banked points.
     */
    public int getBankedPoints() {
        return bankedPoints;
    }

    /**
     * getRoundPoints is a getter for roundpoints.
     * @return The number of points this round
     */
    public int getRoundPoints() {
        return roundPoints;
    }

    /**
     * getEstRoundPoints is a getter for estimated round points
     * @return the number of estimated round points (round points + held dice)
     */
    public int getEstRoundPoints(){return estRoundPoints;}

    /**
     * Is a getter for whether or not the game has won.
     * @todo make this into an observer
     * @return whether or not the game has been won.
     */
    public boolean wonGameStatus() {
         return wonGame;
}

    /**
     * Returns number of farkles.
     * @return returns
     */
    public int getFarkle() {
        return farkleCounter;
    }
}
