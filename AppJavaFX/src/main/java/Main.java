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
    @Override
    public void start(Stage primaryStage) {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas(screenWidth * 0.8, screenHeight * 0.8);
        root.setCenter(canvas);

    // Controls
    ToggleGroup shapeToggleGroup = new ToggleGroup();
    RadioButton rectBtn = new RadioButton("Rectangle");
    rectBtn.setToggleGroup(shapeToggleGroup);
    rectBtn.setSelected(true);
    RadioButton circBtn = new RadioButton("Circle");
    circBtn.setToggleGroup(shapeToggleGroup);
    Button clearBtn = new Button("Clear Canvas");

    Label countLabel = new Label();
    Label areaLabel = new Label();
    Label statusLabel = new Label();

    HBox controls = new HBox(15, new Label("Shape:"), rectBtn, circBtn, clearBtn, countLabel, areaLabel);
    controls.setPadding(new Insets(10));
    root.setTop(controls);

    // Drawing controller
    DrawingController controller = new DrawingController(canvas, shapeToggleGroup);

    // ListView for shape info
    ObservableList<String> shapeInfoList = FXCollections.observableArrayList();
    ListView<String> shapeListView = new ListView<>(shapeInfoList);
    shapeListView.setPrefWidth(350);
    root.setRight(shapeListView);

        // Shape selection event
        shapeToggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                RadioButton selected = (RadioButton) newToggle;
                controller.setCurrentShapeType(selected.getText());
                statusLabel.setText("Current shape: " + selected.getText());
            }
        });
        statusLabel.setText("Current shape: Rectangle");

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
                if (s instanceof ShapeInfo) {
                    shapeInfoList.add(((ShapeInfo) s).getInfo());
                }
            }
        });

        // Status bar for current shape
        HBox statusBar = new HBox(statusLabel);
        statusBar.setPadding(new Insets(5, 10, 5, 10));
        root.setBottom(statusBar);

        Scene scene = new Scene(root, screenWidth * 0.85, screenHeight * 0.85);

        // Keybindings for shape selection and clear
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case C:
                    circBtn.setSelected(true);
                    statusLabel.setText("Current shape: Circle");
                    break;
                case R:
                    rectBtn.setSelected(true);
                    statusLabel.setText("Current shape: Rectangle");
                    break;
                case SHIFT:
                    if (event.isShiftDown() && !event.isControlDown() && !event.isAltDown() && event.getCode().isModifierKey()) {
                        // Only trigger on left shift, not right shift or with other modifiers
                        clearBtn.fire();
                    }
                    break;
                default:
                    break;
            }
        });

        primaryStage.setTitle("Drawing App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
