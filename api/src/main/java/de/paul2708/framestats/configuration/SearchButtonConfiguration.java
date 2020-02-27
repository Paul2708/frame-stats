package de.paul2708.framestats.configuration;

import de.paul2708.framestats.exception.InvalidConfigurationException;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

/**
 * This class holds configuration data about the search button position.
 *
 * @author Paul2708
 */
@SerializableAs("Search-Button")
public final class SearchButtonConfiguration implements ConfigurationSerializable {

    // TODO: Add general verify method

    private final int xPadding;
    private final int yPadding;

    private final int width;
    private final int height;

    /**
     * Create a new column configuration by map.
     * Required by {@link ConfigurationSerializable}.
     *
     * @param serializedMap map with column attributes
     */
    public SearchButtonConfiguration(Map<String, Object> serializedMap) {
        this.xPadding = (int) serializedMap.get("x");
        this.yPadding = (int) serializedMap.get("y");
        this.width = (int) serializedMap.get("width");
        this.height = (int) serializedMap.get("height");
    }

    /**
     * Verify the configuration settings.
     *
     * @throws InvalidConfigurationException if given attributes are not well-configured
     */
    public void verify() throws InvalidConfigurationException {
        if (width < 0) {
            throw new InvalidConfigurationException("Width has to be positive");
        }
        if (height <= 0) {
            throw new InvalidConfigurationException("Height has to be positive");
        }
    }

    /**
     * Get the x padding relative the right side.
     *
     * @return x padding relative to right side
     */
    public int getXPadding() {
        return xPadding;
    }

    /**
     * Get the y padding relative the upper side.
     *
     * @return y padding relative to upper side
     */
    public int getYPadding() {
        return yPadding;
    }

    /**
     * Get the button width.
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the button height.
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Creates a Map representation of this class.
     * <p>
     * This class must provide a method to restore this class, as defined in
     * the {@link ConfigurationSerializable} interface javadocs.
     *
     * @return Map containing the current state of this class
     */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        map.put("x", xPadding);
        map.put("y", yPadding);
        map.put("width", width);
        map.put("height", height);

        return map;
    }
}