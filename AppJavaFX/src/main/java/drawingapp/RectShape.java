package drawingapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a drawable rectangle shape.
 */
public class RectShape implements DrawableShape, ShapeInfo {
    private final double x, y, width, height;
    private final Color color;

    /**
     * Constructs a RectShape.
     * @param x the x coordinate of the top-left corner
     * @param y the y coordinate of the top-left corner
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the fill color
     */
    public RectShape(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /** {@inheritDoc} */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }

    /** {@inheritDoc} */
    @Override
    public double getArea() {
        return width * height;
    }

    /** {@inheritDoc} */
    @Override
    public String getType() {
        return "Rectangle";
    }

    /** {@inheritDoc} */
    @Override
    public String getInfo() {
        return String.format("Rectangle at (%.1f, %.1f), %.1fx%.1f, Area: %.1f", x, y, width, height, getArea());
    }
}
