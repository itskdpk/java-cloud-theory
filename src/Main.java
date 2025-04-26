//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        /*MyThread t1 = new MyThread();
        MyThread2 t2 = new MyThread2();
        */

        Runnable r1 = () ->
        {
            for (int i = 0; i < 10; i++) {
                Thread.currentThread().getName();

                System.out.println("Thread Name: " + Thread.currentThread().getName() + " - Count: " + i);
            }
        };
        Runnable r2 = () -> {
            for (int i = 0; i < 10; i++) {
                Thread.currentThread().getName();
                System.out.println("Thread Name: " + Thread.currentThread().getName() + " - Count: " + i);
            }
        };


        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

    }
}