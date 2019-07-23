package shape;

import org.junit.Test;
import shape.Rectangle;

import static org.junit.Assert.assertEquals;

public class RectangleTest {

    @Test
    public void area_shouldReturn8_whenGivenLengthIs4AndBreadthIs2(){
        Rectangle rectangle = new Rectangle(4, 2);
        int result = rectangle.area();
        assertEquals(8, result);
    }

    @Test
    public void perimeter_shouldReturn12_whenGivenLengthIs4AndBreadthIs2(){
        Rectangle rectangle = new Rectangle(4, 2);
        int result = rectangle.perimeter();
        assertEquals(12, result);
    }

    @Test
    public void area_shouldReturn16_whenGivenLengthIs4(){
        Rectangle square = new Rectangle(4);
        int result = square.area();
        assertEquals(16, result);
    }

}

