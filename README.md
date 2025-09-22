Why did I structure the GUI and logic the way I did?

-It made it extremely simple to use the source code that I had and build off of that. The structure was already there, all I did was modify it to fit the drawing requirements. The DrawableShape Interface made it easier to sort the shape options since they both are similar in the context of this app.

Feature overview:

- A JavaFX Application that draws circles and squares

How to compile and run:

Navigate to the folder labeled AppJavaFX and copy the path into your terminal

Ex: 

```
cd "C:\...\...\...\GUI-s-2D-Graphics-Event-Handling-\AppJavaFX"
```

Use the command:

```
mvn javafx:run
```


Which levels you completed

- Levels 1-3 are completed



AI Reflection

What AI tools did you use?

- I used the copilot assistant in vscode

What suggestions were useful or misleading?

- I was actually recommended from the beginning to add keybinds for the shapes

What did you revise or reject from AI output, and why?

- I was recommended to make an actual manual draw system for drawing shapes which I rejected since that was not the goal of this assignment.

Extra Reflection: 

Include your AI prompt and the returned code.

Prompt: 

- Display shape info on the screen (e.g., a live Label with count, total area; or a ListView of shapes). Real-time updates using JavaFX bindings (e.g., bind a label to shapes.sizeProperty() or a computed DoubleProperty for total area). Tooltips or status text for “current selected shape.”

I had copilot gather all the relevant code since it was scattered throughout the project:

