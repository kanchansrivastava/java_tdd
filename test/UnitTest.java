import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnitTest {

    @Test
    public void convertTo_shouldReturn100_whenGiven1MeterToCentimeter(){
        double result = Unit.METER.convertTo(1, Unit.CENTIMETER);

        assertEquals(100, result, 0.0);
    }

    @Test
    public void convertTo_shouldReturn1_whenGiven100CentimeterToMeter(){
        double result = Unit.CENTIMETER.convertTo(100, Unit.METER);

        assertEquals(1, result, 0.0);
    }

    @Test
    public void convertTo_shouldReturn1_whenGiven1MeterToMeter(){
        double result = Unit.METER.convertTo(1, Unit.METER);

        assertEquals(1, result, 0.0);
    }

    @Test
    public void convertTo_shouldReturn1_whenGiven1CentimeterToCentimeter(){
        double result = Unit.CENTIMETER.convertTo(1, Unit.CENTIMETER);

        assertEquals(1, result, 0.0);
    }


}