import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ScientificCalculator extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Create the root node
    VBox root = new VBox();

    // Create text field for input/output
    TextField display = new TextField();
    display.setEditable(false);
    root.getChildren().add(display);

    // Create number buttons
    for (int i = 0; i < 10; i++) {
      Button button = new Button(String.valueOf(i));
      root.getChildren().add(button);
    }

    // Create operator buttons
    String[] operators = {"+", "-", "*", "/"};
    for (String operator : operators) {
      Button button = new Button(operator);
      root.getChildren().add(button);
    }
    // Create scientific function buttons
    String[] functions = {"sin", "cos", "tan", "log"};
    for (String func : functions) {
      Button button = new Button(func);
      root.getChildren().add(button);
    }

    // Create the scene
    Scene scene = new Scene(root, 400, 600);

    // Set the scene and show the stage
    primaryStage.setScene(scene);
    primaryStage.setTitle("Scientific Calculator");
    primaryStage.show();
    }
}