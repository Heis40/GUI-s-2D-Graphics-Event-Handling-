import javafx.scene.canvas.GraphicsContext;

/**
 * Interface for drawable shapes.
 */
public interface DrawableShape 
{
    void draw(GraphicsContext gc);
    double getArea();
}   