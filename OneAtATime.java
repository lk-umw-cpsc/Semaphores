import java.util.concurrent.Semaphore;

/**
 * This program has multiple competing threads, with only
 * a single thread allowed to run at once. This is done
 * using a semaphore initialized with the value 1.
 */
public class OneAtATime {
    public static void main(String[] args) {
        // Permit only a single thread to run at a time
        Semaphore s = new Semaphore(1, true);

        // Initialize ten workers
        Worker[] workers = new Worker[10];
        for (int i = 0, num = workers.length; i < num; i++) {
            workers[i] = new Worker(s, "Worker " + (i + 1));
        }

        // Create a new thread for each worker
        for (Worker w : workers) {
            new Thread(w).start();
        }
    }
}
