package de.paul2708.framestats.internal.image;

import de.paul2708.framestats.configuration.ColumnConfiguration;
import de.paul2708.framestats.configuration.TableConfiguration;

import java.awt.Rectangle;

/**
 * This calculator is used to calculate table cells.
 *
 * @author Paul2708
 */
public class PositionCalculator {

    private static final int X_OFFSET = 10;
    private static final int Y_OFFSET = 10;

    private static final int WIDTH_SPACE = 10;
    private static final int HEIGHT_SPACE = 10;

    // TODO: Use HEIGHT_SPACE

    private final TableConfiguration configuration;

    private Rectangle[][] rectangles;

    /**
     * Create a new position calculator.
     *
     * @param configuration table configuration
     */
    public PositionCalculator(TableConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Calculate the table cells based on the given configuration.
     */
    public void calculate() {
        this.rectangles = new Rectangle[configuration.getRows()][configuration.getColumnConfigurations().size()];

        int currentX = X_OFFSET;

        for (int row = 0; row < configuration.getRows(); row++) {
            for (int column = 0; column < configuration.getColumnConfigurations().size(); column++) {
                ColumnConfiguration columnConfig = configuration.getColumnConfigurations().get(column);

                int x = currentX;
                int y = (row + 1) * Y_OFFSET + row * configuration.getRowHeight();
                int width = columnConfig.getMaxWidth();
                int height = configuration.getRowHeight();

                rectangles[row][column] = new Rectangle(x, y, width, height);

                currentX += columnConfig.getMaxWidth() + WIDTH_SPACE;
            }

            currentX = X_OFFSET;
        }
    }

    /**
     * Get the result after calling {@link #calculate()}.
     *
     * @return calculated result
     */
    public Rectangle[][] result() {
        if (rectangles == null) {
            throw new IllegalStateException("Call calculate() before using the result");
        }

        return rectangles;
    }
}