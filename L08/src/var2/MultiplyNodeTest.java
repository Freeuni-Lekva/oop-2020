package var2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyNodeTest {

    @Test
    void test() {
        Node n = new MultiplyNode(new IntValue(5), new IntValue(3));
        assertEquals(15, n.evaluate());
        assertEquals("((5)*(3))", n.toString());
    }
}