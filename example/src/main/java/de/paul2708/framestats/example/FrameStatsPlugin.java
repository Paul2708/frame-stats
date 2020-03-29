package de.paul2708.framestats.example;

import de.paul2708.framestats.TablePluginHook;
import de.paul2708.framestats.configuration.TableConfiguration;
import de.paul2708.framestats.exception.InvalidConfigurationException;
import de.paul2708.framestats.table.Table;
import de.paul2708.framestats.table.TableRow;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This {@link JavaPlugin} represents the main plugin.
 *
 * @author Paul2708
 */
public class FrameStatsPlugin extends JavaPlugin {

    /**
     * Standard message prefix.
     */
    public static final String PREFIX = "§8[§3Frame-Stats§8] §7";

    private static FrameStatsPlugin instance;

    private List<PlayerStatistics> database;

    /**
     * Called, if the plugin is loaded.
     */
    @Override
    public void onLoad() {
        FrameStatsPlugin.instance = this;
    }

    /**
     * Called, if the plugin was enabled.
     */
    @Override
    public void onEnable() {
        // Simulate player stats database
        this.database = new ArrayList<>();
        refreshDatabase();

        // Api usage
        TablePluginHook.initialize(this);

        TableConfiguration configuration = null;

        try {
            configuration = TableConfiguration.load("table.yml");
        } catch (InvalidConfigurationException e) {
            System.err.println("Invalid configuration: " + e.getMessage());
        }

        Table table = Table.create(configuration);

        table.setSearcher((player, name) ->
                database.stream()
                        .filter(stats -> stats.getName().contains(name))
                        .map(stats -> new TableRow(stats.getRank(), "", stats.getName(), stats.getKills(),
                                stats.getDeaths(), stats.getPoints()))
                        .collect(Collectors.toList())
        );
        table.fill(database.stream()
                .map(stats -> new TableRow(stats.getRank(), "", stats.getName(), stats.getKills(),
                        stats.getDeaths(), stats.getPoints()))
                .collect(Collectors.toList()));

        table.register();
    }

    /**
     * Called, if the plugin was disabled.
     */
    @Override
    public void onDisable() {

    }

    private void refreshDatabase() {
        this.database.clear();

        for (int i = 0; i < 500; i++) {
            database.add(PlayerStatistics.create(i));
        }

        Collections.sort(database);
        for (int i = 0; i < database.size(); i++) {
            database.get(i).setRank(i + 1);
        }
    }

    /**
     * Register some listener.
     *
     * @param listener listener
     */
    private void registerListener(Listener... listener) {
        for (Listener single : listener) {
            Bukkit.getPluginManager().registerEvents(single, this);
        }
    }

    /**
     * Get the singleton plugin instance.
     *
     * @return plugin instance
     */
    public static FrameStatsPlugin getInstance() {
        return FrameStatsPlugin.instance;
    }
}