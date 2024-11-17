public class Test {
    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();
        MyThread t1 = new MyThread(counter);
        MyThread t2 = new MyThread(counter);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.getCount());

        /* Note :
        *   When same object resource is sharing between two thread like counter object above them make sure to
        *   use synchronized keyword or synchronized block on the operation(like increment method here)
        *   to get the expected result.
        * */
    }
}