import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.layout.Border;
import javafx.scene.canvas.Canvas;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Controller class to manage drawing operations on the canvas.
 */
public class DrawingController 
{
    private final ObservableList<DrawableShape> shapes = FXCollections.observableArrayList();
    private final Canvas canvas;
    private final GraphicsContext gc;
    
 /**
     * Constructor to initialize the DrawingController with a canvas.
     * @param canvas The canvas where shapes will be drawn.
     */
    public DrawingController(Canvas canvas) 
    {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    /**
     * Add a shape to the list and redraw the canvas.
     * @param shape The shape to be added.
     */
    public void addShape(DrawableShape shape) 
    {
        shapes.add(shape);
    }

    /**
     * Clear all shapes from the list and redraw the canvas.
     */
    public void clearShapes() 
    {
        shapes.clear();
    }

    /**
     * Get the canvas associated with this controller.
     * @return The canvas.
     */
    public Canvas getCanvas() 
    {
        return canvas;
    }

    /**
     * Draw all shapes on the canvas.
     */
    public void drawAll() 
    {
        for (DrawableShape shape : shapes) {
            shape.draw(gc);
        }
    }

}