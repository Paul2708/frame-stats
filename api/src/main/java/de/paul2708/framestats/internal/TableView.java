package de.paul2708.framestats.internal;

import de.paul2708.framestats.table.Table;
import org.bukkit.entity.Player;

/**
 * Class description.
 *
 * @author Paul2708
 */
public final class TableView {

    private final Table table;
    private final Player player;

    public TableView(Table table, Player player) {
        this.table = table;
        this.player = player;
    }

    public void update() {
        // TODO: Implement me
    }
}