package var2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelTest {

    @Test
    void testValueChanged() {
        Model model = new Model();
        ViewOne viewOne = new ViewOne("view1");
        ViewTwo viewTwo = new ViewTwo("view2");
        model.registerViewOne(viewOne);
        model.registerViewTwo(viewTwo);
        model.setValue("foo");
        assertEquals("foo", viewOne.getValue());
        assertEquals("foo", viewTwo.getValue());
        model.setValue("bar");
        assertEquals("bar", viewOne.getValue());
        assertEquals("bar", viewTwo.getValue());

    }
}