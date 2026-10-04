import javafx.scene.image.Image;

public class MascotSwitch {
    private MascotRenderer renderer = new MascotRenderer();
    private MascotState mascotState = MascotState.IDLE;

    public Image getMascotImage() {
        String resourcePath = renderer.getImageStateURL(this.mascotState);
        return new Image(MascotSwitch.class.getResourceAsStream(resourcePath));
    }

    public MascotState getMascotState() {return this.mascotState;}

    public void setMascotRender(MascotState newState) {
        this.mascotState = newState;
    }
}
