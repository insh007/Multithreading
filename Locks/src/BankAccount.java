import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;

    // Explicit Lock
    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount) throws InterruptedException {

        // lock.lock(); - same as synchronized

        if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
            System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
            if(balance>=amount) {
                try{
                    System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                    Thread.sleep(3000);
                    balance -= amount;
                    System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
                }catch (Exception e){
                    Thread.currentThread().interrupt();
                }finally {
                    lock.unlock();
                }
            }else {
                System.out.println(Thread.currentThread().getName() + " insufficient balance");
            }

        }else {
            System.out.println(Thread.currentThread().getName() + " could not acquire the lock will again later");
        }

    }
}

// Note: Synchronized has intrinsic lock (automatic lock provided by java - every object in
// java has its own lock & by using synchronized keyword we can enable this automatic lock).