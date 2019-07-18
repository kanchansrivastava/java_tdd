public enum Unit {
    CENTIMETER(100000), METER(1000), KILOMETER(1);

    private int factor;

    Unit(int factor) {

        this.factor = factor;
    }

    public double convertTo(double value, Unit that) {
        return (value * that.factor)/this.factor;
    };
}