import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PairTest {

    @Test
    void integers() {
        Pair<Integer> p = new Pair<>(5, 7);
        assertEquals(5, p.getFirst());
        assertEquals(7, p.getSecond());
    }

    @Test
    void strings() {
        Pair<String> p = new Pair<>("foo", "bar");
        assertEquals("foo", p.getFirst());
        assertEquals("bar", p.getSecond());
    }

    @Test
    void asd() {
        Pair<Integer>[] arr = new Pair[5];
        arr[0] = new Pair<Integer>(3, 4);
        assertEquals(3, arr[0].getFirst());
        arr[1] = new Pair("foo", "bar");
        assertEquals("foo", arr[1].getFirst());
        assertEquals(6, arr[1].getFirst());
    }
}