package drawingapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Controller for managing drawing actions, shape list, and event handling.
 */
public class DrawingController {
    private final ObservableList<DrawableShape> shapes = FXCollections.observableArrayList();
    private final Canvas canvas;
    private final GraphicsContext gc;
    private String currentShapeType = "Rectangle";
    private final ToggleGroup shapeToggleGroup;

    /**
     * Constructs a DrawingController.
     * @param canvas the drawing canvas
     * @param shapeToggleGroup the ToggleGroup for shape selection
     */
    public DrawingController(Canvas canvas, ToggleGroup shapeToggleGroup) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.shapeToggleGroup = shapeToggleGroup;
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        canvas.setOnMouseClicked(this::handleCanvasClick);
    }

    private void handleCanvasClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        DrawableShape shape;
        if (getCurrentShapeType().equals("Rectangle")) {
            shape = new RectShape(x - 30, y - 20, 60, 40, Color.DODGERBLUE);
        } else {
            shape = new CircShape(x, y, 30, Color.ORANGE);
        }
        shapes.add(shape);
        redraw();
    }

    /**
     * Clears all shapes from the canvas and redraws.
     */
    public void clearCanvas() {
        shapes.clear();
        redraw();
    }

    /**
     * Redraws all shapes on the canvas.
     */
    public void redraw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (DrawableShape shape : shapes) {
            shape.draw(gc);
        }
    }

    /**
     * Sets the current shape type to be drawn.
     * @param type the shape type (e.g., "Rectangle" or "Circle")
     */
    public void setCurrentShapeType(String type) {
        this.currentShapeType = type;
    }

    /**
     * Gets the current shape type.
     * @return the current shape type
     */
    public String getCurrentShapeType() {
        return currentShapeType;
    }

    /**
     * Returns the observable list of drawn shapes.
     * @return the observable list of DrawableShape
     */
    public ObservableList<DrawableShape> getShapes() {
        return shapes;
    }
}
