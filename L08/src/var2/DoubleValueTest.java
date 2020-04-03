package var2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleValueTest {

    @Test
    void test() {
        Node n = new DoubleValue(5);
        assertEquals(5, n.evaluate());
        assertEquals("(5.0)", n.toString());
    }
}