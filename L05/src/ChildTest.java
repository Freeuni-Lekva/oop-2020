import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChildTest {
    @Test
    void eatCarrot() {
        Child c = new Child();
        c.eat(new Food("carrot"));
    }

    @Test
    void eatLollipopAsFood() {
        Child c = new Child();
        Food f = new Candy("lollipop", 100);
        c.eat(f);
    }

    @Test
    void eatLollipopAsCandy() {
        Child c = new Child();
        Candy f = new Candy("lollipop", 100);
        c.eat(f);
    }
}