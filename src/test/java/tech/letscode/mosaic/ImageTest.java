package tech.letscode.mosaic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static tech.letscode.mosaic.ImageTestUtils.load;
import static tech.letscode.mosaic.ImageTestUtils.load4x4Image;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class ImageTest
{
    @Test
    public void shouldReturnAvgColorOfSolidColors() throws Exception
    {
        //given
        BufferedImage original = load("4x4.png");
        BufferedImage red = original.getSubimage(0, 0, 2, 2);
        BufferedImage blue = original.getSubimage(2, 0, 2, 2);
        BufferedImage green = original.getSubimage(0, 2, 2, 2);
        BufferedImage yellow = original.getSubimage(2, 2, 2, 2);

        //and
        Image redImage = new Image(red);
        Image blueImage = new Image(blue);
        Image greenImage = new Image(green);
        Image yellowImage = new Image(yellow);

        //when
        Color avgRed = redImage.avgColor();
        Color avgBlue = blueImage.avgColor();
        Color avgGreen = greenImage.avgColor();
        Color avgYelow = yellowImage.avgColor();

        //then
        assertEquals(new Color(255, 0, 0), avgRed);
        assertEquals(new Color(0, 72, 255), avgBlue);
        assertEquals(new Color(0, 255, 42), avgGreen);
        assertEquals(new Color(255, 240, 0), avgYelow);
    }

    @Test
    public void shouldReturnAvgColorOfTwoColors() throws Exception
    {
        //given
        BufferedImage original = load("4x4.png");
        Image twoColorImage = new Image(original.getSubimage(0, 0, 4, 2)); //red and blue

        //when
        Color avgColor = twoColorImage.avgColor();

        //then
        assertEquals(new Color(128, 36, 128), avgColor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cutSubImageShouldThrowIllegalArgumentExceptionIfSpecifiedAreaTooLarge() throws Exception
    {
        //given
        BufferedImage original = load4x4Image();
        Image image = new Image(original);

        //expect
        image.cutSubImage(0, 0, 100, 100);
    }

}