```
import drawingapp.ShapeInfo;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class Reflection {

    // ========== UI COMPONENTS FOR SHAPE INFO DISPLAY ==========
    
    // Labels for live count and area display
    private Label countLabel;
    private Label areaLabel;
    private Label statusLabel;  // Status text for "current selected shape"
    
    // ListView for displaying all shapes
    private ListView<String> shapeListView;
    private ObservableList<String> shapeInfoList;
    
    // Shape selection controls
    private ToggleGroup shapeToggleGroup;
    private RadioButton rectBtn;
    private RadioButton circBtn;

    // ========== COMPONENT INITIALIZATION ==========
    
    /**
     * Initialize UI components for shape info display
     */
    private void initializeShapeInfoComponents() {
        // Labels for real-time display
        countLabel = new Label();
        areaLabel = new Label();
        statusLabel = new Label();
        statusLabel.setText("Current shape: Rectangle");  // Default status
        
        // Shape info list - ObservableList automatically updates ListView
        shapeInfoList = FXCollections.observableArrayList();
        shapeListView = new ListView<>(shapeInfoList);
        shapeListView.setPrefWidth(350);
        
        // Shape selection controls
        shapeToggleGroup = new ToggleGroup();
        rectBtn = new RadioButton("Rectangle");
        rectBtn.setToggleGroup(shapeToggleGroup);
        rectBtn.setSelected(true);
        circBtn = new RadioButton("Circle");
        circBtn.setToggleGroup(shapeToggleGroup);
    }

    // ========== LAYOUT SETUP ==========
    
    /**
     * Setup layout with shape info display components
     */
    private void setupShapeInfoLayout(javafx.scene.layout.BorderPane root) {
        // Top: Controls with live count and area labels
        HBox controls = new HBox(15, 
            new Label("Shape:"), rectBtn, circBtn, 
            countLabel,  // Live count display
            areaLabel    // Live total area display
        );
        controls.setPadding(new Insets(10));
        root.setTop(controls);
        
        // Right: ListView showing all shapes
        root.setRight(shapeListView);
        
        // Bottom: Status bar showing current selected shape
        HBox statusBar = new HBox(statusLabel);
        statusBar.setPadding(new Insets(5, 10, 5, 10));
        root.setBottom(statusBar);
    }

    // ========== REAL-TIME UPDATES USING JAVAFX BINDINGS ==========
    
    /**
     * Setup real-time updates using JavaFX bindings
     * This demonstrates binding labels to observable properties for automatic updates
     */
    private void setupRealTimeBindings(drawingapp.DrawingController controller) {
        
        // LIVE COUNT BINDING - automatically updates when shapes list changes
        countLabel.textProperty().bind(Bindings.createStringBinding(
            () -> "Count: " + controller.getShapes().size(),
            controller.getShapes()  // Bind to ObservableList - updates automatically
        ));
        
        // LIVE TOTAL AREA BINDING - computed property that recalculates automatically
        areaLabel.textProperty().bind(Bindings.createStringBinding(
            () -> {
                double total = controller.getShapes().stream()
                    .filter(s -> s instanceof ShapeInfo)  // Only shapes with area info
                    .mapToDouble(s -> ((ShapeInfo) s).getArea())  // Get area from each
                    .sum();  // Calculate total
                return String.format("Total Area: %.1f", total);
            },
            controller.getShapes()  // Dependency - recalculates when list changes
        ));
        
        // LIVE LISTVIEW UPDATE - listener for real-time shape list updates
        controller.getShapes().addListener(
            (javafx.collections.ListChangeListener.Change<? extends drawingapp.DrawableShape> c) -> {
                shapeInfoList.clear();  // Clear existing items
                // Repopulate with current shapes
                for (drawingapp.DrawableShape s : controller.getShapes()) {
                    if (s instanceof ShapeInfo shapeInfo) {
                        shapeInfoList.add(shapeInfo.getInfo());  // Add formatted info
                    }
                }
            }
        );
    }

    // ========== STATUS TEXT FOR CURRENT SELECTED SHAPE ==========
    
    /**
     * Setup status updates for current selected shape (tooltip/status text)
     */
    private void setupCurrentShapeStatus(drawingapp.DrawingController controller) {
        // Shape selection event listener - updates status text
        shapeToggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                RadioButton selected = (RadioButton) newToggle;
                // Update controller
                controller.setCurrentShapeType(selected.getText());
                // Update status text display
                statusLabel.setText("Current shape: " + selected.getText());
            }
        });
    }

    // ========== SHAPE INFO INTERFACE AND IMPLEMENTATIONS ==========
    
    /**
     * Interface for shapes that provide display information
     * This enables polymorphic access to shape data for UI display
     */
    public interface ShapeInfoExample {
        double getArea();        // For total area calculation
        String getType();        // For shape identification
        String getInfo();        // For ListView display - formatted string
    }
    
    /**
     * Example Rectangle implementation with info display capabilities
     */
    public static class RectangleExample implements ShapeInfoExample {
        private final double x, y, width, height;
        
        public RectangleExample(double x, double y, double width, double height) {
            this.x = x; this.y = y; this.width = width; this.height = height;
        }
        
        @Override
        public double getArea() {
            return width * height;  // Used in total area binding
        }
        
        @Override
        public String getType() {
            return "Rectangle";  // Used for shape identification
        }
        
        @Override
        public String getInfo() {
            // Formatted string for ListView display - shows position, size, and area
            return String.format("Rectangle at (%.1f, %.1f), %.1fx%.1f, Area: %.1f", 
                                x, y, width, height, getArea());
        }
    }
    
    /**
     * Example Circle implementation with info display capabilities
     */
    public static class CircleExample implements ShapeInfoExample {
        private final double centerX, centerY, radius;
        
        public CircleExample(double centerX, double centerY, double radius) {
            this.centerX = centerX; this.centerY = centerY; this.radius = radius;
        }
        
        @Override
        public double getArea() {
            return Math.PI * radius * radius;  // Used in total area binding
        }
        
        @Override
        public String getType() {
            return "Circle";  // Used for shape identification
        }
        
        @Override
        public String getInfo() {
            // Formatted string for ListView display - shows center, radius, and area
            return String.format("Circle at (%.1f, %.1f), r=%.1f, Area: %.1f", 
                                centerX, centerY, radius, getArea());
        }
    }
}
```


Briefly explain what you used, ignored, or changed.

- I used all of the code shown above, I don't believe I had to change that much

Extra Reflection

Use AI for documentation, input handling, or logic.

Submit the prompt(s) and AI output.

Prompt:

- Rework the subclasses into RectShape and CircleShape that implement a shared DrawableShape Interface.

Output: 

- created the subclasses as well as the interface

Provide your critique and changes.

- I had to delete the old shape classes since copilot could not, but other than that it worked as requested