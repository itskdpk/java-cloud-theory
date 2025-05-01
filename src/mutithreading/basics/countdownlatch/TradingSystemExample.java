package mutithreading.basics.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class TradingSystemExample {

    private static int NO_OF_WARM_UP_TASKS = 3;
    private static CountDownLatch latch = new CountDownLatch(NO_OF_WARM_UP_TASKS);
    private static boolean isSystemUp = false ;
    public static void main(String[] args) {
        System.out.println("Please Wait System is Warming Up...");

        new Thread(TradingSystemExample::startTradingSetup).start();
        new Thread(TradingSystemExample::loadHistoricalData).start();
        new Thread(TradingSystemExample::fetchFinancialNews).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        isSystemUp = true;

        System.out.println("Trading System is up and running...");

        executeTrade();

    }

    private static void executeTrade() {
        System.out.println("Executing trade at " + System.currentTimeMillis());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void startTradingSetup(){
        try {
            System.out.println("Starting Trading Setup Task");
            Thread.sleep(3000);
            System.out.println("Completed Trading Setup Task");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            latch.countDown();
        }

    }

    static void loadHistoricalData() {
        try {
            System.out.println("Loading Historical Data Started");
            Thread.sleep(1000);
            System.out.println("Loading Historical Data Completed");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            latch.countDown();
        }
    }

    static void fetchFinancialNews() {
        try {
            System.out.println("Start Fetching Current Financial News");
            Thread.sleep(2000);
            System.out.println("Complete Fetching Current Financial News");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            latch.countDown();
        }
    }
}
