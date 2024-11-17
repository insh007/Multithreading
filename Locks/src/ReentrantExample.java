import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    private final Lock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try{
            System.out.println("Outer method");
            innerMethod();
        }
        finally {
            lock.unlock();
        }

    }

    public void innerMethod() {
        lock.lock(); // indefinite wait
//         lock.lockInterruptibly();
        try{
            System.out.println("Inner method");
        }
          // use catch with lockInterruptibly() method
//        catch (InterruptedException e) {
//            System.out.println("Thread interrupted while waiting for the lock");
//        }
        finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        ReentrantExample example = new ReentrantExample();
        example.outerMethod();
    }

    /* Note:
    *  1. lock.lock()
            i) Acquires the lock unconditionally.
            ii)If the lock is already held by another thread,
            * the current thread is put into a waiting state until the lock becomes available.
        Behavior:
        * Cannot be interrupted while waiting for the lock.
        * The thread will remain blocked until it acquires the lock, even if it is interrupted.

        2. lock.lockInterruptibly()
        * i) Acquires the lock unless the thread is interrupted while waiting.
        * ii) If the current thread is interrupted while waiting to acquire the lock, an InterruptedException is thrown.
        Behavior:
        * Responds to interruptions.
        * If the thread is interrupted while waiting for the lock, it will immediately exit and throw an InterruptedException.
    * */
}
