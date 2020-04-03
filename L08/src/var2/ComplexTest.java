package var2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexTest {

    @Test
    void test() {
        Node n = new MultiplyNode(
                new DivideNode(new IntValue(2), new DoubleValue(4)),
                new PlusNode(new IntValue(0), new DoubleValue(5)));
        assertEquals(0.5 * 5, n.evaluate());
        assertEquals("(((2)/(4.0))*((0)+(5.0)))", n.toString());
    }
}
