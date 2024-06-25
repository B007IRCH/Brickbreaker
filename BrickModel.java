//Brick Class
/*
 * This class's goal is to make new layers of the bricks 
 * my goal is to have multiple layers maybe of varing colours & strengths 
 * i would like a minimum of 4 layers 
 */
// JavaFX imports
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
//Class decleration
public class BrickModel {   //public class (measn it can be acsessed by other parts
    //Atributes
    private double x, y; // declares the position of the bricks on the canvas
    private double width, height; // Decalres the size of the bricks
    private Color color; //colour (AMERICAN COLOR)
    private int strength; //The strength isnt ever uses (currently) this is a place holders to make sure i come around and do it but this doesnt
    // provide any methods
    /*
     * the constructor brick creates a new instance of the brick class with the specified position and size with colour
     * maybe with strength once i am able to add it to the code
     */
    //constructor
    public BrickModel(double x, double y, double width, double height, Color color, int strength) { 
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.strength = strength;
        
    }
    /*
     * this will draw the brick using the imported javaFX 
     */
    //draw methods
    public void draw(GraphicsContext gc) {
        gc.setFill(color); //sets the colour of the brick
        gc.fillRect(x, y, width, height); //this will draw the bricks at its specific location with its size
    }
    //Getter methods
    /*
     * these methods allow accsess to the private fields of this class
     * each method returns one of the values in the fields
     */
    public double getX() {
        return x; //returns x
    }

    public double getY() {
        return y; // returns y
    }

    public double getWidth() {
        return width; //returns width
    }

    public double getHeight() {
        return height; // returns height
    }
}
/* 
 * Note for technical doc!
 * overall the class draws the bricks using the draw method using graphicsContext
 * it allows the paraimters to be laid so that i can edit the shape in the game class
 * the getter methods allows acsess to the private class
 * this is really only basic hopefull strength could work
 * (i could stack in game)
 */