import java.util.concurrent.Semaphore;

/**
 * This program allows three threads to run at once by 
 * creating a semaphore with three "permits". Once the
 * three permits are handed out, the other threads must
 * wait for a permit to be freed up before they may run.
 */
public class ThreeAtOnce {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(3, true);

        // Initialize ten workers
        Worker[] workers = new Worker[10];
        for (int i = 0, num = workers.length; i < num; i++) {
            workers[i] = new Worker(s, "Worker " + (i + 1));
        }

        // Start each worker thread
        for (Worker w : workers) {
            new Thread(w).start();
        }
    }
}
