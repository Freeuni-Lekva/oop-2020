package var2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivideNodeTest {

    @Test
    void test() {
        Node n = new DivideNode(new IntValue(5), new IntValue(3));
        assertEquals(5.0 / 3, n.evaluate());
        assertEquals("((5)/(3))", n.toString());
    }
}