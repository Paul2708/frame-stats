package de.paul2708.framestats.table.impl;

import de.paul2708.framestats.configuration.TableConfiguration;
import de.paul2708.framestats.internal.TableView;
import de.paul2708.framestats.internal.frame.FramePlacer;
import de.paul2708.framestats.table.Table;
import de.paul2708.framestats.table.TableRow;
import de.paul2708.framestats.table.TableSearcher;
import de.paul2708.framestats.table.TableUpdater;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is the default implementation of {@link Table}.
 *
 * @author Paul2708
 */
public final class DefaultTable implements Table {

    private final TableConfiguration configuration;

    private TableUpdater updater;
    private TableSearcher searcher;

    private Set<TableView> views;

    /**
     * Create a new default table.
     *
     * @param configuration configuration
     */
    public DefaultTable(TableConfiguration configuration) {
        this.configuration = configuration;

        this.views = new HashSet<>();
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

        for (TableView view : views) {
            view.update();
        }
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

        for (TableView view : views) {
            view.update();
        }
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
        views.add(view);
    }

    /**
     * Register the table.
     * The item frames will be searched.
     * Several internal listener will be registered (only once!).
     */
    @Override
    public void register() {
        FramePlacer placer = new FramePlacer();
        Block[][] wall = placer.construct(configuration.getLeftLowerCorner(), configuration.getRightUpperCorner());
        ItemFrame[][] frames = placer.search(wall);

        for (ItemFrame[] frame : frames) {
            for (ItemFrame itemFrame : frame) {
                itemFrame.setItem(new ItemStack(Material.APPLE));
            }
        }
    }
}