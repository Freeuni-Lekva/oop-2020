public class Copy {
    private static void debugInfo(Foo x, Foo y) {
	System.out.format("x.a = %d\n", x.a);
	System.out.format("y.a = %d\n", y.a);
	System.out.println("--------\n\n");
    }
    
    public static void main(String[] args) {
	Foo x = new Foo(5);
	Foo y = x;
	debugInfo(x, y);
	
	y.a = 7;
	debugInfo(x, y);
	
	y = new Foo(x);
	debugInfo(x, y);

	y.a = 10;
	debugInfo(x, y);
    }
}

public class Foo {
    public int a;

    public Foo(int a) {
	this.a = a;
    }

    public Foo(Foo o) {
	this.a = o.a;
    }
}
