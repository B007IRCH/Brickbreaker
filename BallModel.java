//Class ball
/*
 * goal for this is to keep the ball in the page 
 * and render a ball not a brick like the template
 */


//JavaFX imports
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
//decleration
public class BallModel {
    private double x, y; //represtents the ball's center
    private double radius; //sets the radius of the ball
    private double dx = 3, dy = -3;//represents the balls direction per update/per collision and will 
    //dertmin the speed and the movement of the ball
    
    //constructor
    /*
     * this class is very similar to the brick class which is that 
     * it will initaise the ball with its pre-set peramiters
     * with its specified x ,y and radius
     */
    public BallModel(double x, double y, double radius) { 
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    //movement
    public void move() {
        //both of these update the balls postion based on its speed 
        //and direction
        x += dx;
        y += dy;
        //collision detection
        //how this works is that if the ball hits the boundires of the screen it will flip its horizontal
        //direction
        if (x <= 0 || x >= 800 - radius * 2) {
            dx = -dx;
        }
        //and this one will flip the vertical direction if it hits the top of the game
        if (y <= 0) {
            dy = -dy;
        }
    }
    //drawing of the ball
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);//colour of ball
        gc.fillOval(x, y, radius * 2, radius * 2);// this draws a filled oval with 
        // the preset radius from the center of the ball 
    }
    //collision (paddle)
    //the whole class here is to detect the collision with the paddle and the ball
    public void checkCollision(PaddleModel paddle) {
        /*
         * it works but detetcing when the bottom of the ball comes into contact 
         * with th etop of the paddle then flipping 
         * its vertical direction
         */
        if (x + radius > paddle.getX() && x < paddle.getX() + paddle.getWidth() && y + radius * 2 > paddle.getY() && y + radius * 2 < paddle.getY() + paddle.getHeight()) {
            dy = -dy;
        }
    }
    //collsion (brick)
    /*
     * same again this will flip its vertical direction if it hits the brick
     * but will also return a true to let the game class know a brick has been hit
     * the false is if there isnt a collsion with a brick
     */
    public boolean checkCollision(BrickModel brick) {
        if (x + radius > brick.getX() && x < brick.getX() + brick.getWidth() && y + radius * 2 > brick.getY() && y < brick.getY() + brick.getHeight()) {
            dy = -dy;
            return true;
        }
        return false;
    }
    //restet
    //once a palyer has lost their lives it will center the ball again
    public void reset(double x, double y) {
        this.x = x;
        this.y = y;
        this.dx = 3;
        this.dy = -3;
    }
    //getter methods
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
/*
 * overall the class will create the ball
 * updates its position and speed and direction
 * and check colision with bricks and boundries 
 * also setting the reset method up
 */