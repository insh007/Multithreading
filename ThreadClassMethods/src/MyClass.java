public class MyClass extends Thread{
    @Override
    public void run() {
        System.out.println("t1 before sleep"); // This will print first
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("t1 thread"); // This will print 2nd when you are using join method
    }

    public static void main(String[] args) throws InterruptedException {
        MyClass t1 = new MyClass();
        t1.start();

        t1.join(); // this method completely execute current thread(t1 Thread here)

        System.out.println("Hello world!"); // This will print 3rd when you are using join method
    }
}