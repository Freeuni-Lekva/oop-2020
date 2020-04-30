import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Writer implements Runnable {
    private final List<Character> buffer;
    private Lock lock;
    private Condition newCharCond;

    public Writer(List<Character> buffer,
                  Lock lock,
                  Condition newCharCond) {
        this.buffer = buffer;
        this.lock = lock;
        this.newCharCond = newCharCond;
    }

    @Override
    public void run() {
        for (char i = 'a'; i <= 'c'; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            buffer.add(i);
            newCharCond.signal();
            System.out.println("sent signal");
            lock.unlock();
        }
    }
}
