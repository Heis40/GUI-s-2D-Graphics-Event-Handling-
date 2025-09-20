import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleShape implements DrawableShape 
{
    private final double centerX, centerY, radius;
    private final Color color;

    /**
     * Constructor to initialize circle properties.
     * @param centerX The x-coordinate of the circle's center.
     * @param centerY The y-coordinate of the circle's center.
     * @param radius The radius of the circle.
     * @param color The color of the circle.
     */
    public CircleShape(double centerX, double centerY, double radius, Color color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }

    /**
     * Draw the circle on the provided GraphicsContext.
     * @param gc The GraphicsContext to draw on.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.strokeOval(centerX, centerY, radius * 2, radius * 2);
    }

    /**
     * Get the area of the circle.
     * @return The area of the circle.
     */
    @Override
    public double getArea() 
    {
        return Math.PI * radius * radius;
    }

    
}