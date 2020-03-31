public class FooBar {
    public interface Foo {
        public static int xyz() {
            return 1;
        }

        public void foo();
    }

    public void bar() {
        Foo f = new Foo() {
            public void foo() {
                System.out.println("foo from bar");
            }
        };
    }

}
