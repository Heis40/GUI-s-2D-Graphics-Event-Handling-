import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.layout.Border;
import javafx.scene.canvas.Canvas;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;


public class DrawingController 
{
    private final ObservableList<DrawableShape> shapes = FXCollections.observableArrayList();
    private final Canvas canvas;
    private final GraphicsContext gc;
    

    public DrawingController(Canvas canvas) 
    {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public void addShape(DrawableShape shape) 
    {
        shapes.add(shape);
    }
    public void clearShapes() 
    {
        shapes.clear();
    }

    public Canvas getCanvas() 
    {
        return canvas;
    }

    public void drawAll() 
    {
        for (DrawableShape shape : shapes) {
            shape.draw(gc);
        }
    }

}