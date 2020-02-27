package de.paul2708.framestats.internal.listener;

import de.paul2708.framestats.internal.TableRegistration;
import de.paul2708.framestats.internal.event.MapClickEvent;
import de.paul2708.framestats.table.Table;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Optional;

/**
 * This listener calculates the clicked table position and runs any click handler on the table.
 *
 * @author Paul2708
 */
public final class MapClickListener implements Listener {

    /**
     * Calculate the clicked table coordinate and execute any referring handles like searching for a player.
     *
     * @param event map click event
     */
    @EventHandler
    public void onClick(MapClickEvent event) {
        ItemFrame frame = event.getFrame();
        Optional<Table> tableRequest = TableRegistration.getInstance().findByFrame(frame);

        if (tableRequest.isPresent()) {
            Table table = tableRequest.get();
            Location leftCorner = table.getConfiguration().getLeftLowerCorner();
            Location rightCorner = table.getConfiguration().getRightUpperCorner();
            Location frameLocation = frame.getLocation();

            // Calculate absolute table coordinates
            int xDiff = Math.abs(isAlongZ(frame.getAttachedFace()) ? leftCorner.getBlockX() - frameLocation.getBlockX() :
                    leftCorner.getBlockZ() - frameLocation.getBlockZ());
            int yDiff = Math.abs(rightCorner.getBlockY() - frameLocation.getBlockY());

            int tableX = 128 * xDiff + event.getX();
            int tableY = 128 * yDiff + event.getY();

            // TODO: Handle table interaction
        }
    }

    // TODO: Move location stuff into utility (or at least common) place

    /**
     * Check if the item frame is located along the z coordinate.
     *
     * @param face item frame face
     * @return true if the frame is z aligned, otherwise false
     */
    private boolean isAlongZ(BlockFace face) {
        return face.getModZ() != 0 && face.getModX() == 0;
    }
}