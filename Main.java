//Main Class
/*
 * This classes whole purpose is to run the prgram using the @Override command it allows me to bypass the other classses
 * and launches the JavaFX window then allows the other classes to load
 */
//JavaFx import
import javafx.application.Application;
import javafx.stage.Stage;
//While making this i took inspiration of the template but by making the code on the main page 
//that its only function is to launch the game and nothing else
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    //The overide command ive been using helps bypass someother parts of my code it tells the compiler that i want to bypass the superclass
    @Override
    public void start(Stage primaryStage) {
        GameStart game = new GameStart();
        game.start(primaryStage);
    }
}

