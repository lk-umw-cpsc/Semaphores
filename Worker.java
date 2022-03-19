import java.util.concurrent.Semaphore;

public class Worker implements Runnable {
    private Semaphore semaphore;
    private String name;

    public Worker(Semaphore s, String name) {
        semaphore = s;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(name + " began working.");
            Thread.sleep(2500);
            System.out.println(name + " finished.");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
