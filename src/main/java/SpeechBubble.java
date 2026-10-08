import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SpeechBubble extends StackPane {

    private final Label speechLabel;

    private final MockSpeeches mockSpeech;

    // Controls how long the speech bubble remains visible.
    private final PauseTransition hideTimer =
            new PauseTransition(Duration.seconds(7));

    public SpeechBubble(MockSpeeches mockSpeech) {

        this.mockSpeech = mockSpeech;

        // Background of the speech bubble.
        Rectangle background = new Rectangle(260, 90);

        background.setArcWidth(25);
        background.setArcHeight(25);

        background.setFill(Color.rgb(255, 255, 255, 0.95));
        background.setStroke(Color.rgb(90, 70, 60));
        background.setStrokeWidth(2);

        // Text displayed inside the speech bubble.
        speechLabel = new Label();

        speechLabel.setTextFill(Color.rgb(50, 40, 35));
        speechLabel.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;"
        );

        // Allow long speech lines to wrap inside the bubble.
        speechLabel.setWrapText(true);
        speechLabel.setMaxWidth(220);

        // Put the text in the center of the bubble.
        setAlignment(Pos.CENTER);

        /*
         * StackPane allows the text to be placed
         * on top of the bubble background.
         */
        getChildren().addAll(background, speechLabel);

        /*
         * The bubble itself should not interfere with
         * mouse interaction with the mascot.
         */
        setMouseTransparent(true);

        // Hide the bubble when the timer finishes.
        hideTimer.setOnFinished(event -> hide());

        // The bubble starts hidden.
        setVisible(false);
    }

    /*
     * Shows the bubble with a new random speech.
     */
    public void showRandomSpeech() {

        // Get a random line from MockSpeech.
        String speech = mockSpeech.getRandomSpeech();

        // Display the selected speech.
        speechLabel.setText(speech);

        // Show the bubble.
        setVisible(true);

        /*
         * Restart the 7-second timer.
         *
         * If the user clicks again while the bubble
         * is already visible, the timer starts again
         * from 7 seconds.
         */
        hideTimer.playFromStart();
    }

    /*
     * Hides the speech bubble.
     */
    public void hide() {
        hideTimer.stop();
        setVisible(false);
    }
}