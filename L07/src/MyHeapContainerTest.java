import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHeapContainerTest {

    @Test
    void twoElems() {
        MyHeapContainer h = new MyHeapContainer();
        h.add(5);
        h.add(10);
        assertEquals(10, h.get(0));
        assertEquals(5, h.get(1));
        MyContainer c = h;
        assertEquals(10, c.getMax());
    }
}