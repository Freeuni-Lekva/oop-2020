package var3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivideNodeTest {

    @Test
    void test() {
        Node n = new DivideNode(new ValueNode<Integer>(5), new ValueNode<Integer>(3));
        assertEquals(5.0 / 3, n.evaluate());
        assertEquals("((5)/(3))", n.toString());
    }
}