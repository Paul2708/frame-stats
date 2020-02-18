package de.paul2708.framestats.configuration;

import de.paul2708.framestats.exception.InvalidConfigurationException;

/**
 * This class holds all needed information about a table.
 * This includes columns, headings and other data.
 *
 * @author Paul2708
 */
public final class TableConfiguration {

    private TableConfiguration() {

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
        // TODO: Implement me
        return null;
    }
}