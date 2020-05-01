import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class FixedSizeBuffer {

    public static void main(String[] args) {
        int[] buffer = new int[8];
        Semaphore elementsEmpty = new Semaphore(buffer.length);
        Semaphore elementsWritten = new Semaphore(0);

        new Thread(() -> {
            try {
                new Reader(buffer, elementsEmpty, elementsWritten).read(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                new Writer(buffer, elementsEmpty, elementsWritten).write(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class Reader {
        private final int[] buffer;
        private final Semaphore elementsEmpty;
        private final Semaphore elementsWritten;

        Reader(int[] buffer, Semaphore elementsEmpty, Semaphore elementsWritten) {
            this.buffer = buffer;
            this.elementsEmpty = elementsEmpty;
            this.elementsWritten = elementsWritten;
        }

         void read(int numElems) throws InterruptedException {
            for (int i = 0; i < numElems; ++i) {
                elementsWritten.await();
                buffer[i % buffer.length] = i;
                System.out.printf("Reader: %d\n", buffer[i % buffer.length]);
                elementsEmpty.post();
            }
         }
    }

    static class Writer {
        private final int[] buffer;
        private final Semaphore elementsEmpty;
        private final Semaphore elementsWritten;

        Writer(int[] buffer, Semaphore elementsEmpty, Semaphore elementsWritten) {
            this.buffer = buffer;
            this.elementsEmpty = elementsEmpty;
            this.elementsWritten = elementsWritten;
        }

        void write(int numElems) throws InterruptedException {
            for (int i = 0; i < numElems; ++i) {
                elementsEmpty.await();
                System.out.printf("Writer: %d\n", buffer[i % buffer.length]);
                elementsWritten.post();
            }
        }
    }
}
