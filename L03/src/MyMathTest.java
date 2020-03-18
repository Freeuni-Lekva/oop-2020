import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyMathTest {

    @Test
    void smallNumbers() {
        assertEquals(6, MyMath.multiply(2, 3));
    }

    @Test
    void zero() {
        assertEquals(0, MyMath.multiply(0, 5));
        assertEquals(0, MyMath.multiply(5, 0));
    }

    @Test
    void one() {
        assertEquals(10, MyMath.multiply(1, 10));
    }

    @Test
    void minusOne() {
        for (int i = 1; i < 10; i++) {
            assertEquals(-i, MyMath.multiply(-1, i));
        }
    }

    @Test
    void main() {
        one();
        minusOne();
        zero();
        smallNumbers();
    }

}