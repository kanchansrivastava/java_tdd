package probability;

public class Probability {
    private double value;
    private final int MAX_PROBABILITY = 1;

    public Probability(double value) {
        this.value = value;
    }

    public boolean isGreaterThan(Probability probability2) {
        return this.value > probability2.value;
    }

    public boolean isLesserThan(Probability probability2) {
        return this.value < probability2.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Probability that = (Probability) o;
        return Double.compare(that.value, value) == 0;
    }



     public Probability and(Probability probability2) {
        return new Probability(this.value * probability2.value);
     }

     public Probability complement() {
        return new Probability(MAX_PROBABILITY - this.value);
     }

     public Probability or(Probability probability2) {
         Probability combinedProbability = this.and(probability2);
         return new Probability(this.value + probability2.value - combinedProbability.value);
     }

 }
