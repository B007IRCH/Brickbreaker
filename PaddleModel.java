//Paddle class
/*This class differs from what is shown in the template this isolates the paddle making 
 * the code simpler for me to read as i wanted to make the code so much more simplistic as 
 * java isnt my strong point
 */

//JavaFX imports 
import javafx.scene.canvas.GraphicsContext; //this allows the paddle to be drawn
import javafx.scene.paint.Color;// This gives accsess to a libary of colours by name (REMEMBER AMERICAN COLOR)
//decleration
public class PaddleModel
 {
    private double x, y; //represents the position from the top left
    private double width, height; // dimentions of the paddle
    private double speed = 10; //Speed of paddle (keep at 10 way to easy if sped up)
    //constructor
    public PaddleModel(double x, double y, double width, double height) {
        /*
         * This class initates thes paddle with specified cordinates and dimentions
         */
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    //movement
    public void moveLeft() {
        if (x - speed > 0) x -= speed; //Sets the boundries (LEFT SIDE)
    }
    //movement
    public void moveRight() {
        if (x + width + speed < 800) x += speed;// sets the boundies of the paddle meaning it can run off the page (RIGHT SIDE)
    }
    //Drawing
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);// Colour of Paddle
        gc.fillRect(x, y, width, height);//Draws the rectangle (Paddle) with its predeterminded peramiters 
    }
    // These four provide accsess to the private fields without allowing direct manipulation to them
    //They are also used for retreving
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
/* 
 * Overall the paddle is self explanitory it Draws the Paddle 
 * It sets the boundries which the paddle cannot pass 
 * in testing i set the right side to 2000 alowing it to leave teh screen
 * and to 400 where it couldt get past the whole screen
 */