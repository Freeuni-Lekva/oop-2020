package core.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBufferTest {

    public static void main(String[] args) {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 1000; ++i) {
                        buffer.put(i);
                    }
                } catch (InterruptedException ex) {
                }
            }
        });
        
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 1000; ++i) {
                        buffer.get();
                    }
                } catch (InterruptedException ex) {
                }
            }
        });
        
        t1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        t2.start();
    }
}

class BoundedBuffer<T> {

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final Object[] items = new Object[100];
    private int count = 0;

    public void put(T x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            
            items[count] = x;
            ++count;

            System.out.println(Thread.currentThread().getName() + " put -> " + x);
            
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T get() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }

            --count;
            @SuppressWarnings("unchecked")
            T x = (T) items[count];
            items[count] = null;
            
            System.out.println(Thread.currentThread().getName() + " get -> " + x);
            
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
