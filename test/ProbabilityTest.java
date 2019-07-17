import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ProbabilityTest {

    @Test
    public void isEqualTo_shouldReturnTrue_whenGivenTwoProbabilitiesAreEqual() {
        Probability probability1 = new Probability(0.5);
        Probability probability2 = new Probability(0.5);

        assertEquals(probability1, probability2);
    }

    @Test
    public void isGreaterThan_shouldReturnTrue_whenGivenProbability1IsGreaterThanProbability2() {
        Probability probability1 = new Probability(0.8);
        Probability probability2 = new Probability(0.2);

        boolean result = probability1.isGreaterThan(probability2);
        assertTrue(result);
    }

    @Test
    public void isLesserThan_shouldReturnTrue_whenGivenProbability1IsLesserThanProbability2() {
        Probability probability1 = new Probability(0.2);
        Probability probability2 = new Probability(0.8);

        boolean result = probability1.isLesserThan(probability2);
        assertTrue(result);
    }

    @Test
    public void compare_shouldReturn1_whenGivenProbability1IsGreaterThanProbability2() {

    }

    @Test
    public void and_shouldReturnPointTwoFive_whenGivenProbability1IsZeroPointFiveAndProbability2IsZeroPointFive() {
        Probability probability1 = new Probability(0.5);
        Probability probability2 = new Probability(0.5);
        Probability expectedProbability = new Probability(0.25);

        Probability combineProbability = probability1.and(probability2);

        assertEquals(expectedProbability, combineProbability);
    }

    @Test
    public void complement_shouldReturnPointSix_whenGivenProbabilityIsZeroPointFour() {
        Probability probability = new Probability(0.4);
        Probability expectedComplementProbability = new Probability(0.6);

        Probability complementProbability = probability.complement();

        assertEquals(expectedComplementProbability, complementProbability);

    }

    @Test
    public void or_shouldReturnPointSevenSix_whenGivenProbability1IsZeroPointFourAndProbability2IsZeroPointSix(){
        Probability probability1 = new Probability(0.4);
        Probability probability2 = new Probability(0.6);
        Probability expectedProbability = new Probability(0.76);

        Probability combineProbability = probability1.or(probability2);

        assertEquals(expectedProbability, combineProbability);

    }
}