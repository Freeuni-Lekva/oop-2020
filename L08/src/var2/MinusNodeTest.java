package var2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinusNodeTest {

    @Test
    void test() {
        Node n = new MinusNode(new IntValue(5), new IntValue(3));
        assertEquals(2, n.evaluate());
        assertEquals("((5)-(3))", n.toString());
    }
}