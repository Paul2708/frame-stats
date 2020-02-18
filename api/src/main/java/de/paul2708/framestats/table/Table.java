package de.paul2708.framestats.table;

/**
 * This interface represents the functional table.
 * One table refers to every player.
 * The view will be separated internal.
 *
 * @author Paul2708
 */
public interface Table {

    /**
     * Set the updater routine.
     *
     * @param updater updater
     */
    void setUpdater(TableUpdater updater);

    /**
     * Update the table by refreshing the row entries with {@link #setUpdater(TableUpdater)}.
     */
    void update();

    /**
     * Set the search routine.
     *
     * @param searcher searcher
     */
    void setSearcher(TableSearcher searcher);

    /**
     * Search for a name.
     *
     * @param name name to search for
     */
    void search(String name);
}