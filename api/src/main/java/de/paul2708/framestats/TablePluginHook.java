package de.paul2708.framestats;

import de.paul2708.framestats.internal.listener.EntityDamageByEntityListener;
import de.paul2708.framestats.internal.listener.HangingBreakListener;
import de.paul2708.framestats.internal.listener.MapClickListener;
import de.paul2708.framestats.internal.listener.PlayerInteractAtEntityListener;
import de.paul2708.framestats.internal.listener.PlayerInteractEntityListener;
import de.paul2708.framestats.internal.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class is used to hook into the plugin that uses the api.
 *
 * @author Paul2708
 */
public final class TablePluginHook {

    private static JavaPlugin plugin;

    /**
     * Private constructor to prevent object creation.
     */
    private TablePluginHook() {
        throw new IllegalAccessError();
    }

    /**
     * Initialize the hook by registering internal listener.
     * <br>
     * This method has to be called before calling api methods.
     *
     * @param plugin plugin that uses the api
     */
    public static void initialize(JavaPlugin plugin) {
        TablePluginHook.plugin = plugin;

        // Register listener
        registerListener(plugin,
                new PlayerJoinListener(),
                new PlayerInteractEntityListener(),
                new PlayerInteractAtEntityListener(),
                new MapClickListener(),
                new HangingBreakListener(),
                new EntityDamageByEntityListener());
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Register some listener.
     *
     * @param listener listener
     */
    private static void registerListener(JavaPlugin plugin, Listener... listener) {
        for (Listener single : listener) {
            Bukkit.getPluginManager().registerEvents(single, plugin);
        }
    }
}