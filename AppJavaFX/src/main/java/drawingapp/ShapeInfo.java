package drawingapp;

/**
 * Interface for shapes that can provide information about themselves.
 * 
 * This interface defines methods that shapes should implement to provide
 * details about their properties such as area, type, and formatted information
 * for display purposes.
 * 
 * @author Your Name
 * @version 1.0
 */
public interface ShapeInfo {
    
    /**
     * Calculates and returns the area of the shape.
     * 
     * @return the area of the shape as a double value
     */
    double getArea();
    
    /**
     * Returns the type name of the shape.
     * 
     * @return a String representing the shape type (e.g., "Rectangle", "Circle")
     */
    String getType();
    
    /**
     * Returns a formatted string containing detailed information about the shape.
     * 
     * This typically includes the shape type, position, dimensions, and area
     * in a human-readable format suitable for display in lists or logs.
     * 
     * @return a formatted String with comprehensive shape information
     */
    String getInfo();
}
