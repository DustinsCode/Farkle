package groupthree;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the controller class for Farkle. It is the class that interfaces between
 * the FXML files generated by Scene Builder and the local variables and objects in Java
 * via the @FXML annotation.
 * @author Wes Harrison
 * @version 1.0
 */
public class FarkleController {
    
    /**
     * The java-FXML accessor variable for the first rectangle.
     */
    @FXML
   private Rectangle rect1;
    /**
     * The java-FXML accessor variable for the second rectangle.
     */
    @FXML
   private Rectangle rect2;
    /**
     * The java-FXML accessor variable for the third rectangle.
     */
    @FXML
   private Rectangle rect3;
    /**
     * The java-FXML accessor variable for the fourth rectangle.
     */
    @FXML
   private Rectangle rect4;
    /**
     * The java-FXML accessor variable for the fifth rectangle.
     */
    @FXML
   private Rectangle rect5;
    /**
     * The java-FXML accessor variable for the sixth rectangle.
     */
    @FXML
   private Rectangle rect6;
    /**
     * This is the label that keeps track of bank points.
     */
    @FXML
    private Label bankPoints;
    /**
     * This is the label that keeps track of the round points.
     */
    @FXML
    private Label roundPoints;


    Image d1 = new Image("d1.png");
    Image d2 = new Image("d2.png");
    Image d3 = new Image("d3.png");
    Image d4 = new Image("d4.png");
    Image d5 = new Image("d5.png");
    Image d6 = new Image("d6.png");

    
    /**
     * ArrayList of rectangles representing the dice on the screen.
     */
   private static ArrayList<Rectangle> rectangles = new ArrayList<>();

    private DiceUILogic game = new DiceUILogic(this);

    public FarkleController() {

    }


    /**
     * When this method is called, it will exit the application.
     */
    public void exitHandler() {
        System.exit(0);
    }


    /**
     * When this method is called, it will change the Scene to GameScreen.
     * @param event The button push event that signals entrance
     *              into the main game screen.
     */
    public void enterGameScreenButtonPushed(final ActionEvent event) throws IOException {


            Parent gameScreenParent = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
            Scene gameScreen = new Scene(gameScreenParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(gameScreen);
            window.show();






    }

    /**
     * This method simply adds our rectangles into the rectangle ArrayList.
     */
    private void setRectangleArray() {
        rectangles.clear();
        rectangles.add(rect1);
        rectangles.add(rect2);
        rectangles.add(rect3);
        rectangles.add(rect4);
        rectangles.add(rect5);
        rectangles.add(rect6);
    }

    /**
     * This method handles the ActionEvent generated by pressing the "Roll Dice" button within the application.
     * It animates the current rectangles on the screen as well as utilizes the Dice class and initializes a random number
     * onto each one.
     * @param event is the ActionEvent generated by pressing the Roll Dice button.
     */
    public void rollTheDiceButtonPushed(final ActionEvent event) {

        // Adds rectangles to the rectangle arrayList.
        setRectangleArray();
        // Sets ArrayList of Dice with random values if they're not held.
        game.setHand();
        game.mapDice();


            //Animates the Dice


            Timeline diceAnimate = new Timeline(

                    new KeyFrame(Duration.ZERO,
                            ae -> game.setRectFill(d1)),

                    new KeyFrame(Duration.millis(111),
                            ae -> game.setRectFill(d2)),

                    new KeyFrame(Duration.millis(222),
                            ae -> game.setRectFill(d3)),

                    new KeyFrame(Duration.millis(333),
                            ae -> game.setRectFill(d4)),

                    new KeyFrame(Duration.millis(444),
                            ae -> game.setRectFill(d5)),

                    new KeyFrame(Duration.millis(555),
                            ae -> game.setRectFill(d6)),

                    new KeyFrame(Duration.millis(777),
                            ae -> game.getHand(rectangles)) // Calls getHand from our game instance and sets the fills.


            );

        diceAnimate.setCycleCount(1);
        diceAnimate.play();

        if (game.rollCount > 0 && game.isFarkle() ) {

            for (Rectangle rectangle : rectangles) {
                rectangle.setEffect(null);
            }

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(MainUI.getPrimaryStage());
            alert.setTitle("Farkle!");
            alert.setHeaderText("You have Farkled: Round Reset");
            alert.setContentText("Try again! The current Farkle count is: "+ game.logic.farkleCounter);
            alert.show();

        }

        game.setRolled(); // Increments the rolled variable.
        System.out.println(game.logic.getRoundPoints());



    }


    /**
     * This method runs when the "Bank Points" button has been clicked.
     * @param event MouseEvent that this method takes as the input.
     */
    public void bankPointsButtonPushed( final ActionEvent event){
        game.setBankScore();
       bankPoints.setText(Integer.toString(game.getBankScore()));
       roundPoints.setText(Integer.toString(game.getRoundScore()));
        game.resetHand();



        if(game.logic.wonGameStatus()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(MainUI.getPrimaryStage());
            alert.setTitle("You win!");
            alert.setHeaderText("Bank Reached 10,000!");
            alert.setContentText("You have won the game, please exit and start a new game!");
            alert.show();
        }




    }

    /**
     * This method will call methods from our game instance of DiceUILogic to
     * take our rectangles and add a glow, then hold the corresponding dice.
     * @param event The Mouse2Event generated by clicking a rectangle.
     */
    public void holdRectangles(MouseEvent event){


       Rectangle rectX = (Rectangle) event.getSource();

            game.checkRolled();
            game.setHoldStatus(rectX);
            roundPoints.setText(Integer.toString(game.getRoundScore()));



}


    public ArrayList<Rectangle> getRectangles () {
        
        return rectangles;

    }




}


