package menu;

import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.ArrayList;
import java.util.List;

public class RadialMenuUI {

    /*
     * Creates all items that will appear in the radial menu.
     *
     * This class is responsible for deciding:
     * - Which orbs exist.
     * - Which icon each orb uses.
     * - Which text each orb displays.
     *
     * menu.RadialMenu itself is responsible for positioning these items.
     */
    public List<RadialMenuItem> createItems() {

        // List containing all radial menu items.
        List<RadialMenuItem> items = new ArrayList<>();

        /*
         * Create the Chat orb.
         *
         * COMMENTS is a Font Awesome icon provided by Ikonli.
         */
        items.add(new RadialMenuItem(new RadialOrb(FontAwesomeSolid.COMMENTS, "Chat")));

        // Create the Study orb.
        items.add(new RadialMenuItem(new RadialOrb(FontAwesomeSolid.BOOK, "Study")));

        // Create the Setting orb.
        items.add(new RadialMenuItem(new RadialOrb(FontAwesomeSolid.COG, "Setting")));

        // Create the Instruction orb.
        items.add(new RadialMenuItem(new RadialOrb(FontAwesomeSolid.INFO_CIRCLE, "Instruction")));

        /*
         * Return all four items to menu.RadialMenu.
         *
         * menu.RadialMenu will then decide where each item
         * should be positioned around the mascot.
         */
        return items;
    }
}