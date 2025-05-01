package mutithreading.basics;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallable implements Callable<Integer> {

    private int ticketBooked;

    MyCallable(int ticketBooked) {
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

class Test {
    static int NO_OF_THREADS = 10;
    public static void main(String[] args) {
        int Total_ticket_Booked = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(NO_OF_THREADS);
     //   CountDownLatch latch = new CountDownLatch(NO_OF_THREADS);
        System.out.println("Initiating Ticket Booking for date "+ LocalDate.now());

        List<Future<Integer>> ticketBookedList = new ArrayList<>();
        for (int i = 0; i < NO_OF_THREADS; i++) {
            System.out.println(Thread.currentThread().getName() + " Starting Thread : "+i);
            Future<Integer> ticketBooked = executorService.submit(new MyCallable(new Random().nextInt(1, 10)));
            System.out.println(Thread.currentThread().getName() + "Started Thread : "+i);
            ticketBookedList.add(ticketBooked);
            try {
            //    latch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " Ending Thread : "+i);
        }
        /*try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        for (Future<Integer> ticketBooked : ticketBookedList) {
            try {
                Total_ticket_Booked += ticketBooked.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total Ticket Booked: " + Total_ticket_Booked);
        System.out.println("Stopping Ticket Booking for date "+ LocalDate.now());

    }
}
