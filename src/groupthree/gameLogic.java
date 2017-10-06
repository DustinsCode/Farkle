package groupthree;

import java.util.*;

public class gameLogic {

    dice d1;
    dice d2;
    dice d3;
    dice d4;
    dice d5;
    dice d6;

    int gameScore;
    int currentScore;

    public void startGame() {

        //create dice
        d1 = new dice();
        d2 = new dice();
        d3 = new dice();
        d4 = new dice();
        d5 = new dice();
        d6 = new dice();

        //set up listing for dice
	    ArrayList<dice> inPlay = new ArrayList<>();
	    inPlay.add(d1);
	    inPlay.add(d2);
        inPlay.add(d3);
        inPlay.add(d4);
        inPlay.add(d5);
        inPlay.add(d6);
	    ArrayList<dice> heldThisRoll = new ArrayList<>();
        ArrayList<dice> holdOverall = new ArrayList();

    
    }

    /**
     * Checks for validity of hold values.
     *
     * TODO: go through dice in play, and dice held this roll
     *
     * @return if hold is valid
     */
    private boolean validHold(){

        return false;
    }
}
