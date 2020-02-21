package de.paul2708.framestats.internal.renderer;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.awt.Image;

/**
 * Class description.
 *
 * @author Paul2708
 */
public final class TableRenderer extends MapRenderer {

    // TODO: False commit

    private final Player player;
    private final Image image;

    private boolean update;

    public TableRenderer(Player player, Image image) {
        super(true);

        this.player = player;
        this.image = image;

        this.update = true;
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
        if (update) {
            // canvas.drawImage(0, 0, ima);

            this.update = false;
        }
    }

    public void update() {
        this.update = true;
    }
}
