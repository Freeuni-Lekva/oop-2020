public class B extends A {
    public B(int x) {
        super(x);
        System.out.println("B: specialized constructor");
    }


    public B() {
        this(10);
        System.out.println("B: default constructor");
    }

    public void bar() {
        System.out.println("B: bar");
        foo();
    }

    public void buzz() {
        System.out.println("B: buzz");
        super.foo();
    }
}
