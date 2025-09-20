
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleShape implements DrawableShape 
{
    private final double x, y, width, height;
    private final Color color;

    public RectangleShape(double x, double y, double width, double height, Color color) 
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext gc) 
    {
        gc.setStroke(color);
        gc.strokeRect(x, y, width, height);
    }
}