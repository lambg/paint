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
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Button drawButton, eraserButton, clearButton, lineThin, lineRgular, lineThick;
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

        ToolBar leftToolbarColOne = new ToolBar();
        leftToolbarColOne.setOrientation(Orientation.VERTICAL);
        leftToolbarColOne.prefHeightProperty().bind(primaryStage.heightProperty());
        leftToolbarColOne.setPrefWidth(40);

        ToolBar leftToolbarColTwo = new ToolBar();
        leftToolbarColTwo.setOrientation(Orientation.VERTICAL);
        leftToolbarColTwo.prefHeightProperty().bind(primaryStage.heightProperty());
        leftToolbarColTwo.setPrefWidth(40);

        Image drawIcon = new Image(getClass().getResourceAsStream("res/brush.png"));
        drawButton = new Button();
        drawButton.setMaxWidth(10);
        drawButton.setStyle("-fx-background-color: #FEFEFE");
        drawButton.setGraphic(new ImageView(drawIcon));

        Image eraserIcon = new Image(getClass().getResourceAsStream("res/eraser.png"));
        eraserButton = new Button();
        eraserButton.setStyle("-fx-background-color: #FEFEFE");
        eraserButton.setGraphic(new ImageView(eraserIcon));

        Image clearIcon = new Image(getClass().getResourceAsStream("res/clear.png"));
        clearButton = new Button();
        clearButton.setStyle("-fx-background-color: #FEFEFE");
        clearButton.setGraphic(new ImageView(clearIcon));

        Image lineOptionThin = new Image(getClass().getResourceAsStream("res/line1.png"));
        Image lineOptionRegular = new Image(getClass().getResourceAsStream("res/line2.png"));

        lineThin = new Button();
        lineThin.setStyle("-fx-background-color: #FEFEFE");
        lineThin.setGraphic(new ImageView(lineOptionThin));

        lineRgular = new Button();
        lineRgular.setStyle("-fx-background-color: #FEFEFE");
        lineRgular.setGraphic(new ImageView(lineOptionRegular));

        leftToolbarColOne.getItems().add(drawButton);
        leftToolbarColOne.getItems().add(eraserButton);
        leftToolbarColOne.getItems().add(clearButton);
        leftToolbarColOne.getItems().add(new Separator());

        leftToolbarColOne.getItems().add(lineThin);
        leftToolbarColOne.getItems().add(lineRgular);
        leftToolbarColOne.getItems().add(new Separator());

        VBox leftBarColOne = new VBox(leftToolbarColOne);
        leftBarColOne.setSpacing(5);
        leftBarColOne.setMinHeight(DEFAULT_HEIGHT);
        root.getChildren().add(leftBarColOne);


        //todo - fix the second toolbar so that it doesn't interfere with the buttons in the first one
//        VBox tmp = new VBox();
//        tmp.setTranslateX(300);
//        tmp.setPrefWidth(100);


    }

    private void getMouseEvents(Canvas canvas, GraphicsContext gc) {

        drawButton.setOnMouseClicked(event -> draw = true);
        eraserButton.setOnMouseClicked(event -> draw = false);
        clearButton.setOnMouseClicked(event -> gc.clearRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT));

        lineThin.setOnMouseClicked(event -> {
            if (size > 5) size -= 3;
        });

        lineRgular.setOnMouseClicked(event -> size += 3);

        canvas.setOnMouseDragged(event -> {
            x = event.getX();
            y = event.getY();

            if (draw) {
                gc.setFill(Color.RED);
                gc.fillOval(x - size / 2, y - size / 2, size, size);
            } else {
                gc.clearRect(x - size / 2, y - size / 2, size, size);
            }
        });


    }
}
