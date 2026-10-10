package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

import mascot.MascotDragHandler;
import mascot.MascotInteractionHandler;
import mascot.MascotState;
import mascot.MascotSwitch;
import menu.RadialMenu;
import menu.RadialMenuItem;
import menu.RadialMenuUI;
import pomodoro.PomodoroListener;
import pomodoro.PomodoroManager;
import pomodoro.PomodoroPhase;
import speech.MockSpeeches;
import speech.SpeechBubble;

import java.util.List;

public class DisplayOnScreen extends Application {

    // Handles the mascot's dragging behavior and calculates its new screen position.
    private MascotDragHandler dragHandler;

    // Manages the mascot's current state and corresponding image.
    private MascotSwitch mascotSwitch;

    // Displays the current mascot image.
    private Image image;

    // JavaFX component used to display the mascot image on screen.
    private ImageView imageView;

    // Root container that holds the mascot and the radial menu.
    private Pane layout;

    // Scene displayed inside the application window.
    private Scene displayedScene;

    // Handles mouse interactions such as single-click and double-click.
    private MascotInteractionHandler mascotInteractionHandler;

    // Enable to take mock-up speech.
    private MockSpeeches mockSpeeches;

    // White frame of bubble speech displayed.
    private SpeechBubble speechBubble;


    @Override
    public void start(Stage primaryStage) {

        // =========================================================
        // UI SIZE CONFIGURATION
        // Defines the size of the Scene and mascot.
        // mascotOffset moves the mascot slightly downward
        // instead of placing it exactly at the vertical center.
        // =========================================================

        int sceneHeight = 450;
        int sceneWidth = 480;

        int mascotHeight = 300;
        int mascotWidth = 300;

        int mascotOffset = 20;


        // Calculate the mascot's top-left position
        // so that it is centered horizontally and slightly
        // shifted downward vertically.
        int mascotCoordinationX =
                (sceneWidth - mascotWidth) / 2;

        int mascotCoordinationY =
                ((sceneHeight - mascotHeight) / 2) + mascotOffset;


        // =========================================================
        // MASCOT INITIALIZATION
        // Creates the mascot state manager and loads
        // the image corresponding to its initial state.
        // =========================================================

        mascotSwitch = new MascotSwitch();

        image = mascotSwitch.getMascotImage();

        imageView = new ImageView(image);

        imageView.setFitHeight(mascotHeight);
        imageView.setFitWidth(mascotWidth);
        imageView.setPreserveRatio(true);

        // Place the mascot at the calculated position.
        imageView.setLayoutX(mascotCoordinationX);
        imageView.setLayoutY(mascotCoordinationY);


        // =========================================================
        // MAIN UI CONTAINER
        // Creates the root Pane and adds the mascot to it.
        // =========================================================

        layout = new Pane();
        layout.setBackground(Background.EMPTY);
        layout.getChildren().add(imageView);

        // =========================================================

        // BUBBLE SPEECH TEST UI
        // Creates a bubble speech beyond the mascot.
        // A string from mock-up are randomly picked to use as
        // what mascot say
        // =========================================================

        // Create the mock speech provider.
        mockSpeeches = new MockSpeeches();

        // Create the speech bubble.
        speechBubble = new SpeechBubble(mockSpeeches);

        // Set position beyond mascot
        speechBubble.setLayoutX(mascotCoordinationX - 30);
        speechBubble.setLayoutY(mascotCoordinationY - 100);

        // Add the bubble to the same layout as the mascot.
        layout.getChildren().add(speechBubble);

        // RADIAL MENU TEST UI
        // Creates a radial menu around the mascot.
        // The four circles are temporary graphics used
        // to test the menu's positioning behavior.
        // =========================================================

        RadialMenuUI radialMenuUI = new RadialMenuUI();
        RadialMenu radialMenu = new RadialMenu(imageView);
        List<RadialMenuItem> rawOrbs = radialMenuUI.createItems(pomodoroManager::toggle);
        for (RadialMenuItem r : rawOrbs) {
            radialMenu.addItem(r);
        }

        // Add the radial menu to the main UI container.
        layout.getChildren().add(radialMenu);


        // =========================================================
        // MASCOT INTERACTION
        // Delegates click and double-click behavior
        // to mascot.MascotInteractionHandler.
        // =========================================================

        mascotInteractionHandler = new MascotInteractionHandler(imageView, mascotSwitch, radialMenu, speechBubble);
        imageView.setOnMouseClicked(event -> mascotInteractionHandler.setup(event));


        // =========================================================
        // MASCOT DRAGGING
        // Delegates drag calculations to mascot.MascotDragHandler.
        // The calculated coordinates are then applied
        // to the application Stage.
        // =========================================================

        dragHandler = new MascotDragHandler(primaryStage);

        imageView.setOnMousePressed(event -> {
            dragHandler.mousePressedHandler(event);
        });

        imageView.setOnMouseDragged(event -> {

            double[] dragCoordination =
                    dragHandler.mouseDraggedHandler(event);

            primaryStage.setX(dragCoordination[0]);
            primaryStage.setY(dragCoordination[1]);
        });


        // =========================================================
        // SCENE SETUP
        // Creates the Scene using the main layout and
        // makes its background transparent.
        // =========================================================
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        displayedScene = new Scene(layout, sceneWidth, sceneHeight);
        displayedScene.setFill(Color.TRANSPARENT);


        // =========================================================
        // STAGE SETUP
        // Configures the application window as transparent,
        // attaches the Scene, gives the window a title,
        // and displays it.
        // =========================================================

        primaryStage.setScene(displayedScene);
        primaryStage.setTitle("Marin Kitagawa");
        primaryStage.show();


        // =========================================================
        // CONSOLE STATE TEST
        // Optional testing code for changing mascot state
        // from console input. Currently disabled.
        // =========================================================

        /*
        app.ConsoleTest test = new app.ConsoleTest();

        Thread consoleTest = new Thread(() -> {
            test.testFromKeyboard(
                    this.mascotSwitch,
                    this.imageView
            );
        });

        consoleTest.start();
        */
    }

    PomodoroManager pomodoroManager = new PomodoroManager(
            new PomodoroListener() {
                @Override
                public void onTick(PomodoroPhase phase, int remainingSeconds, String formattedTime) {
                    javafx.application.Platform.runLater(() -> {
                        speechBubble.setText(formattedTime);
                    });
                }

                @Override
                public void onPhaseChange(PomodoroPhase newPhase, String announcement) {
                    javafx.application.Platform.runLater(() -> {
                        switch (newPhase) {
                            case WORK -> {
                                // Update mascot sprite & notify
                                mascotSwitch.setMascotRender(MascotState.STUDYING); // or MascotSwitch.switchState if static
                                speechBubble.setText(announcement + " (25m)");     // adjust to your SpeechBubble method
                            }
                            case SHORT_BREAK, LONG_BREAK -> {
                                mascotSwitch.setMascotRender(MascotState.HAPPY);
                                speechBubble.setText(announcement);
                            }
                            case STOPPED -> {
                                mascotSwitch.setMascotRender(MascotState.IDLE);
                                speechBubble.setText("Back to chill mode!");
                            }
                        }
                    });
                }

                @Override
                public void onComplete() {
                    javafx.application.Platform.runLater(() -> {
                        speechBubble.setText("All done! Fantastic work today!");
                    });
                }
            }

    );

    // Entry point of the JavaFX application.
    public static void main(String[] args) {
        launch(args);
    }
}