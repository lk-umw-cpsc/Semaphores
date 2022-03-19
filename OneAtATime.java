import java.util.concurrent.Semaphore;

/**
 * This program has multiple competing threads, with only
 * a single thread allowed to run at once. This is done
 * using a semaphore initialized with the value 1.
 */
public class OneAtATime {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(1, true);

        Worker[] workers = new Worker[10];
        for (int i = 0, num = workers.length; i < num; i++) {
            workers[i] = new Worker(s, "Worker " + (i + 1));
        }

        for (Worker w : workers) {
            new Thread(w).start();
        }
    }
}
