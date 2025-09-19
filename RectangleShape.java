
import javafx.scene.canvas.GraphicsContext;
import javafx.scenc.paint.Color;

public class RectangleShape implements DrawableShape 
{
    private final double x, y, width, height;
    private final Color color;

    public RectangleShape(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = Color.RED;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.strokeRect(x, y, width, height);
    }
}