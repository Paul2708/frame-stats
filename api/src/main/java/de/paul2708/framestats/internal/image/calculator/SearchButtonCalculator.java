package de.paul2708.framestats.internal.image.calculator;

import de.paul2708.framestats.configuration.SearchButtonConfiguration;
import de.paul2708.framestats.configuration.TableConfiguration;

import java.awt.Rectangle;

/**
 * Class description.
 *
 * @author Paul2708
 */
public class SearchButtonCalculator {

    // TODO: Use common Calculator interface

    private final TableConfiguration configuration;

    private Rectangle button;

    public SearchButtonCalculator(TableConfiguration configuration) {
        this.configuration = configuration;
    }

    public void calculate() {
        SearchButtonConfiguration position = configuration.getSearchButtonConfiguration();

        this.button = new Rectangle(configuration.getWidth() * 128 - position.getWidth() - position.getXPadding(),
                position.getYPadding(), position.getWidth(), position.getHeight());
    }

    public Rectangle result() {
        if (button == null) {
            throw new IllegalStateException("Call calculate() before using the result");
        }

        return button;
    }
}