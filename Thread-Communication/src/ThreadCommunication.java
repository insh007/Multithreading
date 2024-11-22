class SharedResource{
    private int data;
    private boolean hasData;

    public synchronized void produce(int value){
        while (hasData){
            try{
                wait(); // meaning wait to acquire the lock
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify(); // notify other thread for acquiring the lock
        // notifyAll(); // To notify more than one thread
    }

    public synchronized int consume(){

        while(!hasData){
            try{
                wait(); // meaning wait to acquire the lock
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        System.out.println("Consumed: " + data);
        notify();  // notify other thread for acquiring the lock
        // notifyAll(); // To notify more than one thread
        return data;
    }
}

class Producer implements Runnable {
    private SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run(){
        for(int i=0; i<10; i++){
            resource.produce(i);
        }
    }

}

class Consumer implements Runnable {
    private SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run(){
        for(int i=0; i<10; i++){
            int value = resource.consume();
        }
    }

}

public class ThreadCommunication {

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producerThread = new Thread(new Producer(resource));
        Thread consumerThread = new Thread(new Consumer(resource));

        producerThread.start();
        consumerThread.start();
    }

    //Note: 1. hasData is like a Buffer data. When hasData is true then we are assuming there is data
    // present in Buffer, then it will Consume by consumer and at this point Producer will not produce
    // data because it is already present in buffer meaning producer will wait.
    // 2. But when hasData is false meaning there is no data in Buffer, then Producer produce the data
    // and consumer will wait.

}

/* Output:
Produced: 0
Consumed: 0
Produced: 1
Consumed: 1
Produced: 2
Consumed: 2
Produced: 3
Consumed: 3
Produced: 4
Consumed: 4
Produced: 5
Consumed: 5
Produced: 6
Consumed: 6
Produced: 7
Consumed: 7
Produced: 8
Consumed: 8
Produced: 9
Consumed: 9
* */