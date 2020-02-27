package de.paul2708.framestats.internal.event;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * This event got called in {@link de.paul2708.framestats.internal.listener.PlayerInteractAtEntityListener},
 * if the player clicks on an item frame that holds a map.
 * <br>
 * Inspired by BKCommonLib.
 *
 * @author Paul2708
 */
public final class MapClickEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final ItemFrame frame;
    private final int x;
    private final int y;

    /**
     * Create a new map click event.
     *
     * @param player player that clicked
     * @param frame clicked item frame
     * @param x x coordinate
     * @param y y coordinate
     */
    public MapClickEvent(Player player, ItemFrame frame, int x, int y) {
        this.player = player;
        this.frame = frame;
        this.x = x;
        this.y = y;
    }

    /**
     * Get the player who clicked on the map.
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the clicked item frame that holds the map.
     *
     * @return item frame
     */
    public ItemFrame getFrame() {
        return frame;
    }

    /**
     * Get the clicked x coordinate.
     * Range from 0 to 127.
     *
     * @return clicked x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the clicked y coordinate.
     * Range from 0 to 127.
     *
     * @return clicked y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Get the handler list.
     * Required by spigot framework.
     *
     * @return handler list
     */
    public static HandlerList getHandlerList() {
        return MapClickEvent.HANDLERS;
    }

    /**
     * Get the handler list.
     * Required by spigot framework.
     *
     * @return handler list
     */
    @Override
    public HandlerList getHandlers() {
        return MapClickEvent.HANDLERS;
    }
}