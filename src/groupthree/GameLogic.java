package groupthree;

import java.util.ArrayList;

public class GameLogic {


    /**
     * Turn is at the end of the turn, when you've decided to hold or not, and when it rolls the rectangles.
     * The button that ends turn should be calling this.
     * @param hand An arraylist of dice.
     */
    public void turn(ArrayList<Dice> hand) {

        //loops through hand with temp var j
        for(Dice j: hand){
            // If the rectangles isn't held, roll it
            if (!(j.isHeld())){
                j.roll();
            }
        }
    }

    //TODO add a score method to check score and try to prevent doubledipping.
    /**
     * Scoring determines points based off of which rectangles are held.
     * @param hand is the hand of rectangles
     *  diceCount is an array to list amount of rectangles values, e.g. how many fives.
     * @return Returns the score integer.
     */
    public int scoring(ArrayList<Dice> hand) throws IllegalArgumentException{
        //This is a temp variable while I figure out how to score properly so that rectangles can't doubledip.
        int score = 0;
        //This counts the number of pairs, once it hits three you can use three pairs.
        int pairCount = 0;
        //Counts number of 1's, if there are 6 it's a straight
        int straightCount = 0;

        int diceCount[] = new int[6];
        //If the rectangles isn't held, then +1 to the array spot corresponding with the rectangles val
        for(Dice j: hand){
            if (j.isHeld()){
                diceCount[j.getVal()-1] += 1;
            }
        }
        for(int i = 0;  i < diceCount.length;i++) {

            // switch cases for 1spot rectangles.
            if(i == 0){
                switch(diceCount[0]){
                    case 6: score = 1000;
                        break;
                    case 5: score = 1200;
                        break;
                    case 4: score = 1100;
                            pairCount += 2;
                        break;
                    case 3: score = 1000;
                        break;
                    case 2: score = 200;
                         pairCount+= 2;
                        break;
                    case 1: score = 100;
                        straightCount++;
                        break;
                    //default:
                    //    throw new IllegalArgumentException("Number of rectangles must be between 0 and 6.");

                }
            }
            // We have this not = 0 here because then we can selectively deal with 1 spots since they are unique
            // Plus we don't have to worry as much about straights.
            // So this if statement does NOT deal with 1spots.
            if(i != 0){
                switch(diceCount[i]) {
                    // This will count as two three-pairs, except in the case of three twos where it'll count as three two-pairs
                    case 6:
                        score = ((i+1) * 100) * 2;
                        if (score == 400)
                            score = 500;
                        break;
                    //  counts as a three-pair, unless it's five in which case it's a three-pair plus two 5spots.
                    case 5:
                        if((i+1) == 5)
                            score = 600;
                        else{
                            score = ((i+1)*100);
                        }
                        break;
                    // counts as a three-pair, unless it's five in which case it's a three-pair plus one 5spot. Adds two to paircount
                    case 4:
                        if((i+1) == 5)
                            score = 550;
                        else {
                            score = ((i + 1) * 100);
                            pairCount += 2;
                        }
                        break;
                    case 3:
                        score = ((i+1)*100);
                        break;
                    // Counts as 100 if it's two 5spots, adds 1 to paircount
                    case 2:
                        if((i+1) == 5){
                            score = 100;
                        }
                        pairCount++;
                        break;
                    // If it's a 5 it counts as 50, add's to straightcount
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
            }
                //If there's a three-pair and there isn't a higher possible combination give 500
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

    }
}
