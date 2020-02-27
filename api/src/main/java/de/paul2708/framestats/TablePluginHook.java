package de.paul2708.framestats;

import de.paul2708.framestats.internal.listener.MapClickListener;
import de.paul2708.framestats.internal.listener.PlayerInteractAtEntityListener;
import de.paul2708.framestats.internal.listener.PlayerInteractEntityListener;
import de.paul2708.framestats.internal.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class is used to hook into the plugin that uses the api.
 *
 * @author Paul2708
 */
public final class TablePluginHook {

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
        // TODO: Add registerListener method

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEntityListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractAtEntityListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new MapClickListener(), plugin);
    }
}