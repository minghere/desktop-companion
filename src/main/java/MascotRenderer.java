import java.util.HashMap;
import java.util.Map;

public class MascotRenderer {
    private Map<MascotState, String> imagePaths = new HashMap<>();

    public MascotRenderer() {
        this.imagePaths.put(MascotState.IDLE,     "/state_images/idle.png");
        this.imagePaths.put(MascotState.HAPPY,    "/state_images/happy.png");
        this.imagePaths.put(MascotState.TALKING,  "/state_images/talking.png");
        this.imagePaths.put(MascotState.THINKING, "/state_images/thinking.png");
        this.imagePaths.put(MascotState.SLEEPING, "/state_images/sleeping.png");
        this.imagePaths.put(MascotState.STUDYING, "/state_images/studying.png");
    }

    public String getImageStateURL(MascotState currentState) {
        return this.imagePaths.get(currentState);
    }
}
