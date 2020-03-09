package de.paul2708.framestats.internal.listener;

import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;

/**
 * This listener prevents any entity to destroy an item frame.
 *
 * @author Paul2708
 */
public final class HangingBreakListener implements Listener {

    // TODO: Still possible to kick out the item frame item

    /**
     * Disable item frame breaking.
     *
     * @param event hanging break by entity event
     */
    @EventHandler
    public void onBreak(HangingBreakByEntityEvent event) {
        if (event.getEntity() instanceof ItemFrame) {
            event.setCancelled(true);
        }
    }
}