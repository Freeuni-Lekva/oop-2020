import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BooleanArrayFilterTest {
    private Filter f;

    @BeforeEach
    public void setUp() {
        f = new BooleanArrayFilter();
    }

    @Test
    public void addRemove() {
        assertFalse(f.isPresent(0));
        f.add(0);
        assertTrue(f.isPresent(0));
        f.remove(0);
        assertFalse(f.isPresent(0));
    }

    @Test
    public void emptyPresence() {
        assertEquals(0, f.size());
        for (int i = 0; i < 1000; i++) {
            assertFalse(f.isPresent(i));
        }
    }

    @Test
    public void addEvenIndexes() {
        for (int i = 0; i < 100; i += 2) {
            assertFalse(f.isPresent(i));
            f.add(i);
            assertTrue(f.isPresent(i));
        }
        for (int i = 1; i < 100; i += 2) {
            assertFalse(f.isPresent(i));
        }
        assertEquals(50, f.size());
    }
}