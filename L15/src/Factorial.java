import java.math.BigInteger;

public class Factorial {
    private static BigInteger fact(int n) {
        assert n >= 0;
        String name = Thread.currentThread().getName();
        BigInteger res = BigInteger.valueOf(1);
        for (int i = 1; i <= n; ++i) {
            res = res.multiply(BigInteger.valueOf(i));
            System.out.printf("Thread %s - multiplying on %d\n", name, i);
        }
        return res;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
           System.out.println(fact(99).toString());
        });

        Thread t2 = new Thread(() -> {
            System.out.println(fact(100).toString());
        });

        t1.start();
        t2.start();
    }
}
