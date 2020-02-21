package de.paul2708.framestats.internal.image;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * This interface represents an image layer that is used as pipeline step.
 *
 * @author Paul2708
 */
@FunctionalInterface
public interface ImageLayer {

    /**
     * Apply the layer onto the given image.
     * Note: The input image should not be changed.
     *
     * @see ImageLayer#clone(BufferedImage)
     * @param image base image
     * @return layered image
     */
    BufferedImage apply(BufferedImage image);

    /**
     * Clone a buffered image.
     *
     * @param image image to clone
     * @return copied image
     */
    default BufferedImage clone(BufferedImage image) {
        ColorModel colorModel = image.getColorModel();
        boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);

        return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    }
}