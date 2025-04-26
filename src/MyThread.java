public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i<10; i++) {
            Thread.currentThread().getName();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread Name: " + Thread.currentThread().getName() + " - Count: " + i);
        }
    }
}

class MyThread2 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i<10; i++) {
            Thread.currentThread().getName();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread Name: " + Thread.currentThread().getName() + " - Count: " + i);
        }
    }
}
