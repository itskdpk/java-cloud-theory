package mutithreading.basics.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class BasicExample {
    private static final int THREAD_COUNT = 3;
    private static CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Work Started");
        Thread t = null;
        for (int i = 0; i < THREAD_COUNT; i++) {
            t = new Thread(new Worker(latch));
            t.start();
        }
        //t.join();
        latch.await();

        System.out.println("Work Finished");
    }
}

class Worker implements Runnable {

    private final CountDownLatch latch;
    Worker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread "+Thread.currentThread().getName()+" started");
            Thread.sleep(1000);
            System.out.println("Thread "+Thread.currentThread().getName()+" finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally{
            latch.countDown();
        }
    }
}
