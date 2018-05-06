package tech.letscode.mosaic;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.Nonnull;

import static java.util.Arrays.stream;

/**
 * Implements {@link ImageSuggester} interface for suggesting images. The implementation stores all available images
 * for suggesting in memory.
 *
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class InMemoryImageSuggester implements ImageSuggester
{
    private static final int MIN_COLOR_INT = 0;

    private static final int MAX_COLOR_INT = 255;

    private final Map<Color, Image> images;

    public InMemoryImageSuggester(Image... images)
    {
        this.images = new HashMap<>();
        stream(images).forEach(image -> this.images.put(image.avgColor(), image));
    }

    /**
     * Suggests an appropriate image using the average color algorithm. If the specified color can not be matched
     * with any existed images then it tries to find the image with the similar color using.
     *
     * @param color color for matching
     * @return the matched image
     */
    @Override
    @Nonnull
    public Image suggestByAvgColor(@Nonnull Color color)
    {
        Image image = this.images.get(color);
        return image != null ? image : tryFindNearestImage(color);
    }

    private Image tryFindNearestImage(Color color)
    {
        Image image = iterateThought(color.getRed(), color.getRed() + 1, red ->
                iterateThought(color.getGreen(), color.getGreen() + 1, green ->
                        iterateThought(color.getBlue(), color.getBlue() + 1, blue ->
                                retrieveImage(red, green, blue)
                        )
                )
        );
        if (image == null)
        {
            throw new IllegalStateException("Image has not been found. Please, check the algorithm");
        }
        return image;
    }

    private Image retrieveImage(int r, int g, int b)
    {
        return this.images.get(new Color(r, g, b));
    }

    private Image iterateThought(int leftSide, int rightSide, Function<Integer, Image> function)
    {
        while (leftSide >= MIN_COLOR_INT || rightSide <= MAX_COLOR_INT)
        {
            if (leftSide >= MIN_COLOR_INT)
            {
                Image result = function.apply(leftSide);
                if (result != null)
                {
                    return result;
                }
                leftSide--;
            }
            if (rightSide <= MAX_COLOR_INT)
            {
                Image result = function.apply(rightSide);
                if (result != null)
                {
                    return result;
                }
                rightSide++;
            }
        }
        return null;
    }

}
