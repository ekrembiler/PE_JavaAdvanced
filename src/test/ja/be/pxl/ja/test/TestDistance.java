package be.pxl.ja.test;

import image.GrayscalePixel;
import city.City;
import image.GrayscalePixel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDistance {

    @Test
    public void testDistanceFunctionReturnValueIsDouble() {
        City city = new City("bob", 500, 222);
        assertEquals(123, city.distance(city));
    }

    @Test
    public void testNameIsNull() {
        City city = new City(null, 500, 222);
        assertNull(city.getName());
    }

    @Test
    public void testDistanceIfNotNegative() throws Exception {
        GrayscalePixel gp = new GrayscalePixel(10);
        GrayscalePixel gp2 = new GrayscalePixel(20);
        if (gp.distance(gp2) < 0) {
            throw new Exception();
        }
    }

    @Test
    public void testDistanceNotNull() throws Exception {
        GrayscalePixel gp = new GrayscalePixel(10);
        GrayscalePixel gp2 = new GrayscalePixel(10);
        if (gp.getGreyscale() == gp2.getGreyscale()) {
            throw new Exception();
        }
    }
}

