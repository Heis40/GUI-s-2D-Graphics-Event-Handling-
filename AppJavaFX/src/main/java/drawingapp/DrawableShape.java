
package drawingapp;

import javafx.scene.canvas.GraphicsContext;

/**
 * Interface for drawable shapes on a JavaFX Canvas.
 * 
 * This interface defines the contract for shapes that can be drawn
 * on a JavaFX GraphicsContext. Implementing classes should provide
 * the specific drawing logic for their shape type.
 * 
 * @author Your Name
 * @version 1.0
 */
public interface DrawableShape {
    
    /**
     * Draws the shape using the provided GraphicsContext.
     * 
     * Implementing classes should use the GraphicsContext to render
     * their specific shape with appropriate styling, colors, and positioning.
     * 
     * @param gc the GraphicsContext to draw on, must not be null
     */
    void draw(GraphicsContext gc);
}
