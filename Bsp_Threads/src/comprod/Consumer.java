package comprod;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable{
    
    private Stack stack;
    private int cnt;
    private static final Random rand = new Random();

    public Consumer(Stack stack, int cnt) {
        this.stack = stack;
        this.cnt = cnt;
    }

    @Override
    public void run() {
        System.out.println("Consumer started...");
        for (int i = 0; i < cnt; i++) {
            synchronized(stack) {
                while(stack.isEmpty()) {
                    System.out.println("Stack is empty");
                    try {
                        System.out.println("Consumer has to wait");
                        stack.wait();
                        System.out.println("Consumer finished waiting");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("Consumer takes value: " + stack.pop());
                System.out.println(stack.toString());
                stack.notify();
            }
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    
    
}
