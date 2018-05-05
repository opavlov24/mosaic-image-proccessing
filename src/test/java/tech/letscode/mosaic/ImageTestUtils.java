package tech.letscode.mosaic;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class ImageTestUtils
{
    public static BufferedImage load(String filename) throws IOException
    {
        return read(ImageTestUtils.class.getClassLoader().getResourceAsStream(filename));
    }
}
