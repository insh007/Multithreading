import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);

        CountDownLatch latch = new CountDownLatch(numberOfServices);
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        latch.await();

        System.out.println("Main");
        executorService.shutdown();

    }
}

class DependentService implements Callable<String> {

    private final CountDownLatch latch;

    public DependentService(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        try{
            System.out.println(Thread.currentThread().getName() + " service started...");
            Thread.sleep(2000);
        }finally {
            latch.countDown();
        }
        return "ok";
    }
}
/*
  CountDownLatch:
* CountDownLatch is a synchronization aid in Java's java.util.concurrent package.
* It allows one or more threads to wait until a set of operations being performed
  by other threads completes.

  Reusability: CountDownLatch cannot be reused once the count reaches zero. Use a CyclicBarrier
               if you need reusability.
  Thread Safety: CountDownLatch is thread-safe, so multiple threads can safely interact with it.

  When NOT to Use :
  If you need to reuse the synchronization mechanism multiple times, use a CyclicBarrier or Semaphore instead.
* */