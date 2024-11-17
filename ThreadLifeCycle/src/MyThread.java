public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Running...");
        try {
            Thread.sleep(2000); // t1 Thread
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread(); // NEW state
        System.out.println(t1.getState());

        t1.start();
        System.out.println(t1.getState()); // RUNNABLE state

        // main thread runs the main method (all the statement inside the main method)
        System.out.println(Thread.currentThread()); // main Thread
        Thread.sleep(100); // sleep main thread

        System.out.println(t1.getState()); // TIMED_WAITING state

        t1.join(); // this method completely execute current thread(t1 Thread here)
        System.out.println(t1.getState()); // TERMINATED
    }

    /*
    * Thread Life Cycle States :
    * 1. New - when extended class object is created
    * 2. Runnable - ready to run or thread is running
    * 3. Running - once CPU get the time then thread is in running state
    * 4. Blocked/Waiting/TIMED_WAITING
    * 5. Terminated - thread die or completely executed
    *
    * */
}