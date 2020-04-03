package var3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinusNodeTest {

    @Test
    void test() {
        Node n = new MinusNode(new ValueNode<Integer>(5), new ValueNode<Integer>(3));
        assertEquals(2, n.evaluate());
        assertEquals("((5)-(3))", n.toString());
    }
}