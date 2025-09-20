import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleShape implements DrawableShape 
{
    private final double centerX, centerY, radius;
    private final Color color;

    public CircleShape(double centerX, double centerY, double radius, Color color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.strokeOval(centerX, centerY, radius * 2, radius * 2);
    }

    @Override
    public double getArea() 
    {
        return Math.PI * radius * radius;
    }

    
}