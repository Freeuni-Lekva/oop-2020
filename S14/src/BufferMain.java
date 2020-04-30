import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferMain {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition newCharCond = lock.newCondition();
        List<Character> buffer = new ArrayList<>();
        Runnable writer = new Writer(buffer, lock, newCharCond);
        Runnable reader = new Reader(buffer, lock, newCharCond);

        Thread t1 = new Thread(writer);
        t1.start();
        Thread t2 = new Thread(reader);
        t2.start();
        // t2.interrupt();
    }
}
