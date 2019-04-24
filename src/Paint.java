import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Paint extends Application {

    final private double DEFAULT_WIDTH = 1080;
    final private double DEFAULT_HEIGHT = 720;

    private Pane root;
    private Scene scene;

    private boolean mousePressed = false;
    //boolean mouseRelease = true;

    private double mouseX, mouseY, mouseReleaseX, mouseReleaseY;
    private double drawSize = 4;

    ArrayList<Shape> blocks = new ArrayList<Shape>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        //init
        root = new Pane();
        scene = new Scene(root);

        primaryStage.setHeight(DEFAULT_HEIGHT);
        primaryStage.setWidth(DEFAULT_WIDTH);
        primaryStage.setScene(scene);
        primaryStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                getMouseEvents(scene);
                draw(scene, root);
            }
        }.start();
    }

    private void getMouseEvents(Scene scene) {

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
                System.out.println("Mouse pressed");
                mousePressed = true;

            }
        });

        scene.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Mouse released");
                mousePressed = false;
            }
        });
    }

    private void draw(Scene scene, Pane root) {

        if (mousePressed) {
            Rectangle block = new Rectangle(drawSize, drawSize);
            block.setX(mouseX);
            block.setY(mouseY);
            blocks.add(block);
            root.getChildren().add(block);
        }
    }
}
