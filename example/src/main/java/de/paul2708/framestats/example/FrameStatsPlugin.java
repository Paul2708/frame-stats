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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<PlayerStatistics> database = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            database.add(PlayerStatistics.create());
        }

        Collections.sort(database);
        for (int i = 0; i < database.size(); i++) {
            database.get(i).setRank(i + 1);
        }

        // Api usage
        TablePluginHook.initialize(this);

        try {
            TableConfiguration configuration = TableConfiguration.load("table.yml");
            Table table = Table.create(configuration);

            table.setUpdater(() ->
                database.stream()
                        .map(stats -> new TableRow(stats.getRank(), stats.getName(), stats.getKills(),
                            stats.getDeaths(), stats.getPoints()))
                        .collect(Collectors.toList())
            );

            table.setSearcher(name ->
                database.stream()
                        .filter(stats -> stats.getName().contains(name))
                        .map(stats -> new TableRow(stats.getRank(), stats.getName(), stats.getKills(),
                                stats.getDeaths(), stats.getPoints()))
                        .collect(Collectors.toList())
            );

            table.register();

            table.update();
        } catch (InvalidConfigurationException e) {
            System.err.println("Invalid configuration: " + e.getMessage());
        }
    }

    /**
     * Called, if the plugin was disabled.
     */
    @Override
    public void onDisable() {

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