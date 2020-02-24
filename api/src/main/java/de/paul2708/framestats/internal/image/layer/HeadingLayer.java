package de.paul2708.framestats.internal.image.layer;

import de.paul2708.framestats.internal.image.calculator.PositionCalculator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * This layer draws the column headings into the cells.
 *
 * @author Paul2708
 */
public class HeadingLayer implements ImageLayer {

    private static final Color FONT_COLOR = Color.WHITE;

    private final PositionCalculator calculator;
    private final List<String> headings;

    /**
     * Create a new heading layer.
     *
     * @param calculator position calculator, has to hold the result
     */
    public HeadingLayer(PositionCalculator calculator, List<String> headings) {
        this.calculator = calculator;
        this.headings = headings;
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

        graphics.setColor(HeadingLayer.FONT_COLOR);

        Rectangle[][] rectangles = calculator.result();

        for (int i = 0; i < rectangles[0].length; i++) {
            Rectangle headingCell = rectangles[0][i];

            drawText(headings.get(i), graphics, headingCell);
        }

        return tableImage;
    }
}