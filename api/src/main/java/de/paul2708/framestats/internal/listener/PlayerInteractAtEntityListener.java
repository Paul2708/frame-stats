package de.paul2708.framestats.internal.listener;

import de.paul2708.framestats.internal.event.MapClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.util.Vector;

/**
 * This listener calls the {@link MapClickEvent} that is used to check table sections.
 *
 * @author Paul2708
 */
public final class PlayerInteractAtEntityListener implements Listener {

    private static final int MAP_SIZE = 128;

    /**
     * Calculate the clicked position by magic math shit and call the map click event.
     *
     * @param event player interact at entity event
     */
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof ItemFrame) {
            ItemFrame frame = (ItemFrame) event.getRightClicked();

            if (frame.getItem().getType() != Material.MAP) {
                return;
            }

            // Calculate clicked coordinate on map
            Vector pos = event.getClickedPosition();
            BlockFace attachedFace = frame.getAttachedFace();
            double diffX, diffY;
            if (isAlongZ(attachedFace)) {
                diffX = pos.getX() + 0.5;
                diffY = 1.0 - (pos.getY() + 0.5);
                if (attachedFace == BlockFace.SOUTH) {
                    diffX = 1.0 - diffX;
                }
            } else {
                diffX = pos.getZ() + 0.5;
                diffY = 1.0 - (pos.getY() + 0.5);
                if (attachedFace == BlockFace.WEST) {
                    diffX = 1.0 - diffX;
                }
            }

            // Call event
            int x = (int) Math.round(diffX * PlayerInteractAtEntityListener.MAP_SIZE);
            int y = (int) Math.round(diffY * PlayerInteractAtEntityListener.MAP_SIZE);

            MapClickEvent mapClickEvent = new MapClickEvent(event.getPlayer(), frame, x, y);
            Bukkit.getPluginManager().callEvent(mapClickEvent);
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