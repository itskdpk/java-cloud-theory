package mutithreading.basics.semaphore;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingLot {

    private final int totalNumberOfSpaces = 10;
    private AtomicInteger availableSpaces = new AtomicInteger(totalNumberOfSpaces);
    Semaphore semaphore = new Semaphore(totalNumberOfSpaces, true);
    void parkCar(int carNo) {
        try {
            semaphore.acquire();
            System.out.println(LocalDateTime.now() +" | Car parked : "+carNo + " | Available spaces : " + semaphore.availablePermits());
            Thread.sleep(new Random().nextInt(1, 10) * 1000);
            System.out.println(LocalDateTime.now() +" | Car left : "+carNo + " | Available spaces : " + semaphore.availablePermits());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}

class Test {
    static ParkingLot parkingLot = new ParkingLot();
    private static int NO_OF_CARS = 20;
    public static void main(String[] args) {
        for(int i = 0;  i < NO_OF_CARS; i++) {
            int finalI = i+1;
            new Thread(() -> parkingLot.parkCar(finalI)).start();
        }
    }
}
