import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Reader implements Runnable {

    private final List<Character> buffer;
    private Lock lock;
    private Condition newCharCond;

    public Reader(List<Character> buffer,
                  Lock lock,
                  Condition newCharCond) {
        this.buffer = buffer;
        this.lock = lock;
        this.newCharCond = newCharCond;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; ++i) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            //while (buffer.size() <= i) {
                try {
                    System.out.println("awaiting");
                    newCharCond.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("awoke");
            //}
            System.out.println(buffer.get(i));
            lock.unlock();
        }
    }
}
