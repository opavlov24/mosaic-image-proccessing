package tech.letscode.mosaic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.annotation.Nonnull;

/**
 * Represents the separated sector of an image.
 *
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class Sector
{
    private final BufferedImage image;

    private final int coordinateX;

    private final int coordinateY;

    /**
     * Constructs a new sector.
     *
     * @param image       the binary representation of a sector
     * @param coordinateX the coordinate x of sector's location
     * @param coordinateY the coordinate y of sector's location
     * @throws NullPointerException if the passed image is null
     */
    public Sector(@Nonnull BufferedImage image, int coordinateX, int coordinateY)
    {
        this.image = image;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    /**
     * Draws the sector in the specified graphics context.
     *
     * @param context the graphics context to draw the sector
     * @throws NullPointerException if the specified context is null
     */
    public void draw(@Nonnull Graphics context)
    {
        context.drawImage(this.image, this.coordinateX, this.coordinateY, null);
    }

    int square()
    {
        return this.image.getWidth() * this.image.getHeight();
    }

    int getCoordinateX()
    {
        return this.coordinateX;
    }

    int getCoordinateY()
    {
        return this.coordinateY;
    }
}
