import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

public class RadialOrb extends StackPane {

    /*
     * Creates one orb in the radial menu.
     *
     * icon: Font Awesome icon displayed inside the orb.
     * text: Text displayed below the icon.
     */
    public RadialOrb(FontAwesomeSolid icon, String text) {

        // The circular background of the orb.
        Circle background = new Circle(48);

        // Give the orb a dark, mostly opaque background
        // so the desktop behind it does not make the content difficult to see.
        background.setFill(Color.rgb(150, 112, 82, 0.95));

        // Add a thin white border around the orb.
        background.setStroke(Color.rgb(255, 255, 255, 0.8));
        background.setStrokeWidth(2);

        // Create a shadow behind the orb to separate it from the desktop.
        DropShadow shadow = new DropShadow();
        shadow.setRadius(12);
        shadow.setSpread(0.35);
        shadow.setColor(Color.rgb(0, 0, 0, 0.8));

        // Apply the shadow to the circular background.
        background.setEffect(shadow);

        // Create the graphical icon using the Ikonli library.
        FontIcon iconGraphic = new FontIcon(icon);

        // Set the icon size.
        iconGraphic.setIconSize(28);

        // Set the icon color.
        iconGraphic.setIconColor(Color.WHITE);

        // Create the text displayed below the icon.
        Label textLabel = new Label(text);

        // Basic styling for the text.
        textLabel.setStyle("-fx-text-fill: white;"
                        + "-fx-font-size: 13px;"
                        + "-fx-font-weight: bold;"
        );

        /*
         * VBox places the icon above the text.
         *
         * Example:
         *
         *      [ icon ]
         *       Chat
         */
        VBox content = new VBox(4);
        content.setAlignment(Pos.CENTER);

        // Add the icon and text to the vertical container.
        content.getChildren().addAll(iconGraphic, textLabel);

        /*
         * StackPane layers the content on top of the circular background.
         *
         * Layer 1: Circle
         * Layer 2: Icon + Text
         */
        getChildren().addAll(background, content);

        // Set the orb's preferred size.
        setPrefSize(96, 96);

        // Prevent the orb from becoming smaller than the preferred size.
        setMinSize(96, 96);

        // Prevent the orb from becoming larger than the preferred size.
        setMaxSize(96, 96);

        // Keep the content centered inside the orb.
        setAlignment(Pos.CENTER);

        // Configure the visual reaction when the mouse enters/leaves the orb.
        setupHoverEffect(background);
    }

    /*
     * Adds a simple hover effect.
     *
     * When the mouse enters:
     * - The background becomes brighter.
     * - The orb becomes slightly larger.
     *
     * When the mouse leaves:
     * - The background returns to its original color.
     * - The orb returns to its original size.
     */
    private void setupHoverEffect(Circle background) {

        // Mouse enters the orb.
        setOnMouseEntered(event -> {
            background.setFill(Color.rgb(60, 60, 75, 1.0));

            // Slightly enlarge the entire orb.
            setScaleX(1.1);
            setScaleY(1.1);
        });

        // Mouse leaves the orb.
        setOnMouseExited(event -> {
            background.setFill(Color.rgb(150, 112, 82, 0.95));

            // Return the orb to its original size.
            setScaleX(1.0);
            setScaleY(1.0);
        });
    }
}