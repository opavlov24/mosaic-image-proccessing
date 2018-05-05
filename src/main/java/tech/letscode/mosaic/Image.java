package tech.letscode.mosaic;

import com.google.common.math.IntMath;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.math.RoundingMode;
import javax.annotation.Nonnull;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class Image
{
    private final BufferedImage image;

    public Image(@Nonnull BufferedImage image)
    {
        this.image = image;
    }

    public Color avgColor()
    {
        int avgRed = 0, avgGreen = 0, avgBlue = 0;
        for (int y = 0; y < this.image.getHeight(); y++)
        {
            for (int x = 0; x < this.image.getWidth(); x++)
            {
                Color color = new Color(this.image.getRGB(x, y));
                avgRed += color.getRed();
                avgGreen += color.getGreen();
                avgBlue += color.getBlue();
            }
        }
        return new Color(
                IntMath.divide(avgRed, amountOfPixels(), RoundingMode.CEILING),
                IntMath.divide(avgGreen, amountOfPixels(), RoundingMode.CEILING),
                IntMath.divide(avgBlue, amountOfPixels(), RoundingMode.CEILING)
        );
    }

    public int square()
    {
        return this.image.getWidth() * this.image.getHeight();
    }

    private int amountOfPixels()
    {
        return square();
    }
}