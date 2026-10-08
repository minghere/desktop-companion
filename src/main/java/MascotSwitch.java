import javafx.scene.image.Image;

public class MascotSwitch {

    // Responsible for mapping each mascot state to its corresponding image resource.
    private MascotRenderer renderer = new MascotRenderer();

    // Stores the mascot's current state.
    // The mascot starts in the IDLE state.
    private MascotState mascotState = MascotState.IDLE;

    // Returns the image corresponding to the mascot's current state.
    public Image getMascotImage() {
        String resourcePath = renderer.getImageStateURL(this.mascotState);
        return new Image(
                MascotSwitch.class.getResourceAsStream(resourcePath)
        );
    }

    // Returns the mascot's current state.
    public MascotState getMascotState() {
        return this.mascotState;
    }

    // Changes the mascot's current state.
    public void setMascotRender(MascotState newState) {
        this.mascotState = newState;
    }
}