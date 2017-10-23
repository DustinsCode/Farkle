package groupthree;

import java.util.ArrayList;

public class GameLogic {

    /** bankedPoints is the amount of points you have in the bank.*/
    private int bankedPoints = 0;

    /** roundPoints is the amount of points you have in current round*/
    private int roundPoints = 0;
    /**
     * This keeps track of the number of farkles
     */
    int farkleCounter = 0;
    /**
     * This keeps track of if it's currently farkled.
     */

    private boolean wonGame = false;


    /**
     * Turn is at the end of the turn, when you've decided to hold or not, and when it rolls the dice.
     * The button that ends turn should be calling this.
     * @param hand An arraylist of dice.
     */
    boolean isFarkle(ArrayList<Dice> hand) {
        int points = scoreHandAll(hand);

        if ( points == roundPoints ) {
            farkleCounter++;
            roundPoints = 0;


            if (farkleCounter >= 3){
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
     * This tallies the current hand of dice and sets the roundPoints variable accordingly.
     * @param hand The ArrayList<Dice> representing our current hand.
     */
    void tallyRoundPoints (ArrayList<Dice> hand){
        roundPoints = scoreHand(hand);
    }

    void rollHandStatus(ArrayList<Dice> hand) {
        for(Dice j: hand){
            // For active dice, if it's held, set it to inactive, otherwise, roll it
            if (!(j.isInactive())){
                if(j.isHeld())
                    j.setInactive();
                else
                    j.roll();
            }
        }
    }


    /**
     * ScoreHand determines points based off of which dice are held.
     * @param hand is the hand of dice
     *  diceCount is an array to list amount of dice values, e.g. how many fives.
     * @return Returns the score integer.
     */
    private int scoreHand(ArrayList<Dice> hand){

        //This is a temp variable while I figure out how to score properly so that dice can't doubledip.
        int score = 0;
        //This counts the number of pairs, once it hits three you can use three pairs.
        int pairCount = 0;
        //Counts number of 1's, if there are 6 it's a straight
        int straightCount = 0;

        int diceCount[] = new int[6];
        //If the dice isn't held, then +1 to the array spot corresponding with the dice val
        for(Dice j: hand){
            if (j.isHeld()){
                diceCount[j.getVal()-1] += 1;
            }
        }


        for(int i = 0;  i < diceCount.length;i++) {


            // switch cases for 1spot dice.
            if(i == 0){
                switch(diceCount[0]){
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
                         pairCount+= 2;
                        break;
                    case 1: score += 100;
                        straightCount++;
                        break;
                }
            }
            // Switch cases for 5spot dice.
            if(i == 4){
                switch(diceCount[4]){
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
                }
            }


            // This deals with every die by the 1spot and 5spot
            // Plus we don't have to worry as much about straights.
            if(i != 0 && i != 4){
                switch(diceCount[i]) {
                    // This will count as two three-pairs
                    case 6: score += ((i+1) * 100) * 2;
                        break;
                    case 5: score += ((i+1)*100);
                        break;
                    // c
                    case 4: score += ((i + 1) * 100);
                        pairCount += 2;
                        break;
                    // Counts as a three-pair.
                    case 3:
                        score += ((i+1)*100);
                        break;
                    // adds 1 to paircount
                    case 2:
                        pairCount++;
                        break;
                    // Adds to straightcount
                    case 1:
                        straightCount++;
                        break;
                    //If it's a 0 nothing happens, straightCount resets to 0 just in case I have a bug somewhere. #efficiency.
                    case 0:
                        straightCount = 0;
                        break;
                    default:
                        throw new IllegalArgumentException("Number of dice must be between 0 and 6.");
            }
                //If theres a three-pair and there isn't a higher possible combination give 500
                if(pairCount == 3) {
                    if (score < 500) {
                        score = 500;
                    }
                }
                //If there's a straight and if there isn't a higher possible combination give 1000
                if(straightCount == 6){
                    if(score < 1000){
                        score = 1000;
                    }
                }
            }
        }
        return score;
    }


    private int scoreHandAll(ArrayList<Dice> hand){

        //This is a temp variable while I figure out how to score properly so that dice can't doubledip.
        int score = 0;
        //This counts the number of pairs, once it hits three you can use three pairs.
        int pairCount = 0;
        //Counts number of 1's, if there are 6 it's a straight
        int straightCount = 0;

        int diceCount[] = new int[6];
        //If the dice isn't held, then +1 to the array spot corresponding with the dice val
        for(Dice j: hand){
                diceCount[j.getVal()-1] += 1;

        }


        for(int i = 0;  i < diceCount.length;i++) {


            // switch cases for 1spot dice.
            if(i == 0){
                switch(diceCount[0]){
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
                        pairCount+= 2;
                        break;
                    case 1: score += 100;
                        straightCount++;
                        break;
                }
            }
            // Switch cases for 5spot dice.
            if(i == 4){
                switch(diceCount[4]){
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
                }
            }


            // This deals with every die by the 1spot and 5spot
            // Plus we don't have to worry as much about straights.
            if(i != 0 && i != 4){
                switch(diceCount[i]) {
                    // This will count as two three-pairs
                    case 6: score += ((i+1) * 100) * 2;
                        break;
                    case 5: score += ((i+1)*100);
                        break;
                    // c
                    case 4: score += ((i + 1) * 100);
                        pairCount += 2;
                        break;
                    // Counts as a three-pair.
                    case 3:
                        score += ((i+1)*100);
                        break;
                    // adds 1 to paircount
                    case 2:
                        pairCount++;
                        break;
                    // Adds to straightcount
                    case 1:
                        straightCount++;
                        break;
                    //If it's a 0 nothing happens, straightCount resets to 0 just in case I have a bug somewhere. #efficiency.
                    case 0:
                        straightCount = 0;
                        break;
                    default:
                        throw new IllegalArgumentException("Number of dice must be between 0 and 6.");
                }
                //If theres a three-pair and there isn't a higher possible combination give 500
                if(pairCount == 3) {
                    if (score < 500) {
                        score = 500;
                    }
                }
                //If there's a straight and if there isn't a higher possible combination give 1000
                if(straightCount == 6){
                    if(score < 1000){
                        score = 1000;
                    }
                }
            }
        }
        return score;
    }

    /**
     * bankPoints should end turn
     */
    void bankPoints(ArrayList<Dice> hand){

        bankedPoints += roundPoints; //Adds current round points to our bank.
        roundPoints = 0; //resets round points when you bank.
        // Checks to see if we've won
        if (bankedPoints >= 10000){
            wonGame = true; //Sets to true if we win.
            // @TODO Need an idea to fire off a static notification or event to get with the UI to transition screens.
        }

    }

    /**
     * Returns all dice to normal state and rolls them.
     * @param hand is the current hand of dice
     */
     void resetRound(ArrayList<Dice> hand){ //@TODO this doesn't really need to be called in the beginning. It should only be used when the turn is over (farkle or bankpoints).
        for(Dice j: hand){
            j.releaseDice();
            j.setActive();
            j.roll();
        }
    }

    int getBankedPoints () {
        return bankedPoints;
    }

    int getRoundPoints () {
        return roundPoints;
    }

    boolean wonGameStatus(){
         return wonGame;
}




}
