package de.paul2708.framestats.internal.image;

import de.paul2708.framestats.configuration.ColumnConfiguration;
import de.paul2708.framestats.configuration.TableConfiguration;
import de.paul2708.framestats.internal.image.layer.BackgroundLayer;
import de.paul2708.framestats.internal.image.layer.HeadingLayer;
import de.paul2708.framestats.internal.image.layer.ImageLayer;
import de.paul2708.framestats.internal.image.layer.TableLayer;
import de.paul2708.framestats.table.Table;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class will create an image by mapping layer on layer.
 *
 * @author Paul2708
 */
public final class ImagePipeline {

    private final Table table;

    /**
     * Create a new image pipeline.
     *
     * @param table referring table
     */
    public ImagePipeline(Table table) {
        this.table = table;
    }

    /**
     * Run the pipeline by loading the initial image and applying the layers.
     *
     * @return create image
     */
    public BufferedImage run() {
        // Prepare used layer references
        TableConfiguration configuration = table.getConfiguration();
        PositionCalculator calculator = new PositionCalculator(configuration);
        calculator.calculate();

        // Load image
        // TODO: Replace with config one
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./plugins/Download.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Init pipeline
        List<ImageLayer> layers = new LinkedList<>();
        layers.add(new BackgroundLayer());
        layers.add(new TableLayer(calculator));
        layers.add(new HeadingLayer(calculator, configuration.getColumnConfigurations()
                .stream()
                .map(ColumnConfiguration::getName)
                .collect(Collectors.toList()))
        );

        // Run pipeline
        BufferedImage inputResult = image;
        for (ImageLayer layer : layers) {
            inputResult = layer.apply(inputResult);
        }

        return inputResult;
    }
}