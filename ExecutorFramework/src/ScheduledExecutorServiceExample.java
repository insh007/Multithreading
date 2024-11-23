import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

//        scheduledExecutorService.schedule(
//                () -> System.out.println("Task executed after 5 seconds!"),
//                5,
//                TimeUnit.SECONDS);

//        scheduledExecutorService.shutdown();

        scheduledExecutorService.scheduleAtFixedRate(
                () -> System.out.println("Task executed after every 5 seconds"),
                5,
                5,
                TimeUnit.SECONDS
        );
        // Due to periodic argument in above scheduleAtFixedRate, we need to
        scheduledExecutorService.schedule(() ->{
                    System.out.println("Initiating shutdown...");
                    scheduledExecutorService.shutdown();
                }, 20, TimeUnit.SECONDS
        );

        // ScheduledExecutorService have other method also.

    }
}
