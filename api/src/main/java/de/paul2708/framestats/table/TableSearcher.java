package de.paul2708.framestats.table;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * This interface searches an object and returns a matching list of rows.
 *
 * @author Paul2708
 */
@FunctionalInterface
public interface TableSearcher {

    /**
     * Search for a certain name or rank.
     *
     * @param player player that searches the name
     * @param name search object (typed in by user)
     * @return matching results
     */
    List<TableRow> search(Player player, String name);
}
