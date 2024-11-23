public class DeadlockExample {
    public static void main(String[] args) {
        final String resource1 = "Resource1";
        final String resource2 = "Resource2";

        // Thread 1 tries to lock resource1 then resource2
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Locked " + resource1);

                // Simulate some work with resource1
                try { Thread.sleep(50); } catch (InterruptedException e) {}

                synchronized (resource2) {
                    System.out.println("Thread 1: Locked " + resource2);
                }
            }
        });

        // Thread 2 tries to lock resource2 then resource1
        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: Locked " + resource2);

                // Simulate some work with resource2
                try { Thread.sleep(50); } catch (InterruptedException e) {}

                synchronized (resource1) {
                    System.out.println("Thread 2: Locked " + resource1);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
