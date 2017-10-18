package groupthree;

import java.util.ArrayList;

public class GameLogic {

    /** bankedPoints is the amount of points you have in the bank.*/
    private int bankedPoints = 0;

    /** roundPoints is the amount of points you have in current round*/
    private int roundPoints = 0;

    private int farkleCounter = 0;


    /**
     * Turn is at the end of the turn, when you've decided to hold or not, and when it rolls the rectangles.
     * Turn is at the end of the turn, when you've decided to hold or not, and when it rolls the dice.
     * The button that ends turn should be calling this.
     * @param hand An arraylist of dice.
     */
    public void turn(ArrayList<Dice> hand) {

        int points = scoreHand(hand);
        //If there are 0 points, the round is over and you add one to the farkle counter
        if (points == 0) {
            farkleCounter++;
            roundPoints = 0;
            if (farkleCounter >= 3){
                bankedPoints -= 1000;
                farkleCounter = 0;
            }
            resetRound(hand);
            return;
        }
        //Add points to the roundpoints
        roundPoints += points;
        //loops through hand with temp var j
        for(Dice j: hand){
            // If the rectangles isn't held, roll it
            if (!(j.isHeld())){
                j.roll();
            // For active dice, if it's held, set it to inactive, otherwise, roll it
            if (!(j.isInactive())){
                if(j.isHeld())
                    j.setInactive();
                else
                    j.roll();
            }
        }
    }

    //TODO add a score method to check score and try to prevent doubledipping.

    /**
     * Scoring determines points based off of which rectangles are held.
     * @param hand is the hand of rectangles
     *  diceCount is an array to list amount of rectangles values, e.g. how many fives.
     * ScoreHand determines points based off of which dice are held.
     * @param hand is the hand of dice
     *  diceCount is an array to list amount of dice values, e.g. how many fives.
     * @return Returns the score integer.
     */

        //This is a temp variable while I figure out how to score properly so that rectangles can't doubledip.
        //This is a temp variable while I figure out how to score properly so that dice can't doubledip.
        int score = 0;
        //This counts the number of pairs, once it hits three you can use three pairs.
        int pairCount = 0;
        //Counts number of 1's, if there are 6 it's a straight
        int straightCount = 0;

        int diceCount[] = new int[6];
        //If the rectangles isn't held, then +1 to the array spot corresponding with the rectangles val
        //If the dice isn't held, then +1 to the array spot corresponding with the dice val
        for(Dice j: hand){
            if (j.isHeld()){
                diceCount[j.getVal()-1] += 1;
            }
        }
        for(int i = 0;  i < diceCount.length;i++) {


            // switch cases for 1spot rectangles.
            // switch cases for 1spot dice.
            if(i == 0){
                switch(diceCount[0]){
                    case 6: score = 1000;
                    case 6: score += 2000;
                        break;
                    case 5: score = 1200;
                    case 5: score += 1200;
                        break;
                    case 4: score = 1100;
                            pairCount += 2;
                        break;
                    case 3: score = 1000;
                    case 3: score += 1000;
                        break;
                    case 2: score = 200;
                    case 2: score += 200;
                         pairCount+= 2;
                        break;
                    case 1: score = 100;
                    case 1: score += 100;
                        straightCount++;
                        break;
                    default:
                        throw new IllegalArgumentException("Number of rectangles must be between 0 and 6.");

                }
            }
            // We have this not = 0 here because then we can selectively deal with 1 spots since they are unique
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
            // So this if statement does NOT deal with 1spots.
            if(i != 0){
                switch(diceCount[i]) {
                    // This will count as two three-pairs, except in the case of three twos where it'll count as three two-pairs
                    case 6:
                        score = ((i+1) * 100) * 2;
                        if (score == 400)
                            score = 500;
                    // This will count as two three-pairs
                    case 6: score += ((i+1) * 100) * 2);
                        break;
                    //  counts as a three-pair, unless it's five in which case it's a three-pair plus two 5spots.
                    case 5:
                        if((i+1) == 5)
                            score = 600;
                        else{
                            score = ((i+1)*100);
                        }
                    case 5: score += ((i+1)*100);
                        break;
                    // counts as a three-pair, unless it's five in which case it's a three-pair plus one 5spot. Adds two to paircount
                    case 4:
                        if((i+1) == 5)
                            score = 550;
                        else {
                            score = ((i + 1) * 100);
                            pairCount += 2;
                        }
                    // c
                    case 4: score += ((i + 1) * 100);
                        pairCount += 2;
                        break;
                    // Counts as a three-pair.
                    case 3:
                        score = ((i+1)*100);
                        score += ((i+1)*100);
                        break;
                    // Counts as 100 if it's two 5spots, adds 1 to paircount
                    // adds 1 to paircount
                    case 2:
                        if((i+1) == 5){
                            score = 100;
                        }
                        pairCount++;
                        break;
                    // If it's a 5 it counts as 50, add's to straightcount
                    // Adds to straightcount
                    case 1:
                        if ((i+1) == 5){
                            score = 50;
                        }
                        straightCount++;
                        break;
                    //If it's a 0 nothing happens, straightCount resets to 0 just in case I have a bug somewhere. #efficiency.
                    case 0:
                        straightCount = 0;
                        break;
                    default:
                        throw new IllegalArgumentException("Number of rectangles must be between 0 and 6.");
                        throw new IllegalArgumentException("Number of dice must be between 0 and 6.");
            }
                //If there's a three-pair and there isn't a higher possible combination give 500
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
    public void bankPoints(){
    public void bankPoints(ArrayList<Dice> hand){
        int points = scoreHand(hand);
        if (points == 0) {
            farkleCounter++;
            roundPoints = 0;
            if (farkleCounter >= 3){
                bankedPoints -= 1000;
                farkleCounter = 0;
             }
        }
        roundPoints += points;
        bankedPoints += roundPoints;
        if (bankedPoints >= 10000){
            //you win yay
        }
        resetRound(hand);

    }

    /**
     * Returns all dice to normal state and rolls them.
     * @param hand is the current hand of dice
     */
    private void resetRound(ArrayList<Dice> hand){
        for(Dice j: hand){
            j.releaseDice();
            j.setActive();
            j.roll();
        }
    }


}
