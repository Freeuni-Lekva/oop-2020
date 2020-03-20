package fizzbuzz;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {

    FizzBuzz fb;

    @Before
    public void init() {
        fb = new FizzBuzz();
    }

    @Test
    public void multiplesOfThree() {
        for (int i = 3; i < 100; i += 3) {
            if (i % 5 != 0) assertEquals("Fizz", fb.evaluate(i));
        }
    }

    @Test
    public void multiplesOfFive() {
        for (int i = 5; i < 100; i += 5) {
            if (i % 3 != 0) assertEquals("Buzz", fb.evaluate(i));
        }
    }

    @Test
    public void multiplesOfFifteen() {
        for (int i = 15; i < 100; i += 15) {
            assertEquals("FizzBuzz", fb.evaluate(i));

        }
    }

    @Test
    public void multiplesOfNone() {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                assertEquals(String.valueOf(i), fb.evaluate(i));
            }
        }
    }

}