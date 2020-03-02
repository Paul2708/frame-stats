package de.paul2708.framestats.table.impl;

import de.paul2708.framestats.configuration.TableConfiguration;
import de.paul2708.framestats.internal.TableRegistration;
import de.paul2708.framestats.internal.TableView;
import de.paul2708.framestats.internal.frame.FramePlacer;
import de.paul2708.framestats.internal.interaction.SearchInteraction;
import de.paul2708.framestats.internal.interaction.TableInteraction;
import de.paul2708.framestats.internal.renderer.TableRenderer;
import de.paul2708.framestats.table.Table;
import de.paul2708.framestats.table.TableRow;
import de.paul2708.framestats.table.TableSearcher;
import org.bukkit.block.Block;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is the default implementation of {@link Table}.
 *
 * @author Paul2708
 */
public final class DefaultTable implements Table {

    private final TableConfiguration configuration;

    private TableSearcher searcher;
    private List<TableRow> defaultRows;

    private final Set<TableView> views;
    private final Map<Player, List<TableRow>> playerRows;
    private final List<TableInteraction> interactions;

    /**
     * Create a new default table.
     *
     * @param configuration configuration
     */
    public DefaultTable(TableConfiguration configuration) {
        this.configuration = configuration;

        this.views = new HashSet<>();
        this.playerRows = new HashMap<>();
        this.interactions = new LinkedList<>();

        // Load interactions
        interactions.add(new SearchInteraction(configuration));
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
    public void search(Player player, String name) {
        this.playerRows.put(player,
                searcher.search(player, name).stream().limit(configuration.getRows() - 1).collect(Collectors.toList()));

        for (TableView view : views) {
            view.drawSearch(name);
        }
    }

    @Override
    public void fill(List<TableRow> rows) {
        this.defaultRows = rows.stream().limit(configuration.getRows() - 1).collect(Collectors.toList());
    }

    /**
     * Get an unmodifiable list of the current displayed rows.
     *
     * @return unmodifiable list of rows
     */
    @Override
    public List<TableRow> getRows(Player player) {
        // TODO: Implement me
        return playerRows.getOrDefault(player, defaultRows);
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
        MapView[][] fill = placer.fill(frames);

        TableRenderer[][] renderers = new TableRenderer[fill.length][fill[0].length];

        for (int i = 0; i < fill.length; i++) {
            for (int j = 0; j < fill[i].length; j++) {
                MapView view = fill[i][j];

                List<MapRenderer> list = new LinkedList<>(view.getRenderers());

                for (MapRenderer renderer : list) {
                    view.removeRenderer(renderer);
                }

                renderers[i][j] = new TableRenderer(frames[i][j]);
                view.addRenderer(renderers[i][j]);
            }
        }

        TableRegistration.getInstance().registerTable(this, renderers);
    }

    /**
     * Get the table configuration.
     *
     * @return configuration
     */
    @Override
    public TableConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Get an unmodifiable list of all (implicit registered) interactions.
     *
     * @return unmodifiable list of interactions
     */
    @Override
    public List<TableInteraction> getInteractions() {
        return interactions;
    }
}