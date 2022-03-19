import java.util.concurrent.Semaphore;

/**
 * This program allows the user to create Y threads while only
 * allowing X threads to run at a time.
 * 
 * Usage is UserDefined <num permits> <num threads>
 */
public class UserDefined {
    public static void main(String[] args) {
        final int argc = args.length;
        if (argc < 2) {
            System.out.println("Usage: UserDefined <num permits> <num threads>");
            return;
        }
        int numPermits, numThreads;
        try {
            numPermits = Integer.parseInt(args[0]);
            if (numPermits < 0) {
                System.out.println("num permits must be an integer value >= 0");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("num permits must be an integer value >= 0");
            return;
        }
        try {
            numThreads = Integer.parseInt(args[1]);
            if (numThreads < 1) {
                System.out.println("num threads must be an integer value > 1");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("num threads must be an integer value > 1");
            return;
        }
        
        // Initialize semaphore with the given number of permits
        Semaphore s = new Semaphore(numPermits, true);

        // Initialize the given number of workers
        Worker[] workers = new Worker[numThreads];
        for (int i = 0, num = workers.length; i < num; i++) {
            workers[i] = new Worker(s, "Worker " + (i + 1));
        }

        // Start each worker
        for (Worker w : workers) {
            new Thread(w).start();
        }
    }
}
