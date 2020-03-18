public class Main {
    private static void abc(StringBuilder x) {
	x = new StringBuilder(",mxcbvmabfjk");
	x.append("asdjhl");	
    }
    
    public static void main(String args[]) {
	StringBuilder a = new StringBuilder("qwe");
	System.out.println(a);
	abc(a);
	System.out.println(a);
    }
}


