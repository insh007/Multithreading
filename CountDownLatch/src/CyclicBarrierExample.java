import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        // Create a CyclicBarrier for 3 threads
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("All workers reached the barrier. Moving to the next phase...");
        });

        // Create and start 3 worker threads
        for (int i = 1; i <= 3; i++) {
            new Thread(new Worker(barrier, "Worker-" + i)).start();
        }
    }
}

class Worker implements Runnable {
    private final CyclicBarrier barrier;
    private final String name;

    public Worker(CyclicBarrier barrier, String name) {
        this.barrier = barrier;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is working...");
            Thread.sleep((int) (Math.random() * 1000)); // Simulate work
            System.out.println(name + " reached the barrier.");
            barrier.await(); // Wait for other threads at the barrier
            System.out.println(name + " is proceeding to the next phase.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
/*
* Worker-1 is working...
Worker-3 is working...
Worker-2 is working...
Worker-2 reached the barrier.
Worker-3 reached the barrier.
Worker-1 reached the barrier.
All workers reached the barrier. Moving to the next phase...
Worker-2 is proceeding to the next phase.
Worker-1 is proceeding to the next phase.
Worker-3 is proceeding to the next phase.
* */
