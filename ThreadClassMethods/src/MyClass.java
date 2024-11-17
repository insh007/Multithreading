public class MyClass extends Thread{

    // To give custom name to Thread
    public MyClass(String name){
        super(name);
    }

    @Override
    public void run() {
        for(int i=1; i<=5; i++){
            System.out.println(Thread.currentThread().getName() + " - Priority: " + Thread.currentThread().getPriority() + " - Count: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        MyClass t1 = new MyClass("bond");
        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start();


    }
}