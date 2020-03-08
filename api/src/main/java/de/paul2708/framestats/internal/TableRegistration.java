package de.paul2708.framestats.internal;

import de.paul2708.framestats.internal.renderer.TableRenderer;
import de.paul2708.framestats.internal.state.TableState;
import de.paul2708.framestats.table.Table;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * This singleton holds all registered tables and its states per player.
 *
 * @see Table#register()
 * @author Paul2708
 */
public final class TableRegistration {

    // TODO: Use DI instead singleton

    private static TableRegistration instance;

    private final Map<Table, TableRenderer[][]> tables;
    private final Set<Player> players;

    /**
     * Create a new table registration with an empty map of registered tables.
     */
    private TableRegistration() {
        this.tables = new HashMap<>();
        this.players = new HashSet<>();
    }

    /**
     * Create an internal table state for each table and add an view for them.
     *
     * @param player player to register
     */
    public void registerPlayer(Player player) {
        if (players.contains(player)) {
            return;
        }

        tables.forEach((table, maps) -> {
            TableState state = table.getState(player);
            TableView view = new TableView(player, state, maps);

            state.addView(view);
            view.redraw();
        });
        players.add(player);
    }

    /**
     * Register a table.
     * All registered tables will be set for the players on join.
     *
     * @see #registerPlayer(Player)
     * @param table table to register
     * @param renderers visual table renderers for the table
     */
    public void registerTable(Table table, TableRenderer[][] renderers) {
        tables.put(table, renderers);
    }

    /**
     * Find a table by an item frame.
     *
     * @param frame item frame
     * @return empty if none table was found, otherwise found table
     */
    public Optional<Table> findByFrame(ItemFrame frame) {
        for (Map.Entry<Table, TableRenderer[][]> entry : tables.entrySet()) {
            TableRenderer[][] rendererArray = entry.getValue();

            for (TableRenderer[] tableRenderers : rendererArray) {
                for (TableRenderer tableRenderer : tableRenderers) {
                    if (tableRenderer.getFrame().equals(frame)) {
                        return Optional.ofNullable(entry.getKey());
                    }
                }
            }
        }

        return Optional.empty();
    }

    /**
     * Get the singleton instance of this registration class.
     *
     * @return singleton instance
     */
    public static TableRegistration getInstance() {
        if (TableRegistration.instance == null) {
            TableRegistration.instance = new TableRegistration();
        }

        return TableRegistration.instance;
    }
}