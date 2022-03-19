import java.util.concurrent.Semaphore;

/**
 * A Worker object attempts to acquire a permit from
 * its semaphore by calling semaphore.acquire(); if 
 * any are available, it immediately gets to work and 
 * decrements the semaphore's number of free permits. 
 * If there are no free permits, the worker waits until
 * one is free.
 * 
 * Once working, the Worker prints "<name> began working.",
 * waits 2.5 seconds, then prints "<name> finished."
 * 
 * After its work is done, the worker releases its permit by
 * calling semaphore.release(), allowing any other workers
 * waiting for a permit a chance to obtain one.
 */
public class Worker implements Runnable {
    private Semaphore semaphore;
    private String name;

    public Worker(Semaphore s, String name) {
        semaphore = s;
        this.name = name;
    }

    /**
     * This method is called when a Thread object
     * wrapping this Worker (which implements Runnable)
     * has its start() method called. This method is thus
     * called from within a new thread.
     */
    @Override
    public void run() {
        try {
            // Attempt to acquire a permit (we wait until one is free)
            semaphore.acquire();
            // (Note that this Worker has decreased its semaphore's number of 
            // permits by 1 at this point)

            // We obtained a permit; begin working
            System.out.println(name + " began working.");
            Thread.sleep(2500); // sleep for 2.5 seconds, pretending to do a task
            System.out.println(name + " finished.");

            // We're done with our permit; release it for others to use
            semaphore.release();
            // (semaphore's number of permits was increased by 1)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
