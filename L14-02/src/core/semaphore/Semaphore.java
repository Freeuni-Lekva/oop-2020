package core.semaphore;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Semaphore {
    
    private final LinkedList<Condition> conditions = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private int available;
    
    /**
     * Construct semaphore with maximum available number of allowed thread
     * @param limit 
     */
    public Semaphore(int limit){
        this.available = limit;
    }
    
    /**
     * In case the maximum thread limit is not reached, the method lets 
     * the current thread to pass, otherwise makes it wait until some 
     * other thread leaves the guarded region.
     * 
     * @throws InterruptedException 
     */
    public void acquire() throws InterruptedException{
        lock.lock();
        try {
            if (available == 0) {
                Condition unavailable = lock.newCondition();
                conditions.push(unavailable);
                unavailable.await();
            }
            
            --available;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Invoke this method to notify semaphore about the ending of critical 
     * region. Its invocation means that current thread passed the guarded 
     * block and made it available for another thread in queue.
     * 
     * @throws InterruptedException 
     */
    public void release(){
        lock.lock();
        try {
            ++available;
            if (conditions.size() > 0) {
                conditions.pop().signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
