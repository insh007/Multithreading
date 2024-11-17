public class MyClass extends Thread{
    @Override
    public void run() {

        while (true){
            System.out.println("Hello World");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyClass t1 = new MyClass();
        t1.setDaemon(true);
        t1.start();

        System.out.println("main done");

    }
}
/*
* A daemon thread in Java is a background thread that provides support services for other threads
* or performs tasks in the background. It runs in parallel with the main application but
* doesn't prevent the JVM from exiting when all user threads have finished execution.
*
* Key Methods:
    1. setDaemon(boolean on):
            i) Sets the thread as a daemon thread.
            ii) Must be called before start(), otherwise it will throw IllegalThreadStateException.
    2. isDaemon():
            i) Returns true if the thread is a daemon thread.
* */