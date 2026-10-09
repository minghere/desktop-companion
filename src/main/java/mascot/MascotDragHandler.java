package mascot;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MascotDragHandler {

    // Stores the mouse position when the user starts dragging the mascot.
    private double offSetX;
    private double offSetY;

    // Defines the visible area of the primary screen.
    private final javafx.geometry.Rectangle2D screenBounds =
            javafx.stage.Screen.getPrimary().getVisualBounds();

    // Stores the width and height of the usable screen area.
    private double screenWidth;
    private double screenHeight;

    // Reference to the Stage whose position will be changed during dragging.
    private Stage primaryStage;

    // Stores the calculated new position of the Stage.
    private double newX;
    private double newY;


    public MascotDragHandler(Stage primaryStage) {
        // Stores the Stage controlled by this drag handler.
        this.primaryStage = primaryStage;

        // Gets the usable screen dimensions for drag boundary checking.
        this.screenWidth = screenBounds.getWidth();
        this.screenHeight = screenBounds.getHeight();
    }


    // Records the mouse position when the user presses the mascot.
    // These coordinates are used to keep the mascot from jumping
    // when dragging begins.
    public void mousePressedHandler(MouseEvent event) {
        offSetX = event.getSceneX();
        offSetY = event.getSceneY();
    }


    // Calculates the new Stage position while the mascot is being dragged.
    // The position is also restricted so that the Stage stays inside
    // the visible screen boundaries.
    public double[] mouseDraggedHandler(MouseEvent event) {

        // Stores the calculated X and Y coordinates to return to the caller.
        double[] xyCoordination = new double[2];

        // Calculates the new Stage position based on the current mouse position
        // and the offset recorded when the drag started.
        newX = event.getScreenX() - offSetX;
        newY = event.getScreenY() - offSetY;


        // Prevents the Stage from moving beyond the left or right screen boundaries.
        if (newX < 0) {
            newX = 0;
        } else if (newX + primaryStage.getWidth() > screenWidth) {
            newX = screenWidth - primaryStage.getWidth();
        }


        // Prevents the Stage from moving beyond the top or bottom screen boundaries.
        if (newY < 0) {
            newY = 0;
        } else if (newY + primaryStage.getHeight() > screenHeight) {
            newY = screenHeight - primaryStage.getHeight();
        }


        // Places the final validated coordinates into the result array.
        xyCoordination[0] = newX;
        xyCoordination[1] = newY;

        // Returns the new Stage position to the caller.
        return xyCoordination;
    }
}