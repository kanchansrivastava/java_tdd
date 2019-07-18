import org.junit.Test;

import static org.junit.Assert.*;

public class MeasurementTest {

    @Test
    public void equals_shouldReturnTrue_whenGiven100CentimetersEqualTo1meter() {
        Measurement hundredCentimeter = new Measurement(100, Unit.CENTIMETER);
        Measurement oneMeter = new Measurement(1, Unit.METER);

        assertEquals(hundredCentimeter, oneMeter);
    }

    @Test
    public void equals_shouldReturnTrue_whenGiven1meterEqualTo100Centimeters() {
        Measurement hundredCentimeter = new Measurement(100, Unit.CENTIMETER);
        Measurement oneMeter = new Measurement(1, Unit.METER);

        boolean result = oneMeter.equals(hundredCentimeter);

        assertTrue(result);
    }

    @Test
    public void equals_shouldReturnFalse_whenGiven50CentimetersEqualTo1meter() {
        Measurement fiftyCentimeter = new Measurement(50, Unit.METER);
        Measurement oneMeter = new Measurement(1, Unit.METER);

        boolean result = fiftyCentimeter.equals(oneMeter);

        assertFalse(result);
    }

    @Test
    public void equals_shouldReturnFalse_whenGiven1MeterEqualTo10Centimeters() {
        Measurement tenCentimeter = new Measurement(10, Unit.CENTIMETER);
        Measurement oneMeter = new Measurement(1, Unit.METER);

        boolean result = oneMeter.equals(tenCentimeter);

        assertFalse(result);
    }

    @Test
    public void equals_shouldReturnTrue_whenGiven1000MeterEqualTo1Kilometer() {
        Measurement thousandMeter = new Measurement(1000, Unit.METER);
        Measurement oneKiloMeter = new Measurement(1, Unit.KILOMETER);

        boolean result = thousandMeter.equals(oneKiloMeter);

        assertTrue(result);
    }

    @Test
    public void equals_shouldReturnTrue_whenGiven100MeterEqualTo100Meter() {
        Measurement hundredMeter = new Measurement(100, Unit.METER);
        Measurement oneMeter = new Measurement(1, Unit.METER);

        boolean result = hundredMeter.equals(oneMeter);

        assertFalse(result);
    }

    @Test
    public void equals_shouldReturnTrue_whenGiven1000GramsEqualTo1Kilogram() {
        Measurement thousandGrams = new Measurement(1000, Unit.GRAM);
        Measurement oneKilogram = new Measurement(1, Unit.KILOGRAM);

        assertEquals(thousandGrams, oneKilogram);
    }

    @Test(expected = InvalidTypeConversionException.class)
    public void equals_shouldReturnException_whenGiven1GramsEqualTo1Centimeter() {
        Measurement oneGram = new Measurement(1, Unit.GRAM);
        Measurement oneCentimeter = new Measurement(1, Unit.CENTIMETER);

        assertEquals(oneGram, oneCentimeter);
    }

    @Test
    public void add_shouldReturn200_when100CentimetersAddedTo1meter() {
        Measurement hundredCentimeters = new Measurement(100, Unit.CENTIMETER);
        Measurement oneMeter = new Measurement(1, Unit.METER);
        Measurement expectedMeasurement = new Measurement(200, Unit.CENTIMETER);

        Measurement twoHundredCentimeters = hundredCentimeters.add(oneMeter);

        assertEquals(expectedMeasurement, twoHundredCentimeters);
    }

    @Test
    public void add_shouldReturn2_when1meterAddedTo100Centimeters() {
        Measurement oneMeter = new Measurement(1, Unit.METER);
        Measurement hundredCentimeters = new Measurement(100, Unit.CENTIMETER);
        Measurement expectedMeasurement = new Measurement(2, Unit.METER);

        Measurement twoMeters = oneMeter.add(hundredCentimeters);

        assertEquals(expectedMeasurement, twoMeters);
    }
}