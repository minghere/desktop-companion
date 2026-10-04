import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MascotDragHandler {
    private double offSetX;
    private double offSetY;
    private final javafx.geometry.Rectangle2D screenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
    private double screenWidth;
    private double screenHeight;
    private Stage primaryStage;
    private double newX;
    private double newY;

    public MascotDragHandler(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.screenWidth = screenBounds.getWidth();
        this.screenHeight = screenBounds.getHeight();
    }

    public void mousePressedHandler(MouseEvent event) {
        offSetX = event.getSceneX();
        offSetY = event.getSceneY();
    }

    public double[] mouseDraggedHandler(MouseEvent event) {
        double[] xyCoordination = new double[2];

        newX = event.getScreenX() - offSetX;
        newY = event.getScreenY() - offSetY;

        if (newX < 0) {
            newX = 0;
        } else if (newX + primaryStage.getWidth() > screenWidth) {
            newX = screenWidth - primaryStage.getWidth();
        }

        if (newY < 0) {
            newY = 0;
        } else if (newY + primaryStage.getHeight() > screenHeight) {
            newY = screenHeight - primaryStage.getHeight();
        }

        xyCoordination[0] = newX;
        xyCoordination[1] = newY;

        return xyCoordination;
    }
}
