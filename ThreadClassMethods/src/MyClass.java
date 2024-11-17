public class MyClass extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("Thread is running...");
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted " + e );
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyClass t1 = new MyClass();
        t1.start();

        // This will interrupt the current thread (t1 here) meaning if t1 thread is in sleep mode or in wait mode then it will throw exception
        t1.interrupt();

    }
}