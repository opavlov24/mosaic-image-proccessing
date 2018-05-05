package tech.letscode.mosaic;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static tech.letscode.mosaic.ImageTestUtils.load;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class ImageDividerTests
{
    @Test
    public void shouldReturn4px4pxSectors() throws Exception
    {
        //given
        int stepY = 2, stepX = 2;
        ImageDivider divider = new ImageDivider();
        Image original = new Image(load("4x4.png"));

        //when
        List<Sector> sectors = new ArrayList<>();
        divider.divide(stepX, stepY, original, sectors::add);

        //then
        assertEquals(4, sectors.size());
        assertEquals(4, sectors.stream().mapToInt(Sector::getCoordinateX).sum());
        assertEquals(4, sectors.stream().mapToInt(Sector::getCoordinateY).sum());
        assertTrue(sectors.stream().allMatch(sector -> sector.square() == 4));
    }

    @Test
    public void shouldAdoptStepsTowardsTheImage() throws Exception
    {
        //given
        int stepY = 6, stepX = 6;
        ImageDivider divider = new ImageDivider();
        Image original = new Image(load("4x4.png"));

        //when
        List<Sector> sectors = new ArrayList<>();
        divider.divide(stepX, stepY, original, sectors::add);

        //then
        Sector sector = sectors.iterator().next();
        assertEquals(0, sector.getCoordinateX());
        assertEquals(0, sector.getCoordinateY());
        assertEquals(16, sector.square());
    }

    @Test
    public void shouldReduceStepAutomaticallyForLastSectors() throws Exception
    {
        //given
        int stepX = 3, stepY = 3;
        ImageDivider divider = new ImageDivider();
        Image original = new Image(load("4x4.png"));

        //when
        List<Sector> sectors = new ArrayList<>();
        divider.divide(stepX, stepY, original, sectors::add);

        //then
        assertEquals(4, sectors.size());
        assertEquals(9, sectors.get(0).square());
        assertEquals(3, sectors.get(1).square());
        assertEquals(3, sectors.get(2).square());
        assertEquals(1, sectors.get(3).square());

    }
}