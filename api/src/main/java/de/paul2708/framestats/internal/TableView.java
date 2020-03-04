package de.paul2708.framestats.internal;

import de.paul2708.framestats.internal.image.ImagePipeline;
import de.paul2708.framestats.internal.image.ImageSplitter;
import de.paul2708.framestats.internal.renderer.TableRenderer;
import de.paul2708.framestats.table.Table;
import org.bukkit.entity.Player;

import java.awt.Image;

/**
 * This class renders the table for a given player.
 *
 * @author Paul2708
 */
public final class TableView {

    private final Player player;
    private final TableRenderer[][] view;

    private final ImagePipeline pipeline;

    /**
     * Create a new (empty) table view for the given player.
     *
     * @param table table to view
     * @param view table renderer frames
     * @param player player to show the table
     */
    public TableView(Table table, TableRenderer[][] view, Player player) {
        this.view = view;
        this.player = player;

        this.pipeline = new ImagePipeline(table, player);
    }

    /**
     * Redraw the whole table.
     */
    public void redraw() {
        pipeline.runFully();
        draw();
    }

    /**
     * Draw the search objective and results.
     *
     * @param name name to search for
     */
    public void drawSearch(String name) {
        pipeline.baseImage()
                .applyTableContent()
                .applyPageBar()
                .applySearch(name);
        draw();
    }

    public void drawContent() {
        pipeline.baseImage()
                .applyTableContent()
                .applyPageBar()
                .applySearch("Suche..");
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
                view[i][j].render(player, images[i][j]);
            }
        }
    }
}