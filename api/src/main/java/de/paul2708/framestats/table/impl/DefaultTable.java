package de.paul2708.framestats.table.impl;

import de.paul2708.framestats.configuration.TableConfiguration;
import de.paul2708.framestats.internal.TableRegistration;
import de.paul2708.framestats.internal.frame.FramePlacer;
import de.paul2708.framestats.internal.interaction.PreviousPageInteraction;
import de.paul2708.framestats.internal.interaction.SearchInteraction;
import de.paul2708.framestats.internal.interaction.NextPageInteraction;
import de.paul2708.framestats.internal.interaction.TableInteraction;
import de.paul2708.framestats.internal.renderer.TableRenderer;
import de.paul2708.framestats.internal.state.TableState;
import de.paul2708.framestats.table.Table;
import de.paul2708.framestats.table.TableRow;
import de.paul2708.framestats.table.TableSearcher;
import de.paul2708.framestats.table.image.HeadRequestHandler;
import org.bukkit.block.Block;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class is the default implementation of {@link Table}.
 *
 * @author Paul2708
 */
public final class DefaultTable implements Table {

    private final TableConfiguration configuration;

    private TableSearcher searcher;
    private HeadRequestHandler headRequestHandler;
    private List<TableRow> tableContent;

    private final Map<Player, TableState> states;
    private final List<TableInteraction> interactions;

    /**
     * Create a new default table.
     *
     * @param configuration table configuration
     */
    public DefaultTable(TableConfiguration configuration) {
        this.configuration = configuration;

        this.states = new HashMap<>();
        this.interactions = new LinkedList<>();

        // Load interactions
        interactions.add(new SearchInteraction(configuration.getSearchButton()));
        interactions.add(new PreviousPageInteraction(configuration.getPageBar()));
        interactions.add(new NextPageInteraction(configuration.getPageBar()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSearcher(TableSearcher searcher) {
        this.searcher = searcher;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHeadRequestHandler(HeadRequestHandler handler) {
        this.headRequestHandler = handler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(List<TableRow> rows) {
        this.tableContent = new ArrayList<>(rows);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void search(Player player, String term) {
        getState(player).setContent(searcher.search(player, term));
        getState(player).setSearchTerm(term);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableState getState(Player player) {
        if (!states.containsKey(player)) {
            states.put(player, TableState.create(this));
        }

        return states.get(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TableRow> getContent() {
        return tableContent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register() {
        if (searcher == null) {
            throw new IllegalStateException("Searcher must be set before registering the table.");
        }
        if (headRequestHandler == null) {
            throw new IllegalStateException("Head request handler must be set before registering the table.");
        }
        if (tableContent == null) {
            throw new IllegalStateException("Table must be filled before registering it.");
        }

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
     * {@inheritDoc}
     */
    @Override
    public List<TableInteraction> getInteractions() {
        return interactions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableConfiguration getConfiguration() {
        return configuration;
    }
}