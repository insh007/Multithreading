import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GenericFutureCallToCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Here submit method called runnable task, and we know that runnable interface contains VOID abstract method.
//        executorService.submit(() -> System.out.println("Hello"));

        // Here submit method called callable task, and we know that callable interface contains Generic Type abstract method.
        Future<String> future = executorService.submit(() -> "hello");
        System.out.println(future.get()); // hello



        executorService.shutdown();
    }
}
