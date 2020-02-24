package de.paul2708.framestats.internal.frame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapView;

import java.util.Optional;

/**
 * This class handles searching and placing item frames and their map content.
 *
 * @author Paul2708
 */
public final class FramePlacer {

    // TODO: Remove parameter and move into fields

    private static final short START_ID = 1000;

    private static short counter = FramePlacer.START_ID;

    // TODO: Add javadoc
    public MapView[][] fill(ItemFrame[][] frames) {
        MapView[][] mapViews = new MapView[frames.length][frames[0].length];
        World world = frames[0][0].getWorld();

        for (int i = 0; i < frames.length; i++) {
            for (int j = 0; j < frames[0].length; j++) {
                mapViews[i][j] = Bukkit.getMap(FramePlacer.counter) == null ? Bukkit.createMap(world)
                        : Bukkit.getMap(FramePlacer.counter);

                // TODO: Move item creation outside loop
                ItemStack itemStack = new ItemStack(Material.MAP, 1);
                itemStack.setDurability(mapViews[i][j].getId());

                frames[i][j].setItem(itemStack);

                FramePlacer.counter++;
            }
        }

        return mapViews;
    }

    /**
     * Search for frames and prepare them.
     *
     * @param blocks searched array of blocks
     * @return array of item frames
     * @throws IllegalArgumentException if a block doesn't has an item frame
     */
    public ItemFrame[][] search(Block[][] blocks) {
        ItemFrame[][] frames = new ItemFrame[blocks.length][blocks[0].length];

        for (int i = 0; i < frames.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                Optional<ItemFrame> searched = searchSingle(blocks[i][j]);

                if (searched.isPresent()) {
                    frames[i][j] = searched.get();

                    // Prepare frames
                    frames[i][j].setRotation(Rotation.NONE);
                } else {
                    throw new IllegalArgumentException("block " + blocks[i][j].getLocation() + " has no item frame");
                }
            }
        }

        return frames;
    }

    /**
     * Construct a 2-dimensional array of blocks.
     * The following matrix will show the order:
     * <br>
     * <code>entry[0][0]</code> ... <code>rightCorner</code><br>
     * .                                    .<br>
     * .                                    .<br>
     * .                                    .<br>
     * <code>leftCorner</code> ...  <code>entry[m][n]</code>
     *
     * @param leftCorner left corner location
     * @param rightCorner right corner location
     * @return constructed array of blocks
     */
    public Block[][] construct(Location leftCorner, Location rightCorner) {
        // TODO: Refactor, duplicated code
        // TODO: Get width and height by config
        if (leftCorner.getBlockX() == rightCorner.getBlockX()) {
            int widthBlocks = 1 + Math.abs(leftCorner.getBlockZ() - rightCorner.getBlockZ());
            int heightBlocks = 1 + Math.abs(leftCorner.getBlockY() - rightCorner.getBlockY());

            Block[][] blocks = new Block[widthBlocks][heightBlocks];

            for (int i = 0; i < heightBlocks; i++) {
                for (int j = 0; j < widthBlocks; j++) {
                    int zCoordinate = leftCorner.getBlockZ();

                    if (leftCorner.getBlockZ() < rightCorner.getBlockZ()) {
                        zCoordinate += j;
                    } else {
                        zCoordinate -= j;
                    }

                    Location location = new Location(leftCorner.getWorld(), leftCorner.getBlockX(),
                            rightCorner.getBlockY() - i, zCoordinate);

                    blocks[j][i] = location.getBlock();
                }
            }

            return blocks;
        } else {
            int widthBlocks = 1 + Math.abs(leftCorner.getBlockX() - rightCorner.getBlockX());
            int heightBlocks = 1 + Math.abs(leftCorner.getBlockY() - rightCorner.getBlockY());

            Block[][] blocks = new Block[widthBlocks][heightBlocks];

            for (int i = 0; i < heightBlocks; i++) {
                for (int j = 0; j < widthBlocks; j++) {
                    int xCoordinate = leftCorner.getBlockX();

                    if (leftCorner.getBlockX() < rightCorner.getBlockX()) {
                        xCoordinate += j;
                    } else {
                        xCoordinate -= j;
                    }

                    Location location = new Location(leftCorner.getWorld(), xCoordinate,
                            rightCorner.getBlockY() - i, leftCorner.getBlockZ());

                    blocks[j][i] = location.getBlock();
                }
            }

            return blocks;
        }
    }

    /**
     * Search an hanging item frame at a block.
     * If multiple frames were fount, the first will be returned.
     *
     * @param block block to search for
     * @return optional item frame
     */
    private Optional<ItemFrame> searchSingle(Block block) {
        Location location = block.getLocation();

        for (Entity entity : location.getWorld().getNearbyEntities(location, 2, 2, 2)) {
            if (entity instanceof ItemFrame) {
                ItemFrame frame = (ItemFrame) entity;
                boolean correctSide =  entity.getLocation().getBlock().getRelative(frame.getAttachedFace()).equals(block);

                if (correctSide) {
                    return Optional.of((ItemFrame) entity);
                }
            }
        }

        return Optional.empty();
    }
}