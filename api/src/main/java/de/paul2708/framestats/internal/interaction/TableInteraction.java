package de.paul2708.framestats.internal.interaction;

import de.paul2708.framestats.table.Table;
import org.bukkit.entity.Player;

import java.awt.Shape;

/**
 * This interface represents an interaction with the table.
 * If multiple interactions have the same trigger {@link #position()}, every interaction will be triggered.
 *
 * @author Paul2708
 */
public interface TableInteraction {

    /**
     * Run the interaction, if the player clicks on the {@link #position()}.
     *
     * @param player player that interacts with the table
     * @param table table that the player clicked
     */
    void interact(Player player, Table table);

    /**
     * Get the trigger position.
     * The coordinate system stats in the left upper corner (0,0).
     *
     * @return trigger position
     */
    Shape position();
}