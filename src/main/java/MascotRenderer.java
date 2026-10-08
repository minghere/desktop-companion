import java.util.HashMap;
import java.util.Map;

public class MascotRenderer {

    // Maps each mascot state to the image resource used to display that state.
    private Map<MascotState, String> imagePaths = new HashMap<>();

    public MascotRenderer() {

        // Defines the image resource for each mascot state.
        this.imagePaths.put(MascotState.IDLE,     "/state_images/idle.png");
        this.imagePaths.put(MascotState.HAPPY,    "/state_images/happy.png");
        this.imagePaths.put(MascotState.TALKING,  "/state_images/talking.png");
        this.imagePaths.put(MascotState.THINKING, "/state_images/thinking.png");
        this.imagePaths.put(MascotState.SLEEPING, "/state_images/sleeping.png");
        this.imagePaths.put(MascotState.STUDYING, "/state_images/studying.png");
    }

    // Returns the image resource path corresponding to the given mascot state.
    public String getImageStateURL(MascotState currentState) {
        return this.imagePaths.get(currentState);
    }
}