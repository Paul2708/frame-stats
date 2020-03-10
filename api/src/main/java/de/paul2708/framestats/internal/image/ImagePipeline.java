package de.paul2708.framestats.internal.image;

import de.paul2708.framestats.configuration.ColumnConfiguration;
import de.paul2708.framestats.configuration.TableConfiguration;
import de.paul2708.framestats.internal.image.calculator.PageBarCalculator;
import de.paul2708.framestats.internal.image.calculator.PositionCalculator;
import de.paul2708.framestats.internal.image.calculator.SearchButtonCalculator;
import de.paul2708.framestats.internal.image.layer.BackgroundLayer;
import de.paul2708.framestats.internal.image.layer.ContentLayer;
import de.paul2708.framestats.internal.image.layer.CroppingLayer;
import de.paul2708.framestats.internal.image.layer.HeadingLayer;
import de.paul2708.framestats.internal.image.layer.ImageLayer;
import de.paul2708.framestats.internal.image.layer.PageBarLayer;
import de.paul2708.framestats.internal.image.layer.SearchButtonLayer;
import de.paul2708.framestats.internal.image.layer.SearchNameLayer;
import de.paul2708.framestats.internal.image.layer.TableLayer;
import de.paul2708.framestats.internal.state.TableState;
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
    private final TableState tableState;

    private PositionCalculator positionCalculator;
    private SearchButtonCalculator searchButtonCalculator;
    private PageBarCalculator pageBarCalculator;

    private BufferedImage baseImage;
    private BufferedImage currentImage;

    public ImagePipeline(Table table, TableState tableState) {
        this.table = table;
        this.tableState = tableState;
    }

    /**
     * Run the pipeline fully by creating base, table content and search button.
     *
     * @return image pipeline
     */
    public ImagePipeline runFully() {
        this.currentImage = this.baseImage()
                .applyTableContent()
                .applySearch()
                .applyPageBar()
                .get();

        return this;
    }

    /**
     * Create the base image once.
     *
     * @return image pipeline
     */
    public ImagePipeline baseImage() {
        if (baseImage == null) {
            // Prepare used layer references and calculate positions
            TableConfiguration configuration = table.getConfiguration();

            this.positionCalculator = new PositionCalculator(configuration);
            this.searchButtonCalculator = new SearchButtonCalculator(configuration);
            this.pageBarCalculator = new PageBarCalculator(configuration);

            positionCalculator.calculate();
            searchButtonCalculator.calculate();
            pageBarCalculator.calculate();

            // Load image
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File(configuration.getBackgroundPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Init pipeline
            List<ImageLayer> layers = new LinkedList<>();
            layers.add(new CroppingLayer(configuration.getWidth(), configuration.getHeight()));
            layers.add(new BackgroundLayer());
            layers.add(new TableLayer(positionCalculator));
            layers.add(new HeadingLayer(positionCalculator, configuration.getColumnConfigurations()
                    .stream()
                    .map(ColumnConfiguration::getName)
                    .collect(Collectors.toList()))
            );
            layers.add(new SearchButtonLayer(searchButtonCalculator));
            this.baseImage = run(image, layers);
        }

        this.currentImage = baseImage;

        return this;
    }

    /**
     * Create the table content.
     *
     * @return image pipeline
     */
    public ImagePipeline applyTableContent() {
        this.currentImage = new ContentLayer(positionCalculator, tableState.getContent()).apply(currentImage);
        return this;
    }

    /**
     * Create the search button with objective.
     *
     * @return image pipeline
     */
    public ImagePipeline applySearch() {
        this.currentImage = new SearchNameLayer(searchButtonCalculator, tableState.getSearchTerm())
                .apply(new SearchButtonLayer(searchButtonCalculator)
                        .apply(currentImage));

        return this;
    }

    public ImagePipeline applyPageBar() {
        this.currentImage = new PageBarLayer(pageBarCalculator.result(), tableState.getPage(), tableState.getTotalPages())
                .apply(currentImage);
        return this;
    }

    /**
     * Get the created image.
     *
     * @return created image
     */
    public BufferedImage get() {
        return currentImage;
    }

    /**
     * Apply all layers one by one.
     *
     * @param input input image
     * @param layers layers to apply
     * @return created image
     */
    private BufferedImage run(BufferedImage input, List<ImageLayer> layers) {
        BufferedImage inputResult = input;
        for (ImageLayer layer : layers) {
            inputResult = layer.apply(inputResult);
        }

        return inputResult;
    }
}