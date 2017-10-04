package groupthree;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class farkleUI extends Application {

   private Stage window;
   private Scene scene1, scene2;
    public static void main(String[] args) {
        launch(args);
    }

    @Override    public void start(Stage primaryStage) {
        window = primaryStage;

        Label label1 = new Label ("Welcome to the first scene!");
        Button button1 = new Button("Play!");
        button1.setOnAction(e -> window.setScene(scene2));

        //Home screen layout
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1,button1);
        scene1 = new Scene(layout1, 200, 200);

        //Button 2 Scene 2 Layout
        Label label2 = new Label ("This will be the game window scene");
        Button button2 = new Button("Exit");


        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 200,200);

        window.setScene(scene1);
        window.setTitle("Farkle Alpha Release 1.0");
        window.show();

    }
}
