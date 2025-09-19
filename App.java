
import javafx.scene.canvas.Canvas;

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
    private ObservableList<String> items = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");

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
        rbCircle.setOnMouseClicked(e -> {
            gc.strokeOval(200, 200, 50, 50);
        });

        rbRectangle.setOnMouseClicked(e -> {
            gc.strokeRect(50, 50, 100, 100);
        });

        clear.setOnMouseClicked(e -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());  
        });

        VBox vbox = new VBox(10, rbCircle, rbRectangle, clear);
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        VBox.setVgrow(canvas, Priority.ALWAYS);
        vbox.getChildren().add(canvas);


    }
     public static void main(String[] args) 
        {
            launch(args);
        }
}