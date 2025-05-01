package mutithreading.basics.reinterantlock;

import javax.print.Doc;
import java.util.Random;
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
            new Thread(() -> doctorClinic.doctorRoom(finalI)).start();
        }
    }
}
