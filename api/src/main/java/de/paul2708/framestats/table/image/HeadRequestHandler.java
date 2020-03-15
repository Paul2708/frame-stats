package de.paul2708.framestats.table.image;

import java.awt.Image;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * This interface handles player head textures requests.
 *
 * @author Paul2708
 */
@FunctionalInterface
public interface HeadRequestHandler {

    /**
     * Consume an optional player skull texture.
     * If the optional is empty, a default texture will be displayed.
     * Note: The images will not be cached.
     *
     * @param playerName requested player name
     * @param headConsumer consumer that consumes the optional texture
     */
    void request(String playerName, Consumer<Optional<Image>> headConsumer);
}