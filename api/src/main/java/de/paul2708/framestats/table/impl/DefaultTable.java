package de.paul2708.framestats.table.impl;

import de.paul2708.framestats.configuration.TableConfiguration;
import de.paul2708.framestats.internal.TableView;
import de.paul2708.framestats.table.Table;
import de.paul2708.framestats.table.TableRow;
import de.paul2708.framestats.table.TableSearcher;
import de.paul2708.framestats.table.TableUpdater;

import java.util.List;

/**
 * This class is the default implementation of {@link Table}.
 *
 * @author Paul2708
 */
public final class DefaultTable implements Table {

    private final TableConfiguration configuration;

    private TableUpdater updater;
    private TableSearcher searcher;

    /**
     * Create a new default table.
     *
     * @param configuration configuration
     */
    public DefaultTable(TableConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Set the updater routine.
     *
     * @param updater updater
     */
    @Override
    public void setUpdater(TableUpdater updater) {
        this.updater = updater;
    }

    /**
     * Update the table by refreshing the row entries with {@link #setUpdater(TableUpdater)}.
     */
    @Override
    public void update() {
        List<TableRow> rows = updater.update();

        // TODO: Update screen
    }

    /**
     * Set the search routine.
     *
     * @param searcher searcher
     */
    @Override
    public void setSearcher(TableSearcher searcher) {
        this.searcher = searcher;
    }

    /**
     * Search for a name.
     *
     * @param name name to search for
     */
    @Override
    public void search(String name) {
        List<TableRow> rows = searcher.search(name);

        // TODO: Update screen
    }

    /**
     * Get an unmodifiable list of the current displayed rows.
     *
     * @return unmodifiable list of rows
     */
    @Override
    public List<TableRow> getRows() {
        // TODO: Implement me
        return null;
    }

    /**
     * Internal method.
     * Map a table view to this table.
     *
     * @param view table view
     */
    @Override
    public void addView(TableView view) {
        // TODO: Implement me
    }

    @Override
    public void register() {
        // TODO: Implement me
    }
}