public class AnonnRunnable {

    public static void main(String[] args) throws InterruptedException {
        Thread t  = new Thread(new Runnable() {
            private static final int x = 5;

            @Override
            public void run() {
                System.out.println("foo");
            }
        });
        t.start();
        t.join();
        System.out.println("done");
    }
}
