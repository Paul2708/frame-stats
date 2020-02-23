package de.paul2708.framestats.internal.renderer;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

/**
 * This map renderer renders an image once and only re-renders if a new image was set.
 *
 * @author Paul2708
 */
public final class TableRenderer extends MapRenderer {

    private final Map<Player, Image> images;

    public TableRenderer() {
        super(true);

        this.images = new HashMap<>();
    }

    /**
     * Render to the given map.
     *
     * @param map    The MapView being rendered to.
     * @param canvas The canvas to use for rendering.
     * @param player The player who triggered the rendering.
     */
    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        images.forEach((playerKey, image) -> {
            if (player.equals(playerKey)) {
                canvas.drawImage(0, 0, image);
            }
        });
    }

    public void render(Player player, Image image) {
        images.put(player, image);
    }
}