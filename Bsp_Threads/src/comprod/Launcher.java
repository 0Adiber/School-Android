package comprod;

public class Launcher {
    
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        Producer producer = new Producer(stack, 100);
        Consumer consumer = new Consumer(stack, 100);
        
        Thread pThread = new Thread(producer, "Producer");
        Thread cThread = new Thread(consumer, "Consumer");
        
        pThread.start();
        cThread.start();
    }

}
