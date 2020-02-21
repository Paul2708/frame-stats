package de.paul2708.framestats.internal.image.layer;

import java.awt.image.BufferedImage;

/**
 * This layer applies some smooth effects on the background image.
 *
 * @author Paul2708
 */
public class BackgroundLayer implements ImageLayer {

    /**
     * Apply the layer onto the given image.
     * Note: The input image should not be changed.
     *
     * @param image base image
     * @return layered image
     * @see ImageLayer#clone(BufferedImage)
     */
    @Override
    public BufferedImage apply(BufferedImage image) {
        // TODO: Apply effects
        return clone(image);
    }
}