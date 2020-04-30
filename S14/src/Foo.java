public class Foo {

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        System.out.println(c.counter.get());
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(c.counter.get());
    }

    static class Counter implements Runnable {

        public ThreadLocal<Integer> counter;

        public Counter() {
            counter = new ThreadLocal<>();
            // counter.set(5);
        }

        @Override
        public void run() {
            System.out.println(counter.get());
            counter.set(0);
            for (int i = 0; i < 100; i++) {
                System.out.printf(
                        "%s - %d\n",
                        Thread.currentThread().getName(),
                        counter.get());
                counter.set(counter.get() + 1);
            }
        }
    }
}
