
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.Priority;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class App extends Application 
{
    private ObservableList<DrawableShape> shapes = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setTitle("Drawing Shapes");
         
         // Canvas
          Canvas canvas = new Canvas(800, 500);
          GraphicsContext gc = canvas.getGraphicsContext2D();

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

        VBox vbox = new VBox(10, rbCircle, rbRectangle, clear);
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        VBox.setVgrow(canvas, Priority.ALWAYS);
        vbox.getChildren().add(canvas);


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