package de.paul2708.framestats.table;

import java.util.List;

/**
 * This interface updates the table by replacing the current list of rows by a new one.
 *
 * @author Paul2708
 */
@FunctionalInterface
public interface TableUpdater {

    /**
     * Update the table.
     * Gets called by {@link Table#update()}.
     *
     * @return list of updated table rows
     */
    List<TableRow> update();
}
