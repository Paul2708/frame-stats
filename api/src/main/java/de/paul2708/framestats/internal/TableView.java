package de.paul2708.framestats.internal;

import de.paul2708.framestats.internal.image.ImageSplitter;
import de.paul2708.framestats.internal.image.PositionCalculator;
import de.paul2708.framestats.internal.image.layer.TableLayer;
import de.paul2708.framestats.table.Table;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class description.
 *
 * @author Paul2708
 */
public final class TableView {

    private final Table table;
    private final Player player;
    private final MapView[][] view;

    private BufferedImage image;

    public TableView(Table table, MapView[][] view, Player player) {
        this.table = table;
        this.view = view;
        this.player = player;
    }

    public void setup() {
        try {
            image = ImageIO.read(new File("./plugins/Download.png"));
            PositionCalculator calculator = new PositionCalculator(table.getConfiguration());
            calculator.calculate();
            image = new TableLayer(calculator).apply(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        ImageSplitter splitter = new ImageSplitter();
        Image[][] images = splitter.splitImage(image);

        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[0].length; j++) {
                Image currentImage = images[i][j];
                MapView view = this.view[i][j];

                List<MapRenderer> list = new LinkedList<>(view.getRenderers());

                for (MapRenderer renderer : list) {
                    view.removeRenderer(renderer);
                }

                view.addRenderer(new MapRenderer() {

                    boolean render = false;

                    @Override
                    public void render(MapView map, MapCanvas canvas, Player player) {
                        if (!render) {
                            canvas.drawImage(0, 0, currentImage);
                            render = true;
                        }
                    }
                });
            }
        }


    }
}