class Run implements Runnable {

    @Override
    public void run() {
        for(int i=1; i<=10; i++){
            System.out.println("Hello " + i);

            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class UsingRunnableInterface {
    public static void main(String[] args){
        // Traditional approach
        Thread t1 = new Thread(new Run());
        t1.start();

        // Just using lamda expression, and we don't need to create a separate class like Run when we are using lamda expression so this will concise the code.
        Thread t2 = new Thread(() -> {
            for(int i=1; i<=10; i++){
                System.out.println("Hello " + i);

                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t2.start();

        // Using Anonymous class, and we don't need to create a separate class like Run when we are using anonymous class also, so this will concise the code.
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=10; i++){
                    System.out.println("Hello " + i);

                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        t3.start();
    }

}
