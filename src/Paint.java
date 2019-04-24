import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Timer;


public class Paint extends Application {

    final private double DEFAULT_WIDTH = 1080;
    final private double DEFAULT_HEIGHT = 720;

    private Pane root;
    private Scene scene;

    private Button draw, rect, clear;

    private boolean mousePressed = false;
    //boolean mouseRelease = true;

    private double mouseX, mouseY, startX, startY;
    private double drawSize = 4;

    ArrayList<Shape> blocks = new ArrayList<Shape>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        root = new Pane();
        scene = new Scene(root);

        //todo - create an setup method init()

        primaryStage.setTitle("JavaFX Paint by Greg Lamb");
        primaryStage.setHeight(DEFAULT_HEIGHT);
        primaryStage.setWidth(DEFAULT_WIDTH);
        primaryStage.setScene(scene);
        primaryStage.show();

        ToolBar leftToolbar = new ToolBar();
        leftToolbar.setOrientation(Orientation.VERTICAL);
        leftToolbar.prefHeightProperty().bind(primaryStage.heightProperty());

        draw = new Button("Draw");
        leftToolbar.getItems().add(draw);
        rect = new Button("rect");
        leftToolbar.getItems().add(rect);
        clear = new Button("Clear");
        leftToolbar.getItems().add(clear);

        VBox leftBar = new VBox(leftToolbar);
        leftBar.setMinWidth(100);
        leftBar.setMinHeight(DEFAULT_HEIGHT);
        root.getChildren().add(leftBar);


        new AnimationTimer() {
            @Override
            public void handle(long now) {
                getMouseEvents(scene);
                //todo - get buttonEvents
                buttonEvents(scene, root);
                draw(scene, root);
            }
        }.start();
    }

    private void buttonEvents(Scene scene, Pane root) {

        clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (Shape shape : blocks) {
                    root.getChildren().remove(shape);
                }
            }
        });

    }

    private void getMouseEvents(@org.jetbrains.annotations.NotNull Scene scene) {

        scene.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseX = event.getX();
                mouseY = event.getY();
            }
        });

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mousePressed = true;
            }
        });

        scene.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mousePressed = false;
            }
        });
    }

    private void draw(Scene scene, Pane root) {
        Rectangle block = null;

        //creates the blocks
        if (mousePressed && mouseInDrawRange()) {
            block = new Rectangle(drawSize, drawSize);
            block.setX(mouseX);
            block.setY(mouseY);
            blocks.add(block);
        }

        //adds the blocks to scene
        for (Shape shape : blocks) {
            if (block != null) {
                try {
                    root.getChildren().add(block);
                } catch (Exception IllegalArgumentException) {
                }
            }
        }
    }

    private boolean mouseInDrawRange() {
        return mouseX > 50; //todo - change to toolbar width
    }
}
