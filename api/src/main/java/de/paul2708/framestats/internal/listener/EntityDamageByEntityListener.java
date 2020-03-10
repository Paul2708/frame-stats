package de.paul2708.framestats.internal.listener;

import org.bukkit.entity.Hanging;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * This listener cancels any damage to hangings.
 *
 * @author Paul2708
 */
public final class EntityDamageByEntityListener implements Listener {

    /**
     * Cancel entity damage to hangings.
     * This includes item frames.
     *
     * @param event entity damage by entity event
     */
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Hanging) {
            event.setCancelled(true);
        }
    }
}