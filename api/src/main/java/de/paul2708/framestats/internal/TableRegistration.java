package de.paul2708.framestats.internal;

import de.paul2708.framestats.internal.renderer.TableRenderer;
import de.paul2708.framestats.table.Table;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Class description.
 *
 * @author Paul2708
 */
public final class TableRegistration {

    private static TableRegistration instance;

    private final Map<Table, TableRenderer[][]> tables;
    // private final Map<UUID, List<TableView>> views;

    private TableRegistration() {
        this.tables = new HashMap<>();
    }

    public void registerPlayer(Player player) {
        tables.forEach((table, maps) -> {
            TableView view = new TableView(table, maps, player);

            table.addView(view);
            view.draw();
        });
    }

    public void registerTable(Table table, TableRenderer[][] renderers) {
        tables.put(table, renderers);
    }

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


    public static TableRegistration getInstance() {
        if (TableRegistration.instance == null) {
            TableRegistration.instance = new TableRegistration();
        }

        return TableRegistration.instance;
    }
}
