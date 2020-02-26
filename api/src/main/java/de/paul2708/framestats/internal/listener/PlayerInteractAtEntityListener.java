package de.paul2708.framestats.internal.listener;

import de.paul2708.framestats.internal.TableRegistration;
import de.paul2708.framestats.internal.image.calculator.LocationTransformer;
import de.paul2708.framestats.table.Table;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.Optional;

/**
 * Class description.
 *
 * @author Paul2708
 */
public class PlayerInteractAtEntityListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof ItemFrame) {
            // Hier ist das Problem:
            Location location = event.getPlayer().getLocation().add(event.getClickedPosition());
            ItemFrame frame = (ItemFrame) event.getRightClicked();

            Optional<Table> frameOptional = TableRegistration.getInstance().findByFrame(frame);

            if (frameOptional.isPresent()) {
                // Table clicked
            } else {
                // Table not found
            }

        }
    }
}