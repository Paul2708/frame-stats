package de.paul2708.framestats.example;

import de.paul2708.framestats.configuration.TableConfiguration;
import de.paul2708.framestats.exception.InvalidConfigurationException;
import de.paul2708.framestats.table.Table;
import de.paul2708.framestats.table.TableRow;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collections;

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
        try {
            TableConfiguration configuration = TableConfiguration.load("table.yml");
            Table table = Table.create(configuration);

            table.setUpdater(() -> Arrays.asList(
                    new TableRow("1", "Paul2708", "100", "15"),
                    new TableRow("2", "Tommy", "50", "30"),
                    new TableRow("3", "Lisa", "10", "2"),
                    new TableRow("4", "Steve", "0", "0")
            ));
            table.setSearcher(name -> {
                if (name.contains("Paul")) {
                    return Collections.singletonList(new TableRow("1", "Paul2708", "100", "15"));
                } else {
                    return Collections.emptyList();
                }
            });

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