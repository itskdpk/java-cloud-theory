package mutithreading.basics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallableExample2 implements Callable<Integer> {
    private int ticketBooked;

    MyCallableExample2(int ticketBooked) {
        this.ticketBooked = ticketBooked;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " initiated Ticket Booking of " + ticketBooked + " tickets");
        Thread.sleep(9000);//ticket Booking time
        System.out.println(Thread.currentThread().getName() + " Booked " + ticketBooked + " tickets");
        return ticketBooked;
    }
}

class Main {
    private static final int NO_OF_THREADS = 10;
    private static int totalTickets = 0;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NO_OF_THREADS);
        List<MyCallableExample2> myCallableList =  new ArrayList<>();
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i< NO_OF_THREADS; i++) {
            myCallableList.add(new MyCallableExample2(new Random().nextInt(1, 10)));
        }
        try {
            futureList = executorService.invokeAll(myCallableList);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        futureList.forEach(future -> {
            try {
                totalTickets += future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Total Ticket Booked : "+totalTickets);
    }
}
