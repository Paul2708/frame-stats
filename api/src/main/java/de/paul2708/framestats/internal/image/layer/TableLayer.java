package de.paul2708.framestats.internal.image.layer;

import de.paul2708.framestats.internal.image.calculator.PositionCalculator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * This layer draws the table cells.
 *
 * @author Paul2708
 */
public class TableLayer implements ImageLayer {

    private static final Color COLOR = Color.RED;

    private final PositionCalculator calculator;

    /**
     * Create a new table layer.
     *
     * @param calculator position calculator, has to hold the result
     */
    public TableLayer(PositionCalculator calculator) {
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

        graphics.setColor(TableLayer.COLOR);

        for (Rectangle[] rectangles : calculator.result()) {
            for (Rectangle rectangle : rectangles) {
                graphics.fillRect((int) rectangle.getX(), (int) rectangle.getY(),
                        (int) rectangle.getWidth(), (int) rectangle.getHeight());
            }
        }

        return tableImage;
    }
}