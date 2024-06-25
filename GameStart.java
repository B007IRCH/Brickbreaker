//Class Game

/*
 * This will contain all gui functions of the game  and the logic of it
 * this class will bring all other classes together through it
 */
//JavaFX imports
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
//Main class Game that brings all of teh calsses together
public class GameStart {
    private final int WIDTH = 800; //Size of the game window (width)
    private final int HEIGHT = 600;//size of game window (height)
    private final int BRICK_ROWS = 6; // number of brick rows
    private final int BRICK_COLUMNS = 10; //number of bricks horizontally 

    private PaddleModel paddle; // paddle instance links Game class to paddle class
    private BallModel ball; //ball instance linbks Game class to all class
    private BrickModel[][] bricks;// a 2D array of bricks linking to the brick class
    private int score = 0; // starting score
    private int lives = 3; // starting lives
    private boolean running = false; // a boolean to say if the game is running or not
    //method
    public void start(Stage primaryStage) {
    Pane root = new Pane(); // The main root container for the whole scene
    Canvas canvas = new Canvas(WIDTH, HEIGHT); // The canvas where the game will be drawn
    root.getChildren().add(canvas); // Add the canvas to the root pane
    Scene scene = new Scene(root, WIDTH, HEIGHT); // Create a scene with the root pane and specified dimensions

    primaryStage.setTitle("Brick Breaker by Kyle Birch CI401"); // Set the title of the window
    primaryStage.setScene(scene); // Set the scene on the primary stage
    primaryStage.show(); // Show the primary stage

    paddle = new PaddleModel(WIDTH / 2, HEIGHT - 40, 100, 10); // Initialize the paddle at the bottom center of the screen
    ball = new BallModel(WIDTH / 2, HEIGHT / 2, 10); // Initialize the ball at the center of the screen
    bricks = new BrickModel[BRICK_ROWS][BRICK_COLUMNS]; // Declare the brick array
    initBricks(); // Initialize the bricks

    GraphicsContext gc = canvas.getGraphicsContext2D(); // Get the graphics context for drawing on the canvas
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (running) { // Check if the game is running
                update(); // Update the game state
                draw(gc); // Draw the updated game state
            }
        }
    };
    timer.start(); // Start the animation timer

    // Set up key event handling for the scene
    scene.setOnKeyPressed(e -> {
        if (e.getCode() == KeyCode.LEFT) {
            paddle.moveLeft(); // Move the paddle left if the LEFT arrow key is pressed
        } else if (e.getCode() == KeyCode.RIGHT) {
            paddle.moveRight(); // Move the paddle right if the RIGHT arrow key is pressed
        } else if (e.getCode() == KeyCode.SPACE) {
            running = !running; // Toggle the running state of the game if the SPACE key is pressed
        }
    });

    drawStartScreen(gc); // Draw the start screen
}


    private void initBricks() {
    for (int i = 0; i < BRICK_ROWS; i++) { // Loop through each row of bricks
        for (int j = 0; j < BRICK_COLUMNS; j++) { // Loop through each column in the current row
            // Create a new Brick object and place it in the correct position within the grid
            bricks[i][j] = new BrickModel(
                j * 80 + 5, // x-coordinate: horizontal position, including spacing
                i * 30 + 50, // y-coordinate: vertical position, including spacing
                75, // width of each brick
                25, // height of each brick
                Color.GOLD, // color of the brick
                1 // hit points of the brick (this doesnt work ive tried
                //but i cannot make the strengths work ;((
            );
            // The numbers 80 and 30 are used to determine the horizontal and vertical spacing between bricks
            // The numbers 5 and 50 are the starting offsets from the top-left corner of the canvas
            // Adjust these numbers to experiment with different spacing between the bricks
        }
    }
}


   private void update() {
    ball.move(); // Move the ball according to its current velocity and direction
    ball.checkCollision(paddle); // Check for collision between the ball and the paddle

    // Check for collisions between the ball and the bricks
    for (int i = 0; i < BRICK_ROWS; i++) {
        for (int j = 0; j < BRICK_COLUMNS; j++) {
            if (bricks[i][j] != null && ball.checkCollision(bricks[i][j])) {
                bricks[i][j] = null; // Remove the brick upon collision
                score += 10; // Increase the score by 10 points for each brick broken
            }
        }
    }

    // Check if the ball has fallen below the bottom of the screen
    if (ball.getY() > HEIGHT) {
        lives--; // Decrease the number of lives by one
        if (lives <= 0) { // If no lives are left
            running = false; // Stop the game
            drawGameOverScreen(); // Draw the game over screen (as of testing this doest draw?)
        } else {
            ball.reset(WIDTH / 2, HEIGHT / 2); // Reset the ball to the center of the screen
        }
    }
}

  private void draw(GraphicsContext gc) {
    gc.setFill(Color.GRAY); // Set the fill color for the background
    gc.fillRect(0, 0, WIDTH, HEIGHT); // Fill the entire canvas with black to clear the previous frame

    gc.setFill(Color.MEDIUMAQUAMARINE); // Set the fill color for the text
    gc.fillText("Score: " + score, WIDTH - 90, 20); // Draw the current score at the top-left corner
    gc.fillText("Lives: " + lives, 20, 20); // Draw the number of lives at the top-right corner
    gc.fillText("Press Space to Pause", 70, 594); // Draw instructions to pause the game near the bottom
    gc.fillText("Brick Breaker CI401", 280, 20); // Draw the game title near the top-center
    gc.fillText("Press Space to reset after Lives lost", WIDTH - 320, 594); // Draw instructions to reset the game after losing all lives

    paddle.draw(gc); // Draw the paddle
    ball.draw(gc); // Draw the ball

    // Loop through all the bricks and draw each one that is not null
    for (int i = 0; i < BRICK_ROWS; i++) {
        for (int j = 0; j < BRICK_COLUMNS; j++) {
            if (bricks[i][j] != null) {
                bricks[i][j].draw(gc); // Draw the brick at position (i, j)
            }
        }
    }
}


    private void drawStartScreen(GraphicsContext gc) {
    gc.setFill(Color.MEDIUMAQUAMARINE); // Set the background color to blue
    gc.fillRect(0, 0, WIDTH, HEIGHT); // Fill the entire canvas with the blue background

    gc.setFill(Color.KHAKI); // Set the text color 
    gc.setFont(new Font(50)); // Set the font size for the title
    gc.fillText("CI401 Brick Breaker Game", WIDTH / 2 - 250, HEIGHT / 2 - 30); // Draw the game title centered horizontally

    gc.setFont(new Font(20)); // Set the font size for the subtitles and instructions
    gc.fillText("Author: Kyle Birch", WIDTH / 2 - 100, HEIGHT / 2 + 20); // Draw the author's name centered horizontally
    gc.fillText("Tutor: Shine Shan", WIDTH / 2 - 100, HEIGHT / 2 + 40); // Draw the tutor's name centered horizontally
    gc.fillText("Press SPACE to Start", WIDTH / 2 - 100, HEIGHT / 2 + 90); // Draw the instruction to start the game
    gc.fillText("Use LEFT and RIGHT arrows to move the paddle", WIDTH / 2 - 200, HEIGHT / 2 + 150); // Draw the instruction to move the paddle
}

    /*
     * As of doing these comments I am struggiling to get this to display after a loss
     * but i will continue to make it work
     */

    private void drawGameOverScreen() { 
    GraphicsContext gc = new Canvas(WIDTH, HEIGHT).getGraphicsContext2D(); // Create a new GraphicsContext
    gc.setFill(Color.BLACK); // Set the background color to black
    gc.fillRect(0, 0, WIDTH, HEIGHT); // Fill the entire canvas with black to clear previous drawings

    gc.setFill(Color.BLUE); // Set the fill color to blue for the game over text
    gc.setFont(new Font(20)); // Set the font size to 20
    gc.fillText("Game Over", WIDTH / 2 - 50, HEIGHT / 2); // Display the "Game Over" text centered horizontally
    gc.fillText("Score: " + score, WIDTH / 2 - 50, HEIGHT / 2 + 30); // Display the final score centered horizontally below the "Game Over" text
    gc.fillText("Press SPACE to Restart", WIDTH / 2 - 100, HEIGHT / 2 + 60); // Display the instruction to restart the game centered horizontally below the score

    // Reset game state
    running = false; // Stop the game
    score = 0; // Reset the score
    lives = 3; // Reset the lives
    initBricks(); // Reinitialize the bricks
    ball.reset(WIDTH / 2, HEIGHT / 2); // Reset the ball position
}

}

    

