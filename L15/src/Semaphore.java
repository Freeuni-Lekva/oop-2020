import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Semaphore {
    private int value;
    private final Lock lock;
    private final Condition cond;

    public Semaphore(int value) {
        this.value = value;
        lock = new ReentrantLock();
        cond = lock.newCondition();
    }

    public void post() {
        lock.lock();
        ++value;
        cond.signal();
        lock.unlock();
    }

    public void await() throws InterruptedException {
        lock.lock();
        while (value == 0) {
            cond.await();
        }
        --value;
        lock.unlock();
    }
}
