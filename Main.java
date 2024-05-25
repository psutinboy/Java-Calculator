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

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    ScientificCalculator calculator = new ScientificCalculator();
    calculator.start(primaryStage);
  }

  public static void main(String[] args) {
    launch(args);
  }
}