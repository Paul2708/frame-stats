package de.paul2708.framestats.configuration;

import de.paul2708.framestats.exception.InvalidConfigurationException;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

/**
 * This class holds configuration data about a column like index, name and max width.
 *
 * @author Paul2708
 */
@SerializableAs("Column")
public final class ColumnConfiguration implements Comparable<ColumnConfiguration>, ConfigurationSerializable {

    private final int index;
    private final String name;
    private final int maxWidth;

    /**
     * Create a new column configuration by map.
     * Required by {@link ConfigurationSerializable}.
     *
     * @param serializedMap map with column attributes
     */
    public ColumnConfiguration(Map<String, Object> serializedMap) {
        this.index = (int) serializedMap.get("index");
        this.name = (String) serializedMap.get("name");
        this.maxWidth = (int) serializedMap.get("max-width");
    }

    /**
     * Verify the configuration settings.
     *
     * @throws InvalidConfigurationException if given attributes are not well-configured
     */
    public void verify() throws de.paul2708.framestats.exception.InvalidConfigurationException {
        if (index < 0) {
            throw new InvalidConfigurationException("Column index has to be positive");
        }
        if (maxWidth <= 0) {
            throw new InvalidConfigurationException("Column maximal width has to be great enough");
        }
    }

    /**
     * Get the index of the column.
     * Starts by zero.
     *
     * @return column index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Get the name that will be display as column title.
     *
     * @return column name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the maximal width in pixels.
     * Will be used to draw the row correctly.
     *
     * @return width in pixels
     */
    public int getMaxWidth() {
        return maxWidth;
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

        map.put("index", index);
        map.put("name", name);
        map.put("max-width", maxWidth);

        return map;
    }

    /**
     * Compare columns by column index.
     *
     * @param column column to compare
     * @return the column with smaller index
     */
    @Override
    public int compareTo(ColumnConfiguration column) {
        return Integer.compare(index, column.index);
    }
}