package var3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueNodeTest {

    @Test
    void doubleTest() {
        Node n = new ValueNode<Double>(5.0);
        assertEquals(5, n.evaluate());
        assertEquals("(5.0)", n.toString());
    }

    @Test
    void floatTest() {
        Node n = new ValueNode<Float>(3.0f);
        assertEquals(3.0, n.evaluate());
        assertEquals("(3.0)", n.toString());
    }

    @Test
    void intTest() {
        Node n = new ValueNode<Integer>(5);
        assertEquals(5, n.evaluate());
        assertEquals("(5)", n.toString());
    }

    @Test
    void shortTest() {
        Node n = new ValueNode<Short>((short)2);
        assertEquals(2, n.evaluate());
        assertEquals("(2)", n.toString());
    }
}