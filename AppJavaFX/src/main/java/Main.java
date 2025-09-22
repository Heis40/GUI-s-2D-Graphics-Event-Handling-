import drawingapp.DrawingController;
import drawingapp.ShapeInfo;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    
    // UI Components
    private Canvas canvas;
    private ToggleGroup shapeToggleGroup;
    private RadioButton rectBtn;
    private RadioButton circBtn;
    private Button clearBtn;
    private Label countLabel;
    private Label areaLabel;
    private Label statusLabel;
    private ListView<String> shapeListView;
    private ObservableList<String> shapeInfoList;
    private DrawingController controller;
    
    @Override
    public void start(Stage primaryStage) {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        // Initialize components
        initializeComponents(screenWidth, screenHeight);
        
        // Setup layout
        BorderPane root = setupLayout();
        
        // Setup event handlers
        setupEventHandlers();
        
        // Create and configure scene
        Scene scene = new Scene(root, screenWidth * 0.85, screenHeight * 0.85);
        setupKeyboardShortcuts(scene);
        
        // Configure and show stage
        primaryStage.setTitle("Drawing App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Initializes all UI components and the drawing controller.
     * 
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     */
    private void initializeComponents(double screenWidth, double screenHeight) {
        // Canvas
        canvas = new Canvas(screenWidth * 0.8, screenHeight * 0.8);
        
        // Shape selection controls
        shapeToggleGroup = new ToggleGroup();
        rectBtn = new RadioButton("Rectangle");
        rectBtn.setToggleGroup(shapeToggleGroup);
        rectBtn.setSelected(true);
        circBtn = new RadioButton("Circle");
        circBtn.setToggleGroup(shapeToggleGroup);
        
        // Buttons
        clearBtn = new Button("Clear Canvas");
        
        // Labels
        countLabel = new Label();
        areaLabel = new Label();
        statusLabel = new Label();
        statusLabel.setText("Current shape: Rectangle");
        
        // Shape info list
        shapeInfoList = FXCollections.observableArrayList();
        shapeListView = new ListView<>(shapeInfoList);
        shapeListView.setPrefWidth(350);
        
        // Drawing controller
        controller = new DrawingController(canvas);
    }
    
    /**
     * Sets up the main layout structure using BorderPane.
     * 
     * @return the configured BorderPane root
     */
    private BorderPane setupLayout() {
        BorderPane root = new BorderPane();
        
        // Center: Canvas
        root.setCenter(canvas);
        
        // Top: Controls
        HBox controls = new HBox(15, new Label("Shape:"), rectBtn, circBtn, clearBtn, countLabel, areaLabel);
        controls.setPadding(new Insets(10));
        root.setTop(controls);
        
        // Right: Shape list
        root.setRight(shapeListView);
        
        // Bottom: Status bar
        HBox statusBar = new HBox(statusLabel);
        statusBar.setPadding(new Insets(5, 10, 5, 10));
        root.setBottom(statusBar);
        
        return root;
    }
    
    /**
     * Sets up all event handlers for UI interactions.
     */
    private void setupEventHandlers() {
        // Shape selection event
        shapeToggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                RadioButton selected = (RadioButton) newToggle;
                controller.setCurrentShapeType(selected.getText());
                statusLabel.setText("Current shape: " + selected.getText());
            }
        });
        
        // Clear button event
        clearBtn.setOnAction(e -> controller.clearCanvas());
        
        // Live updates for count and area
        countLabel.textProperty().bind(Bindings.createStringBinding(
            () -> "Count: " + controller.getShapes().size(),
            controller.getShapes()
        ));
        
        areaLabel.textProperty().bind(Bindings.createStringBinding(
            () -> {
                double total = controller.getShapes().stream()
                    .filter(s -> s instanceof ShapeInfo)
                    .mapToDouble(s -> ((ShapeInfo) s).getArea())
                    .sum();
                return String.format("Total Area: %.1f", total);
            },
            controller.getShapes()
        ));
        
        // Live ListView update
        controller.getShapes().addListener((javafx.collections.ListChangeListener.Change<? extends drawingapp.DrawableShape> c) -> {
            shapeInfoList.clear();
            for (drawingapp.DrawableShape s : controller.getShapes()) {
                if (s instanceof ShapeInfo shapeInfo) {
                    shapeInfoList.add(shapeInfo.getInfo());
                }
            }
        });
    }
    
    /**
     * Sets up keyboard shortcuts for the application.
     * 
     * @param scene the scene to attach keyboard events to
     */
    private void setupKeyboardShortcuts(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case C -> {
                    circBtn.setSelected(true);
                    statusLabel.setText("Current shape: Circle");
                }
                case R -> {
                    rectBtn.setSelected(true);
                    statusLabel.setText("Current shape: Rectangle");
                }
                case SHIFT -> {
                    if (event.isShiftDown() && !event.isControlDown() && !event.isAltDown() && event.getCode().isModifierKey()) {
                        // Only trigger on left shift, not right shift or with other modifiers
                        clearBtn.fire();
                    }
                }
                default -> {
                    // No action for other keys
                }
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
