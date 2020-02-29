package de.paul2708.framestats.internal.image.layer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * This interface represents an image layer that is used as pipeline step.
 *
 * @author Paul2708
 */
@FunctionalInterface
public interface ImageLayer {

    /**
     * Apply the layer onto the given image.
     * Note: The input image should not be changed.
     *
     * @see ImageLayer#clone(BufferedImage)
     * @param image base image
     * @return layered image
     */
    BufferedImage apply(BufferedImage image);

    /**
     * Clone a buffered image.
     *
     * @param image image to clone
     * @return copied image
     */
    default BufferedImage clone(BufferedImage image) {
        ColorModel colorModel = image.getColorModel();
        boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);

        return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    }

    /**
     * Draw text in the center of the rectangle.
     *
     * @param text text to draw
     * @param color text color
     * @param graphics graphics of the image
     * @param rectangle rectangle border
     */
    default void drawText(String text, Color color, Graphics2D graphics, Rectangle rectangle) {
        // Draw border
        graphics.draw(rectangle);

        // Calculate text position
        Font font = graphics.getFont();
        FontRenderContext context = graphics.getFontRenderContext();
        graphics.setFont(font);

        int textWidth = (int) font.getStringBounds(text, context).getWidth();
        LineMetrics lineMetrics = font.getLineMetrics(text, context);

        int textHeight = (int) (lineMetrics.getAscent() + lineMetrics.getDescent());
        int x = (int) (rectangle.getX() + (rectangle.getWidth() - textWidth) / 2);
        int y = (int) (rectangle.getY() + (rectangle.getHeight() + textHeight) / 2 - lineMetrics.getDescent());

        // Draw it
        Color oldColor = graphics.getColor();

        graphics.setColor(color);
        graphics.drawString(text, x, y);
        graphics.setColor(oldColor);
    }
}