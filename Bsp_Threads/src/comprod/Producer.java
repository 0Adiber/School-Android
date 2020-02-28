package comprod;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable{
    
    private Stack stack;
    private int cnt;
    private static final Random rand = new Random();

    public Producer(Stack stack, int cnt) {
        this.stack = stack;
        this.cnt = cnt;
    }

    @Override
    public void run() {
        System.out.println("Producer started...");
        for (int i = 0; i < cnt; i++) {
            synchronized(stack) {
                while(stack.isFull()) {
                    System.out.println("Stack is full");
                    try {
                        System.out.println("Producer has to wait");
                        stack.wait();   // macht lock für das objekt automatisch FREI
                        System.out.println("Producer finished waiting");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("Producer pushes to stack");
                stack.push(rand.nextInt(100));
                System.out.println(stack.toString());
                stack.notify();
            }
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

}
