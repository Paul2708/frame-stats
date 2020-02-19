package de.paul2708.framestats.configuration;

import de.paul2708.framestats.exception.InvalidConfigurationException;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * This class holds all needed information about a table.
 * This includes columns, headings and other data.
 *
 * @author Paul2708
 */
public final class TableConfiguration {

    private static final String PATH = "./plugins/Frame-Stats/";

    private final FileConfiguration configuration;

    /**
     * Load the yaml configuration.
     *
     * @param path an existing absolute path to the configuration
     */
    private TableConfiguration(String path) {
        this.configuration = YamlConfiguration.loadConfiguration(new File(path));
    }

    /**
     * Get the left lower corner of the frames wall.
     *
     * @return left lower corner
     */
    public Location getLeftLowerCorner() {
        return (Location) configuration.get("locations.left-lower-corner");
    }

    /**
     * Get the right upper corner of the frames wall.
     *
     * @return right upper corner
     */
    public Location getRightUpperCorner() {
        return (Location) configuration.get("locations.right-upper-corner");
    }

    /**
     * Load a table configuration from file.
     *
     * @param path relative path from <code>./plugins/Frame-Stats</code> to <code>.yml</code> file
     * @return loaded table configuration
     * @throws InvalidConfigurationException if the configuration was invalid,
     * see more in {@link InvalidConfigurationException#getMessage()}
     */
    public static TableConfiguration load(String path) throws InvalidConfigurationException {
        String fullPath = TableConfiguration.PATH + path;
        if (Files.notExists(Paths.get(fullPath))) {
            throw new InvalidConfigurationException(String.format("Configuration file %s not found", fullPath));
        }

        TableConfiguration configuration = new TableConfiguration(fullPath);

        Objects.requireNonNull(configuration.getLeftLowerCorner(), "Left lower corner must be set");
        Objects.requireNonNull(configuration.getRightUpperCorner(), "Right upper corner must be set");

        return configuration;
    }
}