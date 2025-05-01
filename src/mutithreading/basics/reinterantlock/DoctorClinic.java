package mutithreading.basics.reinterantlock;

import javax.print.Doc;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DoctorClinic {
    Lock lock = new ReentrantLock();

    void doctorRoom(int patientNo) {
        lock.lock();
        try {
            System.out.println("Patient : "+ patientNo +" Entering inside Doctor Room");
            Thread.sleep(new Random().nextInt(1, 10) * 1000);
            System.out.println("Patient : "+ patientNo +" Exiting Doctors Room");
        } catch (InterruptedException e) {
            System.out.println("Doctor Room interrupted");
        } finally {
            lock.unlock();
        }
    }
}

class Patients {
    private static final int NO_OF_PATIENTS = 10;
    public static void main(String[] args) {
        DoctorClinic doctorClinic = new DoctorClinic();



        for (int i = 0; i < NO_OF_PATIENTS ; i++) {
            int finalI = i;

            //1st way of Creating Threads
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    doctorClinic.doctorRoom(finalI);
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();

            //2nd Way of Creating Threads
            // new Thread(() -> doctorClinic.doctorRoom(finalI)).start();

        }

        //3rd Way of Creating Threads

        /*ExecutorService executorService = Executors.newFixedThreadPool(NO_OF_PATIENTS);
        for (int i = 0;  i < NO_OF_PATIENTS; i++) {
            int finalI = i;
            executorService.execute(() -> doctorClinic.doctorRoom(finalI));
        }*/
    }
}
