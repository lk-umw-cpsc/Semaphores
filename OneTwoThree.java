import java.util.concurrent.Semaphore;

/**
 * This program starts its semaphore off with an initial value of zero,
 * preventing any thread from running. It then increases the number of
 * threads allowed to work at once by calling release() three times,
 * with a second passed between each of these release() calls.
 * 
 * This causes a sort of staggered start, where only one thread is
 * running at a time, then two (after two seconds), 
 * then three (after three seconds) running at the same time.
 * 
 * This behavior is possible because each call to release() increments 
 * the semaphore's number of available permits by one.
 */
public class OneTwoThree {

    public static void main(String[] args) {
        Semaphore s = new Semaphore(0, true);
        Worker[] workers = new Worker[10];

        for (int i = 0, num = workers.length; i < num; i++) {
            workers[i] = new Worker(s, "Worker " + (i + 1));
        }

        for (Worker w : workers) {
            new Thread(w).start();
        }
        
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
                s.release();
            }
        } catch (InterruptedException e) {
            
        }
    }
}