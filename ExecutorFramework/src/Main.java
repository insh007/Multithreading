import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(int i=1; i<10; i++){
            int finalI = i;
            executor.submit(() -> {
                // Here we are not returning anything just holding result in result variable
                long result = factorial(finalI);
                System.out.println(result);
            });
        }
        executor.shutdown();
    }

    public static int factorial(int n)
    {
        int res = 1;
        for (int i = 2; i <= n; i++)
            res *= i;
        return res;
    }
}