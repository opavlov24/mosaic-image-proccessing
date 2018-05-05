package tech.letscode.mosaic;

import java.awt.Color;
import javax.annotation.Nonnull;

/**
 * Represents the separated sector of an image.
 *
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class Sector
{
    private final Image image;

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
    public Sector(@Nonnull Image image, int coordinateX, int coordinateY)
    {
        this.image = image;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Color avgColor()
    {
        return this.image.avgColor();
    }

    int square()
    {
        return this.image.square();
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
