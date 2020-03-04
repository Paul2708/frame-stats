package de.paul2708.framestats.internal.image.calculator;

import de.paul2708.framestats.configuration.TableConfiguration;

import java.awt.Rectangle;

/**
 * Class description.
 *
 * @author Paul2708
 */
public class PageBarCalculator {

    private static final int X = 100;
    private static final int Y = 100;

    private static final int HEIGHT = 20;
    private static final int INFO_WIDTH = 40;
    private static final int SELECTOR_WIDTH = 40;

    // TODO: Use common Calculator interface

    private final TableConfiguration configuration;

    private PageBar pageBar;

    public PageBarCalculator(TableConfiguration configuration) {
        this.configuration = configuration;
    }

    public void calculate() {
        // TODO: Use correct coordinates
        Rectangle back = new Rectangle(420, 200 , 30, 20);
        Rectangle info = new Rectangle(460, 200 , 50, 20);
        Rectangle skip = new Rectangle(520, 200 , 30, 20);

        this.pageBar = new PageBar(back, info, skip);
    }

    public PageBar result() {
        if (pageBar == null) {
            throw new IllegalStateException("Call calculate() before using the result");
        }

        return pageBar;
    }
}