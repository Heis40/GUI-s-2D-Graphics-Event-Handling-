package drawingapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a drawable circle shape.
 */
public class CircShape implements DrawableShape, ShapeInfo {
    private final double centerX, centerY, radius;
    private final Color color;

    /**
     * Constructs a CircShape.
     * @param centerX the x coordinate of the center
     * @param centerY the y coordinate of the center
     * @param radius the radius of the circle
     * @param color the fill color
     */
    public CircShape(double centerX, double centerY, double radius, Color color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }

    /** {@inheritDoc} */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
    }

    /** {@inheritDoc} */
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    /** {@inheritDoc} */
    @Override
    public String getType() {
        return "Circle";
    }

    /** {@inheritDoc} */
    @Override
    public String getInfo() {
        return String.format("Circle at (%.1f, %.1f), r=%.1f, Area: %.1f", centerX, centerY, radius, getArea());
    }
}
