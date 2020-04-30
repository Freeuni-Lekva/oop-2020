

// Demonstrates Thread.currentThread(), and the difference
// betweeen run() and start().
class MiscThread {
    // Prints the name of the thread calling this method.
    public static void whoami() {
        Thread runningMe = Thread.currentThread();
        System.out.println("whoami thread:" + runningMe.getName());
    }

    // Simple worker thread subclass -- run() sleeps
    // and then call whoami().
    public static class MiscWorker extends Thread {
        @Override
        public void run() {
//            try {
//                // Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            MiscThread.whoami();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MiscThread.whoami();
        Thread worker = new MiscWorker();
        worker.run();   // Note: never call run() like this!
        worker.start(); // Correct way to start a thread.
        // worker.join();
        Thread.sleep(12);
        System.out.println("all done");
    }
	/*
	 Output:
	 whoami thread:main
	 whoami thread:main
	 all done
	 whoami thread:Thread-0
	 */
}


