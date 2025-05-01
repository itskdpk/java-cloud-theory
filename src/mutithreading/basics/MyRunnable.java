package mutithreading.basics;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i<10; i++) {
            Thread.currentThread().getName();
            System.out.println("Thread Name: " + Thread.currentThread().getName() + " - Count: " + i);
        }
    }
}


class MyRunnable2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i<10; i++) {
            Thread.currentThread().getName();
            System.out.println("Thread Name: " + Thread.currentThread().getName() + " - Count: " + i);
        }
    }
}

