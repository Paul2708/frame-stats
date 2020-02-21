package de.paul2708.framestats.internal.image.layer;

import java.awt.image.BufferedImage;

/**
 * This layer crops the image if the image is bigger than the given wall.
 *
 * @author Paul2708
 */
public class CroppingLayer implements ImageLayer {

    private static final int BLOCK_SIZE = 128;

    private final int width;
    private final int height;

    /**
     * Create a new copping layer.
     *
     * @param width wall width
     * @param height wall height
     */
    public CroppingLayer(int width, int height) {
        this.width = width * CroppingLayer.BLOCK_SIZE;
        this.height = height * CroppingLayer.BLOCK_SIZE;
    }

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
        if (image.getWidth() <= width && image.getHeight() <= height) {
            return image;
        }

        return image.getSubimage(0, 0, width, height);
    }
}