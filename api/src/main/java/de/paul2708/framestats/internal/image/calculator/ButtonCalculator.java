package de.paul2708.framestats.internal.image.calculator;

import de.paul2708.framestats.configuration.TableConfiguration;

import java.awt.Rectangle;

/**
 * Class description.
 *
 * @author Paul2708
 */
public class ButtonCalculator {

    private static final int X_PADDING = 10;
    private static final int Y_PADDING = 10;

    private static final int WIDTH = 160;
    private static final int HEIGHT = 20;

    // TODO: Use common Calculator interface
    // TODO: Use configuration for padding

    private final TableConfiguration configuration;

    private Rectangle button;

    public ButtonCalculator(TableConfiguration configuration) {
        this.configuration = configuration;
    }

    public void calculate() {
        this.button = new Rectangle(configuration.getWidth() * 128 - WIDTH - X_PADDING,
                Y_PADDING, WIDTH, HEIGHT);
    }

    public Rectangle result() {
        if (button == null) {
            throw new IllegalStateException("Call calculate() before using the result");
        }

        return button;
    }
}