public class DeadlockExample {
    public static void main(String[] args) {
        final String resource1 = "Resource1";
        final String resource2 = "Resource2";

        // Thread 1 locks resource1 first, then resource2
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Locked " + resource1);

                // Simulate some work
                try { Thread.sleep(50); } catch (InterruptedException e) {}

                synchronized (resource2) {
                    System.out.println("Thread 1: Locked " + resource2);
                }
            }
        });

        // Thread 2 locks resource1 first, then resource2 (same order as Thread 1)
        Thread t2 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 2: Locked " + resource1);

                // Simulate some work
                try { Thread.sleep(50); } catch (InterruptedException e) {}

                synchronized (resource2) {
                    System.out.println("Thread 2: Locked " + resource2);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
/*
How to Properly Resolve the Deadlock
Solution 1: Consistent Lock Ordering - Just like above example
Solution 2: Use ReentrantLock with tryLock - see next commit
* */
