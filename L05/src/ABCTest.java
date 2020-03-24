import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ABCTest {
    @Test
    public void specializedConstructorA() {
        A a = new A(5);
    }

    @Test
    public void defaultConstructorA() {
        A a = new A();
    }

    @Test
    public void specializedConstructorB() {
        B b = new B(15);
    }

    @Test
    public void defaultConstructorB() {
        B b = new B();
    }

    @Test
    public void specializedConstructorC() {
        C c = new C(25);
    }

    @Test
    public void defaultConstructorC() {
        C c = new C();
    }

    @Test
    public void fooA() {
        A a = new A();
        a.foo();
    }

    @Test
    public void fooC() {
        C c = new C();
        c.foo();
    }

    @Test
    public void barB() {
        B b = new B();
        b.bar();
    }

    @Test
    public void barC() {
        B c = new C();
        c.bar();
    }

    @Test
    public void buzzB() {
        B b = new B();
        b.buzz();
    }

    @Test
    public void buzzC() {
        B c = new C();
        c.buzz();
    }
}