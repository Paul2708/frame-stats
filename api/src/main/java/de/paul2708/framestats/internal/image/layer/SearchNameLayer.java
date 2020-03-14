package de.paul2708.framestats.internal.image.layer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * This layer draws the searching name.
 *
 * @author Paul2708
 */
public class SearchNameLayer implements ImageLayer {

    private static final Color FONT_COLOR = Color.WHITE;

    private final Rectangle button;
    private final String name;

    public SearchNameLayer(Rectangle button, String name) {
        this.button = button;
        this.name = name;
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

        drawText(name, FONT_COLOR, graphics, button);

        return tableImage;
    }
}