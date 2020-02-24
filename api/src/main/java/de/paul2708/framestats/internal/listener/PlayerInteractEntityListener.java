package de.paul2708.framestats.internal.listener;

import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * This listener disables item frame rotation.
 *
 * @author Paul2708
 */
public final class PlayerInteractEntityListener implements Listener {

    /**
     * Cancel the interaction for item frames.
     *
     * @param event player interact entity event
     */
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof ItemFrame) {
            event.setCancelled(true);
        }
    }
}