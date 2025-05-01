package mutithreading;

class TicketBookingSystem {
    private int totalTickets;
    private boolean physicalTurn = true; // Flag to alternate between physical and digital

    public TicketBookingSystem(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public synchronized void bookPhysicalTickets(int ticketsRequested) throws InterruptedException {
        // Wait until it's physical booking's turn or no tickets left
        while (!physicalTurn && totalTickets > 0) {
            System.out.println("Physical booking waiting...");
            wait();
        }

        if (totalTickets <= 0) {
            return;
        }

        if (ticketsRequested <= totalTickets) {
            System.out.println("Physical booking: " + ticketsRequested + " tickets booked.");
            totalTickets -= ticketsRequested;
            System.out.println("Remaining tickets: " + totalTickets);
        } else {
            System.out.println("Physical booking: Not enough tickets. Only " + totalTickets + " available.");
        }

        // Switch turn to digital
        physicalTurn = false;
        notify(); // Notify waiting digital booking thread
    }

    public synchronized void bookDigitalTickets(int ticketsRequested) throws InterruptedException {
        // Wait until it's digital booking's turn or no tickets left
        while (physicalTurn && totalTickets > 0) {
            System.out.println("Digital booking waiting...");
            wait();
        }

        if (totalTickets <= 0) {
            return;
        }

        if (ticketsRequested <= totalTickets) {
            System.out.println("Digital booking: " + ticketsRequested + " tickets booked.");
            totalTickets -= ticketsRequested;
            System.out.println("Remaining tickets: " + totalTickets);
        } else {
            System.out.println("Digital booking: Not enough tickets. Only " + totalTickets + " available.");
        }

        // Switch turn to physical
        physicalTurn = true;
        notify(); // Notify waiting physical booking thread
    }

    public int getRemainingTickets() {
        return totalTickets;
    }
}

class PhysicalBooking implements Runnable {
    private TicketBookingSystem bookingSystem;

    public PhysicalBooking(TicketBookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
    }

    @Override
    public void run() {
        while (bookingSystem.getRemainingTickets() > 0) {
            int ticketsToBook = (int) (Math.random() * 10) + 1; // Random number between 1-10

            try {
                bookingSystem.bookPhysicalTickets(ticketsToBook);

                // Demonstrate yield() - gives chance to other threads to run
                Thread.yield();

                // Simulate some processing time
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Physical booking thread exiting - no tickets left");
    }
}

class DigitalBooking implements Runnable {
    private TicketBookingSystem bookingSystem;

    public DigitalBooking(TicketBookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
    }

    @Override
    public void run() {
        while (bookingSystem.getRemainingTickets() > 0) {
            int ticketsToBook = (int) (Math.random() * 10) + 1; // Random number between 1-10

            try {
                bookingSystem.bookDigitalTickets(ticketsToBook);

                // Demonstrate yield() - gives chance to other threads to run
                Thread.yield();

                // Simulate some processing time
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Digital booking thread exiting - no tickets left");
    }
}

public class AdvancedTicketBookingExample {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem(1000);

        Thread physicalThread = new Thread(new PhysicalBooking(bookingSystem));
        Thread digitalThread = new Thread(new DigitalBooking(bookingSystem));

        System.out.println("Starting ticket booking system with 1000 tickets...");

        physicalThread.start();
        digitalThread.start();

        try {
            physicalThread.join();
            digitalThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tickets booked. Program ending.");
    }
}