package de.paul2708.framestats.internal;

import de.paul2708.framestats.internal.image.ImagePipeline;
import de.paul2708.framestats.internal.image.ImageSplitter;
import de.paul2708.framestats.internal.renderer.TableRenderer;
import de.paul2708.framestats.table.Table;
import org.bukkit.entity.Player;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * This class renders the table for a given player.
 *
 * @author Paul2708
 */
public final class TableView {

    private final Table table;
    private final Player player;
    private final TableRenderer[][] view;

    /**
     * Create a new (empty) table view for the given player.
     *
     * @param table table to view
     * @param view table renderer frames
     * @param player player to show the table
     */
    public TableView(Table table, TableRenderer[][] view, Player player) {
        this.table = table;
        this.view = view;
        this.player = player;
    }

    /**
     * Run the {@link ImagePipeline} and apply the image to the table renderers.
     */
    public void draw() {
        // TODO: Add methods to update only needed images
        BufferedImage image = new ImagePipeline(table, player).run();
        ImageSplitter splitter = new ImageSplitter();
        Image[][] images = splitter.splitImage(image);

        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[0].length; j++) {
                view[i][j].render(player, images[i][j]);
            }
        }
    }
}