


import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.Priority;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
//import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.binding.Bindings;




public class App extends Application 
{
    private ObservableList<DrawableShape> shapes = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setTitle("Drawing Shapes");
         
    // Use a safe fixed canvas size and only add it to the center
    BorderPane root = new BorderPane();
    Canvas canvas = new Canvas(800, 500); // Safe fixed size
    root.setCenter(canvas);
    GraphicsContext gc = canvas.getGraphicsContext2D();

          Label areaLabel = new Label();
          DoubleProperty totalArea = new SimpleDoubleProperty();
          areaLabel.textProperty().bind(totalArea.asString("Total Area: %.2f"));

          shapes.addListener((javafx.collections.ListChangeListener.Change<? extends DrawableShape> change) -> {
              double area = 0;
              for (DrawableShape shape : shapes) {
                  area += shape.getArea();
              }
              totalArea.set(area);
          });

          Label countLabel = new Label();
          countLabel.textProperty().bind(Bindings.size(shapes).asString("Shapes: %d"));

          ListView<DrawableShape> shapeListView = new ListView<>(shapes);

          Label selectedShapeLabel = new Label();
            shapeListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    selectedShapeLabel.setText("Selected Shape Area: " + String.format("%.2f", newSelection.getArea()));
                } else {
                    selectedShapeLabel.setText("No Shape Selected");
                }
            });

          // Controls
          ToggleGroup shapeToggle = new ToggleGroup();
          RadioButton rbRectangle = new RadioButton("Rectangle");
          rbRectangle.setToggleGroup(shapeToggle);
          rbRectangle.setSelected(true);
          RadioButton rbCircle = new RadioButton("Circle");
          rbCircle.setToggleGroup(shapeToggle);
          Button clear  = new Button("Clear");  

          


        //Change action to reflect drawing shapes
        rbCircle.setOnAction(e -> {
            shapes.add(new CircleShape(200, 200, 50, Color.BLUE)); // Add shape to list
            redrawAll(gc); // Redraw all shapes using gc
        });

        rbRectangle.setOnAction(e -> {
            shapes.add(new RectangleShape(50, 50, 100, 100, Color.RED)); // Add shape to list
            redrawAll(gc); // Redraw all shapes using gc
        });

        clear.setOnAction(e -> {
            shapes.clear(); // Clear the list of shapes
            redrawAll(gc); // Redraw all shapes using gc
        });

        VBox vbox = new VBox(10, new Label("Shape: "), rbCircle, rbRectangle, clear, areaLabel, countLabel, selectedShapeLabel, shapeListView);
        root.setLeft(vbox);
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    // Do not add the canvas to multiple parents or layouts


    }
     private void redrawAll(GraphicsContext gc) 
          {
              gc.clearRect(0, 0, 800, 500); // Clear canvas
              for (DrawableShape shape : shapes) {
                  shape.draw(gc);
              }
          }

     public static void main(String[] args) 
        {
            launch(args);
        }
}
