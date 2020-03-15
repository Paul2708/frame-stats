package de.paul2708.framestats.configuration;

import de.paul2708.framestats.exception.InvalidConfigurationException;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

/**
 * This class holds configuration data about the page bar.
 *
 * @author Paul2708
 */
@SerializableAs("Page-Bar")
public final class PageBarConfiguration implements ConfigurationSerializable {

    // TODO: Add general verify method

    private final int x;
    private final int y;

    private final int shiftWidth;
    private final int infoWidth;
    private final int spaceWidth;

    private final int height;

    /**
     * Create a new page bar configuration by map.
     * Required by {@link ConfigurationSerializable}.
     *
     * @param serializedMap map with column attributes
     */
    public PageBarConfiguration(Map<String, Object> serializedMap) {
        this.x = (int) serializedMap.get("x");
        this.y = (int) serializedMap.get("y");
        this.shiftWidth = (int) serializedMap.get("shift-width");
        this.infoWidth = (int) serializedMap.get("info-width");
        this.spaceWidth = (int) serializedMap.get("space-width");
        this.height = (int) serializedMap.get("height");
    }

    /**
     * Verify the configuration settings.
     *
     * @throws InvalidConfigurationException if given attributes are not well-configured
     */
    public void verify() throws InvalidConfigurationException {
        if (shiftWidth < 0 || infoWidth < 0) {
            throw new InvalidConfigurationException("Width has to be positive");
        }
        if (height <= 0) {
            throw new InvalidConfigurationException("Height has to be positive");
        }
    }

    /**
     * Get the x coordinate.
     *
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y coordinate.
     *
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Get the width of the page shift buttons.
     *
     * @return shift button width
     */
    public int getShiftWidth() {
        return shiftWidth;
    }

    /**
     * Get the width of the info button.
     *
     * @return info button width
     */
    public int getInfoWidth() {
        return infoWidth;
    }

    /**
     * Get the width of the space between two buttons.
     *
     * @return space width
     */
    public int getSpaceWidth() {
        return spaceWidth;
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

        map.put("x", x);
        map.put("y", y);
        map.put("shift-width", shiftWidth);
        map.put("info-width", infoWidth);
        map.put("space-width", spaceWidth);
        map.put("height", height);

        return map;
    }
}