package mascot;

import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import menu.RadialMenu;
import speech.SpeechBubble;

public class MascotInteractionHandler {

    // Reference to the mascot's ImageView for updating its displayed image.
    private final ImageView imageView;

    // Manages the mascot's current state and state-related images.
    private final MascotSwitch mascotSwitch;

    // Controls the radial menu shown around the mascot.
    private final RadialMenu radialMenu;

    // Stores whether the radial menu is currently displayed.
    private boolean isMenuDisplayed = false;

    // Delays single-click processing so a second click can be detected
    // as part of a double-click before changing the mascot state.
    private final PauseTransition singleClickDelay =
            new PauseTransition(Duration.millis(100));

    // Stores the index of the randomly selected mascot state.
    private int currentStateIndex;

    // Contains all available mascot states.
    private final MascotState[] states = MascotState.values();

    //
    private final SpeechBubble speechBubble;


    public MascotInteractionHandler(
            ImageView imageView,
            MascotSwitch mascotSwitch,
            RadialMenu radialMenu,
            SpeechBubble speechBubble) {

        // Store the components required to handle mascot interactions.
        this.imageView = imageView;
        this.mascotSwitch = mascotSwitch;
        this.radialMenu = radialMenu;
        this.speechBubble = speechBubble;
    }


    // Processes mouse click events received from the mascot.
    // Drag operations are ignored so they do not trigger click actions.
    public void setup(MouseEvent event) {

        if (!event.isStillSincePress()) {
            return;
        }

        // Delay single-click processing to give a possible second click
        // time to arrive and become a double-click.
        if (event.getClickCount() == 1) {
            singleClickDelay.setOnFinished(e -> handleSingleClick(event));
            singleClickDelay.playFromStart();
        }

        // A double-click opens or closes the radial menu.
        if (event.getClickCount() == 2) {
            handleDoubleClick();
        }
    }


    // Changes the mascot to a randomly selected state.
    // The new state cannot be the current state or THINKING.
    private void handleSingleClick(MouseEvent event) {

        if (event.isStillSincePress()) {

            do {
                currentStateIndex =
                        (int) (Math.random() * states.length);

            } while (
                    states[currentStateIndex] == mascotSwitch.getMascotState()
                            || states[currentStateIndex] == MascotState.THINKING
            );
        }

        // Apply the selected state and update the mascot image.
        MascotState newState = states[currentStateIndex];

        mascotSwitch.setMascotRender(newState);
        imageView.setImage(mascotSwitch.getMascotImage());

        // Show a new random speech for 7 seconds.
        speechBubble.showRandomSpeech();

        System.out.println(
                "Current state: " + mascotSwitch.getMascotState()
        );
    }


    // Toggles the radial menu between visible and hidden states.
    private void handleDoubleClick() {

        singleClickDelay.stop();

        if (!isMenuDisplayed) {

            // Show the radial menu.
            radialMenu.show();
            isMenuDisplayed = true;

            System.out.println("Menu is on");

        } else {

            // Hide the radial menu.
            radialMenu.hide();
            isMenuDisplayed = false;

            System.out.println("Menu is off");
        }
    }
}