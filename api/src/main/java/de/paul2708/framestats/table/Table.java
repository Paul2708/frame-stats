package de.paul2708.framestats.table;

import de.paul2708.framestats.configuration.TableConfiguration;
import de.paul2708.framestats.internal.interaction.TableInteraction;
import de.paul2708.framestats.internal.state.TableState;
import de.paul2708.framestats.table.impl.DefaultTable;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * This interface represents the functional table.
 * One table refers to every player.
 * The view and its states will be separated internal.
 *
 * @author Paul2708
 */
public interface Table {

    /**
     * Set the search routine.
     * It will call {@link TableSearcher#search(Player, String)} if a player searches for a term.
     * <br>
     * Has to be set before {@link #register()} the table.
     *
     * @see TableSearcher#search(Player, String)
     * @see #search(Player, String)
     * @param searcher table searcher
     */
    void setSearcher(TableSearcher searcher);

    /**
     * Fill the table with content.
     * It has to be called one time - before registering the table.
     *
     * @param rows list of table row entries
     */
    void fill(List<TableRow> rows);

    /**
     * Let a player search for a given term.
     * It updates the players table with rows that matches the result.
     *
     * @see #setSearcher(TableSearcher)
     * @param term term to search for
     */
    void search(Player player, String term);

    /**
     * Get the players table state.
     *
     * @param player player to get the state for
     * @return internal table state
     */
    TableState getState(Player player);

    /**
     * Get the filled content.
     *
     * @see #fill(List)
     * @return unmodifiable list of table rows
     */
    List<TableRow> getContent();

    /**
     * Register the table.
     * The item frames will be searched.
     * Several internal listener will be registered (only once!).
     */
    void register();

    /**
     * Get an unmodifiable list of all (implicit registered) interactions.
     *
     * @return unmodifiable list of interactions
     */
    List<TableInteraction> getInteractions();

    /**
     * Get the table configuration.
     *
     * @return configuration
     */
    TableConfiguration getConfiguration();

    /**
     * Create a new table instance by configuration.
     * After configuring it use {@link #register()} to register the table.
     *
     * @see #register()
     * @see TableConfiguration#load(String)
     * @param configuration already valid and loaded configuration
     * @return created table
     */
    static Table create(TableConfiguration configuration) {
        return new DefaultTable(configuration);
    }
}