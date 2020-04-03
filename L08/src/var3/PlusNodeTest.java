package var3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlusNodeTest {

    @Test
    void test() {
        Node n = new PlusNode(new ValueNode<Integer>(5), new ValueNode<Integer>(3));
        assertEquals(8, n.evaluate());
        assertEquals("((5)+(3))", n.toString());
    }
}