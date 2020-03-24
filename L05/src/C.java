public class C extends B {
    public C(int x) {
        super(x);
        System.out.println("C: specialized constructor");
    }

    public C() {
        this(20);
        System.out.println("C: default constructor");
    }

    @Override
    public void foo() {
        System.out.println("C: foo");
        super.foo();
    }
}
