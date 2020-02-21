package de.paul2708.framestats.internal.listener;

import de.paul2708.framestats.internal.TableRegistration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Class description.
 *
 * @author Paul2708
 */
public final class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        TableRegistration.getInstance().registerPlayer(player);
    }
}
