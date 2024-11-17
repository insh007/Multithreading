public class Counter {
    private int count = 0;

    // This will synchronized whole method
//    public synchronized void increment(){
//        count++;
//    }

    // critical section
    public void increment(){
        // this will synchronized each instance and one thread will get the block statement at one time
        synchronized (this){
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
