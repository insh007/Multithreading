public class MyClass extends Thread{
    @Override
    public void run() {
        for(int i=1; i<=5; i++){
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.yield(); // This method will hint the Scheduler to give the chance to another thread as well.
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyClass t1 = new MyClass();
        t1.start();

        MyClass t2 = new MyClass();
        t2.start();

    }
}

// Output without Thread.yield() method:

//Thread-0 is running
//Thread-0 is running
//Thread-0 is running
//Thread-0 is running
//Thread-0 is running
//Thread-1 is running
//Thread-1 is running
//Thread-1 is running
//Thread-1 is running
//Thread-1 is running

// Output with Thread.yield() method:

//Thread-0 is running
//Thread-0 is running
//Thread-1 is running
//Thread-1 is running
//Thread-0 is running
//Thread-1 is running
//Thread-1 is running
//Thread-0 is running
//Thread-1 is running
//Thread-0 is running