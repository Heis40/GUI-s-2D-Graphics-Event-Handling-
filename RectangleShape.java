
/**
 * A class representing a rectangle shape that can be drawn on a canvas.
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleShape implements DrawableShape 
{
    private final double x, y, width, height;
    private final Color color;

    /**
     * Constructor to initialize rectangle properties.
     * @param x The x-coordinate of the rectangle.
     * @param y The y-coordinate of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color The color of the rectangle.
     */

    public RectangleShape(double x, double y, double width, double height, Color color) 
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Draw the rectangle on the provided GraphicsContext.
     * @param gc The GraphicsContext to draw on.
     */
    @Override
    public void draw(GraphicsContext gc) 
    {
        gc.setStroke(color);
        gc.strokeRect(x, y, width, height);
    }

    /**
     * Get the area of the rectangle.
     * @return The area of the rectangle.
     */
    @Override
    public double getArea() 
    {
        return width * height;
    }
}