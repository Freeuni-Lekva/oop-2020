package var2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntValueTest {

    @Test
    void test() {
        Node n = new IntValue(5);
        assertEquals(5, n.evaluate());
        assertEquals("(5)", n.toString());
    }
}