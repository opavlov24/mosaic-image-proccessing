package tech.letscode.mosaic;

import java.awt.Color;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static tech.letscode.mosaic.ImageTestUtils.*;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class InMemoryImageSuggesterTest
{
    @Test
    public void shouldReturnNearestColor() throws Exception
    {
        //given
        Image red = new Image(load2x2Red());
        Image lightRed = new Image(load2x2LightRed());
        Image green = new Image(load2x2Green());

        //and
        InMemoryImageSuggester imageSuggester = new InMemoryImageSuggester(red, lightRed, green);

        //when
        Image firstNearestImage = imageSuggester.suggestByAvgColor(new Color(255, 2, 3));
        Image secondNearestImage = imageSuggester.suggestByAvgColor(new Color(255, 130, 200));
        Image thirdNearestImage = imageSuggester.suggestByAvgColor(new Color(20, 23, 10));

        //then
        assertEquals(red.avgColor(), firstNearestImage.avgColor());
        assertEquals(lightRed.avgColor(), secondNearestImage.avgColor());
        assertEquals(green.avgColor(), thirdNearestImage.avgColor());
    }

    @Test
    public void shouldReturnBlackImage() throws Exception
    {
        //given
        Image black = new Image(load2x2Black());
        InMemoryImageSuggester imageSuggester = new InMemoryImageSuggester(black);

        //when
        Image found = imageSuggester.suggestByAvgColor(Color.WHITE);

        //then
        assertEquals(black.avgColor(), found.avgColor());
    }

    @Test
    public void shouldReturnWhiteImage() throws Exception
    {
        //given
        Image white = new Image(load2x2White());
        InMemoryImageSuggester imageSuggester = new InMemoryImageSuggester(white);

        //when
        Image found = imageSuggester.suggestByAvgColor(Color.BLACK);

        //then
        assertEquals(white.avgColor(), found.avgColor());
    }
}