package tech.letscode.mosaic;

import com.google.common.math.IntMath;
import java.awt.image.BufferedImage;
import java.math.RoundingMode;
import java.util.function.Consumer;
import javax.annotation.Nonnull;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class ImageDivider
{
    /**
     * Divides the specified image on the sectors. Each sector has the width is equal to {@code sizeStepX} or less and
     * the height is equal to {@code sizeStepY} or less.
     *
     * @param sizeStepX the width of the sector
     * @param sizeStepY the height of the sector
     * @param image  the image to divide on the sectors
     * @param consumer  a function that handles each computed sector
     * @throws NullPointerException if the specified consumer or image is {@code null}
     */
    public void divide(int sizeStepX, int sizeStepY, @Nonnull BufferedImage image, @Nonnull Consumer<Sector> consumer)
    {
        //Calculate the amount of sectors
        int maxSectorY = IntMath.divide(image.getHeight(), sizeStepY, RoundingMode.CEILING);
        int maxSectorX = IntMath.divide(image.getWidth(), sizeStepX, RoundingMode.CEILING);

        int lastCoordinateX, lastCoordinateY = 0;
        int currentStepY, currentStepX;

        for (int currentSectorY = 0; currentSectorY < maxSectorY; currentSectorY++)
        {
            currentStepY = calculateNextStepSize(image.getHeight(), lastCoordinateY, sizeStepY);
            lastCoordinateX = 0; //the algorithms walks from up to down and right to left.
            for (int currentSectorX = 0; currentSectorX < maxSectorX; currentSectorX++)
            {
                currentStepX = calculateNextStepSize(image.getWidth(), lastCoordinateX, sizeStepX);
                BufferedImage sub = image.getSubimage(lastCoordinateX, lastCoordinateY, currentStepX, currentStepY);
                consumer.accept(new Sector(sub, lastCoordinateX, lastCoordinateY));
                lastCoordinateX += currentStepX;
            }
            lastCoordinateY += currentStepY;
        }
    }

    private int calculateNextStepSize(int max, int current, int defaultStep)
    {
        return max - current >= defaultStep ? defaultStep : max - current;
    }

}


