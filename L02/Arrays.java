import java.util.*;

public class Arrays {
    public static void main(String[] args) {
	int[] a = {1, 2, 3};
	int[] b = {1, 2, 3};
	
	System.out.format("a == b  =>  %b\n", a == b);
	System.out.format("a.equals(b)  =>  %b\n", a.equals(b));
	System.out.format("Arrays.equals(a, b)  =>  %b\n", java.util.Arrays.equals(a, b));
	// System.out.format("Arrays.deepEquals(a, b)  =>  %b\n", java.util.Arrays.deepEquals(a, b));	
	
	System.out.println("\n\n---------\n");

	int[][] x = {{1, 2},
		     {3},
		     {4, 5, 6}};
	int[][] y = {{1, 2},
		     {3},
		     {4, 5, 6}};
	System.out.format("x == y  =>  %b\n", x == y);
	System.out.format("x.equals(y)  =>  %b\n", x.equals(y));
	System.out.format("Arrays.equals(x, y)  =>  %b\n", java.util.Arrays.equals(x, y));
	System.out.format("Arrays.deepEquals(x, y)  =>  %b\n", java.util.Arrays.deepEquals(x, y));
    }
}
