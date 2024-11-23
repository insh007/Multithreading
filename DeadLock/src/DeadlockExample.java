import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {
    public static void main(String[] args) {
        final Lock lock1 = new ReentrantLock();
        final Lock lock2 = new ReentrantLock();

        // Thread 1 tries to acquire both locks
        Thread t1 = new Thread(() -> {
            try {
                if (lock1.tryLock() && lock2.tryLock()) {
                    try {
                        System.out.println("Thread 1: Acquired both locks");
                    } finally {
                        lock2.unlock();
                        lock1.unlock();
                    }
                } else {
                    System.out.println("Thread 1: Could not acquire locks");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Thread 2 tries to acquire both locks
        Thread t2 = new Thread(() -> {
            try {
                if (lock2.tryLock(50, TimeUnit.MILLISECONDS) && lock1.tryLock()) {
                    try {
                        System.out.println("Thread 2: Acquired both locks");
                    } finally {
                        lock1.unlock();
                        lock2.unlock();
                    }
                } else {
                    System.out.println("Thread 2: Could not acquire locks");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
/*
* Output - Because During execution of Thread 2 tryLock tries to acquire lock immediately
Thread 1: Acquired both locks
Thread 2: Could not acquire locks
*
* But if we change it to tryLock with time like : lock2.tryLock(50, TimeUnit.MILLISECONDS) && lock1.tryLock() then the output:
Thread 1: Acquired both locks
Thread 2: Acquired both locks
* */