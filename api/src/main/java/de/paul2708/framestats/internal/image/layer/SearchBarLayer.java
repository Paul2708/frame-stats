package de.paul2708.framestats.internal.image.layer;

import de.paul2708.framestats.internal.image.calculator.PageBar;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * This layer draws the search button.
 *
 * @author Paul2708
 */
public class SearchBarLayer implements ImageLayer {

    private static final Color BUTTON_COLOR = Color.GRAY;

    private final PageBar pageBar;

    public SearchBarLayer(PageBar pageBar) {
        this.pageBar = pageBar;
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

        graphics.setColor(SearchBarLayer.BUTTON_COLOR);

        graphics.fill(pageBar.getBack());
        graphics.fill(pageBar.getInfo());
        graphics.fill(pageBar.getSkip());

        drawText("-1", Color.WHITE, graphics, pageBar.getBack());
        drawText("+1", Color.WHITE, graphics, pageBar.getSkip());

        return tableImage;
    }
}