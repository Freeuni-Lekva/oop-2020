public class Pair {
    private int a;
    private int b;

    public void inc() {
        synchronized (this) {
            a++;
        }
        synchronized (this) {
            b++;
        }
    }

    public int sum() {
        return a + b;
    }
}

class PairWorker extends Thread {
    private Pair pair;

    PairWorker(Pair pair) {
        this.pair = pair;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            pair.inc();
        }
    }
}

class Main {
    public static void main(String[] args) throws InterruptedException {
        Pair p = new Pair();

        PairWorker w1 = new PairWorker(p);
        PairWorker w2 = new PairWorker(p);
        PairWorker w3 = new PairWorker(p);

        w1.start();
        w2.start();
        w3.start();

        w1.join();
        w2.join();
        w3.join();

        System.out.println(p.sum());
    }
}