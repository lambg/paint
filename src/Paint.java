import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Paint extends Application {

    final private double DEFAULT_WIDTH = 1080;
    final private double DEFAULT_HEIGHT = 720;

    private Pane root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;

    private Button drawButton, eraseButton, clearButton;
    private boolean draw = true;


    private double x, y;
    private double size = 4;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        initialize(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.show();

        getMouseEvents(canvas, gc);
    }

    private void initialize(Stage primaryStage) {
        root = new Pane();
        scene = new Scene(root);
        primaryStage.setTitle("JavaFX Paint by Greg Lamb");
        primaryStage.setHeight(DEFAULT_HEIGHT);
        primaryStage.setWidth(DEFAULT_WIDTH);

        canvas = new Canvas(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        ToolBar leftToolbar = new ToolBar();
        leftToolbar.setOrientation(Orientation.VERTICAL);
        leftToolbar.prefHeightProperty().bind(primaryStage.heightProperty());

        drawButton = new Button("Draw");
        leftToolbar.getItems().add(drawButton);
        eraseButton = new Button("Erase");
        leftToolbar.getItems().add(eraseButton);
        clearButton = new Button("Clear");
        leftToolbar.getItems().add(clearButton);

        VBox leftBar = new VBox(leftToolbar);
        leftBar.setMinWidth(100);
        leftBar.setMinHeight(DEFAULT_HEIGHT);
        root.getChildren().add(leftBar);

    }

    private void getMouseEvents(Canvas canvas, GraphicsContext gc) {

        drawButton.setOnMouseClicked(event -> draw = true);
        eraseButton.setOnMouseClicked(event -> draw = false);
        clearButton.setOnMouseClicked(event -> gc.clearRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT));

        canvas.setOnMouseDragged(event -> {
            x = event.getX();
            y = event.getY();

            if (draw) {
                gc.setFill(Color.RED);
                gc.fillRect(x, y, size, size);
            } else {
                gc.clearRect(x, y, size, size);
            }
        });


    }
}
