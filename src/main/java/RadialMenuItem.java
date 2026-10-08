import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class RadialMenuItem extends StackPane {

    public RadialMenuItem(Node graphic) {
        // Define the preferred size of one menu item.
        setPrefSize(70, 70);

        // Keep the graphic centered inside the item.
        setAlignment(Pos.CENTER);

        // Put the provided graphic inside this menu item.
        getChildren().add(graphic);
    }
}