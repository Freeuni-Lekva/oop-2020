public class A {
    private int x;

    public A(int x) {
        System.out.println("A: specialized constructor");
        this.x = x;
    }

    public A() {
        this(5);
        System.out.println("A: default constructor");
    }

    public int getX() {
        return x;
    }

    public void foo() {
        System.out.println("A: foo");
    }
}
