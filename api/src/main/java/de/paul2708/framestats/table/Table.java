package de.paul2708.framestats.table;

import de.paul2708.framestats.configuration.TableConfiguration;
import de.paul2708.framestats.internal.TableView;
import de.paul2708.framestats.table.impl.DefaultTable;

import java.util.List;

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

    /**
     * Get an unmodifiable list of the current displayed rows.
     *
     * @return unmodifiable list of rows
     */
    List<TableRow> getRows();

    /**
     * Internal method.
     * Map a table view to this table.
     *
     * @param view table view
     */
    void addView(TableView view);

    /**
     * Register the table.
     * The item frames will be searched.
     * Several internal listener will be registered (only once!).
     */
    void register();

    /**
     * Get the table configuration.
     *
     * @return configuration
     */
    TableConfiguration getConfiguration();

    /**
     * Create a new table instance by configuration.
     * After configuring it use {@link #register()}.
     *
     * @see TableConfiguration#load(String)
     * @param configuration already valid and loaded configuration
     * @return created table
     */
    static Table create(TableConfiguration configuration) {
        return new DefaultTable(configuration);
    }
}