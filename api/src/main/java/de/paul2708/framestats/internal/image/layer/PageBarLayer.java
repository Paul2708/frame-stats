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
public class PageBarLayer implements ImageLayer {

    private static final Color BUTTON_COLOR = Color.GRAY;

    private final PageBar pageBar;
    private final int page;
    private final int totalPages;

    public PageBarLayer(PageBar pageBar, int page, int totalPages) {
        this.pageBar = pageBar;
        this.page = page;
        this.totalPages = totalPages;
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

        graphics.setColor(PageBarLayer.BUTTON_COLOR);

        graphics.fill(pageBar.getPrevious());
        graphics.fill(pageBar.getInfo());
        graphics.fill(pageBar.getNext());

        drawText("-1", Color.WHITE, graphics, pageBar.getPrevious());
        drawText(page + " / " + totalPages, Color.WHITE, graphics, pageBar.getInfo());
        drawText("+1", Color.WHITE, graphics, pageBar.getNext());

        return tableImage;
    }
}