public class Measurement {
    private final double value;
    private final Unit unit;

    public Measurement(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    private boolean isEqual(Measurement anotherMeasurement) {
        if (anotherMeasurement.unit != this.unit) {
            return anotherMeasurement.unit.convertTo(anotherMeasurement.value, this.unit) == this.value;
        }
        return anotherMeasurement.value == this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement measurement = (Measurement) o;
        return isEqual(measurement);
    }

}