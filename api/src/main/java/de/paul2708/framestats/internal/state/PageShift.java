package de.paul2708.framestats.internal.state;

/**
 * This enum holds possible page shifts like previous and next to switch between pages.
 *
 * @author Paul2708
 */
public enum PageShift {

    /**
     * Get the previous page.
     */
    PREVIOUS(-1),

    /**
     * Get the next page.
     */
    NEXT(1);

    private final int delta;

    /**
     * Create a new page shift.
     *
     * @param delta delta to add on the current page index
     */
    PageShift(int delta) {
        this.delta = delta;
    }

    /**
     * Get the page delta.
     * Adding the delta to the current page index will result in the new (changed) page index.
     *
     * @return page delta
     */
    public int getDelta() {
        return delta;
    }
}