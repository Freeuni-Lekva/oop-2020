package var3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexTest {

    @Test
    void test() {
        Node n = new MultiplyNode(
                new DivideNode(new ValueNode<Integer>(2), new ValueNode<Double>(4.0)),
                new PlusNode(new ValueNode<Integer>(0), new ValueNode<Double>(5.0)));
        assertEquals(0.5 * 5, n.evaluate());
        assertEquals("(((2)/(4.0))*((0)+(5.0)))", n.toString());
    }
}
