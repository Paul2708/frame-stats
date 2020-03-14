package de.paul2708.framestats.internal.interaction;

import de.paul2708.framestats.internal.search.NameReceiver;
import de.paul2708.framestats.table.Table;
import org.bukkit.entity.Player;

import java.awt.Rectangle;
import java.awt.Shape;

/**
 * This interaction represents the searching for a player.
 * It will open an ui to enter the name.
 *
 * @author Paul2708
 */
public final class SearchInteraction implements TableInteraction {

    private final Rectangle rectangle;

    public SearchInteraction(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Run the interaction, if the player clicks on the {@link #position()}.
     *
     * @param player player that interacts with the table
     * @param table  table that the player clicked
     */
    @Override
    public void interact(Player player, Table table) {
        NameReceiver.anvilReceiver().receive(player, name -> {
            table.search(player, name);
            player.sendMessage("Searching for... " + name);
        });
    }

    /**
     * Get the trigger position.
     * The coordinate system stats in the left upper corner (0,0).
     *
     * @return trigger position
     */
    @Override
    public Shape position() {
        return rectangle;
    }
}