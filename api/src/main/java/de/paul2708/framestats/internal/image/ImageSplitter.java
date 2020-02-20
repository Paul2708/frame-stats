package de.paul2708.framestats.internal.image;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Class description.
 *
 * @author Paul2708
 */
public final class ImageSplitter {

    public Image[][] splitImage(BufferedImage image) {
        int widthFrames = image.getWidth() / 128;
        int heightFrames = image.getHeight() / 128;

        Image[][] images = new BufferedImage[widthFrames][heightFrames];

        for (int i = 0; i < widthFrames; i++) {
            for (int j = 0; j < heightFrames; j++) {
                images[i][j] = image.getSubimage(i * 128, j * 128, 128, 128);
            }
        }

        return images;
    }
}
