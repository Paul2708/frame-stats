package de.paul2708.framestats.internal.image.layer;

import de.paul2708.framestats.internal.image.calculator.SearchButtonCalculator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * This layer draws the search button.
 *
 * @author Paul2708
 */
public class SearchButtonLayer implements ImageLayer {

    private static final Color BUTTON_COLOR = Color.GRAY;

    private final SearchButtonCalculator calculator;

    public SearchButtonLayer(SearchButtonCalculator calculator) {
        this.calculator = calculator;
    }

    /**
     * Apply the layer onto the given image.
     * Note: The input image should not be changed.
     *
     * @param image base image
     * @return layered image
     * @see ImageLayer#clone(BufferedImage)
     */
    @Override
    public BufferedImage apply(BufferedImage image) {
        BufferedImage tableImage = clone(image);
        Graphics2D graphics = tableImage.createGraphics();

        graphics.setColor(SearchButtonLayer.BUTTON_COLOR);

        graphics.fill(calculator.result());

        return tableImage;
    }
}