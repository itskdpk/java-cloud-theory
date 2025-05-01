package mutithreading.basics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorExample {

    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(10);
        System.out.println("Start Loading Data");
        for(int i = 0; i < 10; i++) {
            //executors.submit(new Worker((i * 10) + 1, (i + 1) * 10));
            int finalI = i;
            executors.execute(() -> method((finalI * 10) + 1, (finalI + 1) * 10));
        }
        try {
            executors.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        executors.shutdown();
        System.out.println("Completed Loading Data");

    }

    public static void method(Integer start, Integer end) {
        try {
            System.out.println("Starting Load Data from page " + start + " to page " + end);
            Thread.sleep(1000);
            System.out.println("Completed Load Data from page " + start + " to page " + end);
        }
        catch(Exception e) {
            System.out.println("Exception found during processing page " + start + " to page " + end);
        }
    }
}

class Worker implements Runnable{
    private int start;
    private int end;

    public Worker(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        try {
            System.out.println("Starting Load Data from page " + start + " to page " + end);
            Thread.sleep(1000);
            System.out.println("Completed Load Data from page " + start + " to page " + end);
        }
        catch(Exception e) {
            System.out.println("Exception found during processing page " + start + " to page " + end);
        }
    }
}
