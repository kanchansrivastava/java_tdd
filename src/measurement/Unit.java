package measurement;

public enum Unit {

    CENTIMETER(100000, UnitType.DISTANCE, 0), METER(1000, UnitType.DISTANCE, 0), KILOMETER(1, UnitType.DISTANCE, 0),
    KILOGRAM(1, UnitType.WEIGHT, 0), GRAM(1000, UnitType.WEIGHT, 0),
    CELSIUS(5, UnitType.TEMPERATURE, 0), FAHRENHEIT ( 9, UnitType.TEMPERATURE, 32);

    private int factor;
    private UnitType type;
    private int intercept;

    Unit(int factor, UnitType type, int intercept) {
        this.factor = factor;
        this.type = type;
        this.intercept = intercept;
    }

    public double convertTo(double value, Unit that) {
        if (this.type != that.type){
            throw new InvalidTypeConversionException();
        }

        return (((value - this.intercept) * that.factor)/this.factor) + that.intercept;
    }
}
