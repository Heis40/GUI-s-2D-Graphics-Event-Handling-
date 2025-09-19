
/**
 * Interface for drawable shapes on a JavaFX Canvas.
 */
package drawingapp;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a shape that can be drawn on a JavaFX GraphicsContext.
 */
public interface DrawableShape {
    /**
     * Draws the shape using the provided GraphicsContext.
     * @param gc the GraphicsContext to draw on
     */
    void draw(GraphicsContext gc);
}
