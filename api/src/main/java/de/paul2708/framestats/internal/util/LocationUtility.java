package de.paul2708.framestats.internal.util;

import org.bukkit.block.BlockFace;

/**
 * This utility class provides help methods for locations.
 *
 * @author Paul2708
 */
public final class LocationUtility {

    /**
     * Private constructor to avoid instances.
     */
    private LocationUtility() {
        throw new IllegalAccessError();
    }

    /**
     * Check if the item frame is located along the z coordinate.
     *
     * @param face item frame face
     * @return true if the frame is z aligned, otherwise false
     */
    public static boolean isAlongZ(BlockFace face) {
        return face.getModZ() != 0 && face.getModX() == 0;
    }
}