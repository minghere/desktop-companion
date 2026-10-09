package menu;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class RadialMenu extends Pane{
    // Gain mascot as center point
    private final Node mascot;

    private final List<RadialMenuItem> items = new ArrayList<>();

    // distance from center of mascot to each orb
    private final double orbRadius = 180;

    // angle of first orb
    private final double startAngle = Math.toRadians(-195);

    // angle range limit of orbs
    private final double arcAngle = Math.toRadians(210);

    public RadialMenu(Node mascot) {
        this.mascot = mascot;
        setPickOnBounds(false); // Not regard all of bound part as clickable area
        setBackground(Background.EMPTY);
    }

    //Add orb item to menu
    public void addItem(RadialMenuItem singleItem) {
        items.add(singleItem);
        singleItem.setVisible(false);
        getChildren().add(singleItem);
    }

    // Display visible items in menu
    public void show() {
        positionItems();

        for (RadialMenuItem item : items) {
            item.setVisible(true);
        }
    }

    // Display visible items in menu
    public void hide() {
        for (RadialMenuItem item : items) {
            item.setVisible(false);
        }
    }

    // Arrange the position of each item on menu
    public void positionItems() {
        Bounds mascotBounds = mascot.getBoundsInParent();

        // The coordination of mascot's center
        double mascotCenterX = mascotBounds.getMinX() + (mascotBounds.getWidth() / 2);
        double mascotCenterY = mascotBounds.getMinY() + (mascotBounds.getHeight() / 2);

        // The average degree-distance among orbs in menu
        double angleStep = arcAngle / (items.size() - 1);

        for (int i = 0; i < items.size(); i++) {
            RadialMenuItem item = items.get(i);

            // The angle of each orb compared to mascot
            double angle = startAngle + (i * angleStep);

            // Coordination of single orb (still coordination of top-left as default)
            double itemCenterX = mascotCenterX + (orbRadius * Math.cos(angle));
            double itemCenterY = mascotCenterY + (orbRadius * Math.sin(angle));

            // Coordination of orb's center
            item.setLayoutX(itemCenterX - (item.getPrefWidth() / 2));
            item.setLayoutY(itemCenterY - (item.getPrefHeight() / 2));
        }
    }
}
