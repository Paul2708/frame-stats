package de.paul2708.framestats.internal.image.layer;

import de.paul2708.framestats.internal.image.PositionCalculator;
import de.paul2708.framestats.table.TableRow;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * This layer draws the content (every table row) to the image.
 *
 * @author Paul2708
 */
public class ContentLayer implements ImageLayer {

    // TODO: Support color codes

    private static final Color FONT_COLOR = Color.WHITE;

    private final PositionCalculator calculator;
    private final List<TableRow> rows;

    /**
     * Create a new heading layer.
     *
     * @param calculator position calculator, has to hold the result
     * @param rows table rows that will be rendered
     */
    public ContentLayer(PositionCalculator calculator, List<TableRow> rows) {
        this.calculator = calculator;
        this.rows = rows;
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

        graphics.setColor(ContentLayer.FONT_COLOR);

        Rectangle[][] rectangles = calculator.result();

        for (int i = 0; i < rows.size(); i++) {
            TableRow row = rows.get(i);

            for (int j = 0; j < row.getEntries().length; j++) {
                String cell = row.getEntries()[j];

                // [i + 1] as [0] is the heading row
                drawText(cell, graphics, rectangles[i + 1][j]);
            }
        }

        return tableImage;
    }
}