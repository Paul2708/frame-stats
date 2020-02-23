package de.paul2708.framestats.internal;

import de.paul2708.framestats.internal.image.ImagePipeline;
import de.paul2708.framestats.internal.image.ImageSplitter;
import de.paul2708.framestats.internal.renderer.TableRenderer;
import de.paul2708.framestats.table.Table;
import org.bukkit.entity.Player;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Class description.
 *
 * @author Paul2708
 */
public final class TableView {

    private final Table table;
    private final Player player;
    private final TableRenderer[][] view;

    public TableView(Table table, TableRenderer[][] view, Player player) {
        this.table = table;
        this.view = view;
        this.player = player;
    }

    public void draw() {
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