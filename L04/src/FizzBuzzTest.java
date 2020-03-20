import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzBeforeAllTest {

    private FizzBuzz fb;

    @BeforeEach
    public void setUp() {
        fb = new FizzBuzz();
    }

    @Test
    public void evaluate() {
        fb.evaluate(0);
    }

    @Test
    public void one() {
        assertEquals("1", fb.evaluate(1));
    }

    @Test
    public void seven() {
        assertEquals("7", fb.evaluate(7));
    }

    @Test
    public void multipleOfThree() {
        assertEquals("fizz", fb.evaluate(3));
        assertEquals("fizz", fb.evaluate(-3));
        assertEquals("fizz", fb.evaluate(21));
    }

    @Test
    public void multipleOfFive() {
        assertEquals("buzz", fb.evaluate(5));
        assertEquals("buzz", fb.evaluate(-5));
        assertEquals("buzz", fb.evaluate(25));
    }

    @Test
    public void multipleOfFifteen() {
        assertEquals("fizzbuzz", fb.evaluate(0));
        assertEquals("fizzbuzz", fb.evaluate(15));
        assertEquals("fizzbuzz", fb.evaluate(-15));
        assertEquals("fizzbuzz", fb.evaluate(45));
    }

    @Test
    public void containsThree() {
        assertEquals("fizz", fb.evaluate(13));
    }

    @Test
    public void containsFive() {
        assertEquals("buzz", fb.evaluate(52));
    }
}