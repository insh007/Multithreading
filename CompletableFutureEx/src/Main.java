import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("worker");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "ok";
        });

        completableFuture.get(); // Block and wait for the result
        System.out.println(completableFuture.get()); // // Print the result

        System.out.println("Main");

        // Note: Has multiple methods to explore like get()
    }
}
/* Why and When to Use CompletableFuture :
*   CompletableFuture is part of Java's concurrent programming model. It is designed to
*   handle asynchronous programming more effectively by providing a rich API for running
*   tasks asynchronously, combining tasks, and handling results or errors.
* */