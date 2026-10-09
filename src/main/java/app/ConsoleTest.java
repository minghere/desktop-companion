package app;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import mascot.MascotState;
import mascot.MascotSwitch;

import java.util.Scanner;

public class ConsoleTest {
    public void testFromKeyboard(MascotSwitch mascotSwitch, ImageView imageView) {
        boolean isTrue = true;
        Scanner sc = new Scanner(System.in);

        while (isTrue) {
            System.out.println("Enter Marin's state: ");
            MascotState modifiedState = fromString(sc.nextLine());
            if (modifiedState != null) {
                Platform.runLater(() -> {
                    mascotSwitch.setMascotRender(modifiedState);
                    imageView.setImage(mascotSwitch.getMascotImage());
                });
            } else {
                isTrue = false;
            }
        }
    }
    public static MascotState fromString(String text) {
        for (MascotState state : MascotState.values()) {
            if (state.name().equalsIgnoreCase(text.trim())) {
                return state;
            }
        }
        return null;
    }
}
