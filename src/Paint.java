import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Paint extends Application {

    final double DEFAULT_WIDTH = 1080;
    final double DEFAULT_HEIGHT = 720;

    private Pane root;
    private Scene scene;

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

                //update everything

            }
        }.start();

    }
}
