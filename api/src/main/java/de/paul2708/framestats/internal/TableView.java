package de.paul2708.framestats.internal;

import de.paul2708.framestats.internal.image.ImagePipeline;
import de.paul2708.framestats.internal.image.ImageSplitter;
import de.paul2708.framestats.internal.renderer.TableRenderer;
import de.paul2708.framestats.internal.state.TableState;
import org.bukkit.entity.Player;

import java.awt.Image;

/**
 * This class renders the table for a given player.
 *
 * @author Paul2708
 */
public final class TableView {

    private final Player player;
    private final TableState state;
    private final TableRenderer[][] renderer;

    private final ImagePipeline pipeline;

    /**
     * Create a new table view.
     *
     * @param player player to render the view for
     * @param state table state that will be rendered
     * @param view physical item frame renderer
     */
    public TableView(Player player, TableState state, TableRenderer[][] view) {
        this.renderer = view;
        this.state = state;
        this.player = player;

        this.pipeline = new ImagePipeline(state.getTable(), state);
    }

    // TODO: Add extra methods to update only specific layers instead of all

    /**
     * Redraw the whole table.
     */
    public void update() {
        pipeline.runFully();
        draw();
    }

    /**
     * Draw the pipeline image to the item frames.
     */
    private void draw() {
        ImageSplitter splitter = new ImageSplitter();
        Image[][] images = splitter.splitImage(pipeline.get());

        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[0].length; j++) {
                renderer[i][j].render(player, images[i][j]);
            }
        }
    }
}