package var1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void testValueChanged() {
        Model model = new Model();
        View view = new View("view1");
        model.registerView(view);
        model.setValue("foo");
        assertEquals("foo", view.getValue());
        model.setValue("bar");
        assertEquals("bar", view.getValue());
    }
}