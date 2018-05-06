package tech.letscode.mosaic;

import java.awt.Color;
import javax.annotation.Nonnull;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public interface ImageSuggester
{
    /**
     * Suggests an appropriate image by matching the average color of the images with the specified color.
     *
     * @param color color for matching
     * @return the matched image
     */
    @Nonnull
    Image suggestByAvgColor(@Nonnull Color color);
}