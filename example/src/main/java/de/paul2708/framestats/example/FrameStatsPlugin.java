package de.paul2708.framestats.example;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

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