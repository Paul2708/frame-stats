package de.paul2708.framestats.internal.image.calculator;

import java.awt.Rectangle;

/**
 * Class description.
 *
 * @author Paul2708
 */
public class PageBar {

    private final Rectangle back;
    private final Rectangle info;
    private final Rectangle skip;

    public PageBar(Rectangle back, Rectangle info, Rectangle skip) {
        this.back = back;
        this.info = info;
        this.skip = skip;
    }

    public Rectangle getBack() {
        return back;
    }

    public Rectangle getInfo() {
        return info;
    }

    public Rectangle getSkip() {
        return skip;
    }
}
