import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

public class DisplayOnScreen extends Application {
    private MascotDragHandler dragHandler;

    private MascotSwitch mascotSwitch;
    private Image image;
    private ImageView imageView;
    private Pane layout;
    private Scene displayedScene;
    private int currentStateIndex;
    private final MascotState[] states = MascotState.values();


    @Override
    public void start(Stage primaryStage) {
        mascotSwitch = new MascotSwitch();

        image = mascotSwitch.getMascotImage();
        imageView = new ImageView(image);
        imageView.setFitHeight(360);
        imageView.setFitWidth(360);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(36);
        imageView.setLayoutY(36);

        dragHandler = new MascotDragHandler(primaryStage);

        imageView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                do {
                    currentStateIndex = (int) (Math.random() * states.length);
                } while (states[currentStateIndex] == mascotSwitch.getMascotState()
                        || states[currentStateIndex] == MascotState.THINKING);

                MascotState newState = states[currentStateIndex];

                mascotSwitch.setMascotRender(newState);
                imageView.setImage(mascotSwitch.getMascotImage());
                System.out.println("Current state: " + mascotSwitch.getMascotState());
            }
        });

        imageView.setOnMousePressed(event -> {
            dragHandler.mousePressedHandler(event);
        });

        imageView.setOnMouseDragged(event -> {
            double[] dragCoordination = dragHandler.mouseDraggedHandler(event);

            primaryStage.setX(dragCoordination[0]);
            primaryStage.setY(dragCoordination[1]);
        });

        layout = new Pane();
        layout.getChildren().add(imageView);

        displayedScene = new Scene(layout);
        displayedScene.setFill(Color.TRANSPARENT);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(displayedScene);
        primaryStage.setTitle("Marin Kitagawa");
        primaryStage.show();

        /*ConsoleTest test = new ConsoleTest();
        Thread consoleTest = new Thread(() -> {
            test.testFromKeyboard(this.mascotSwitch, this.imageView);
        });
        consoleTest.start();*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
