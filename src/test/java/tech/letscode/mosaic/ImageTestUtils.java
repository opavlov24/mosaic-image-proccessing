package tech.letscode.mosaic;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class ImageTestUtils
{
    public static BufferedImage load4x4Image() throws IOException
    {
        return load("4x4.png");
    }

    public static BufferedImage load2x2Red() throws IOException
    {
        return load("2x2-red.png");
    }

    public static BufferedImage load2x2LightRed() throws IOException
    {
        return load("2x2-light-red.png");
    }

    public static BufferedImage load2x2Green() throws IOException
    {
        return load("2x2-green.png");
    }

    public static BufferedImage load2x2White() throws IOException
    {
        return load("2x2-white.png");
    }

    public static BufferedImage load2x2Black() throws IOException
    {
        return load("2x2-black.png");
    }

    public static BufferedImage load(String filename) throws IOException
    {
        return read(ImageTestUtils.class.getClassLoader().getResourceAsStream(filename));
    }
}
