package bl;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NameThread implements Runnable{

    private StringBuilder sb = new StringBuilder(10_000_000);
    
    @Override
    public void run() {
        for (int i = 0; i < 100_000; i++) {
            synchronized(sb) {
                sb.append(Thread.currentThread().getName());
            }
        }
    }

    public StringBuilder getSb() {
        return sb;
    }  
    

    public static void main(String[] args) {
        NameThread nt = new NameThread();
        
        Thread thread1 = new Thread(nt, "Fred");
        Thread thread2 = new Thread(nt, "Laurentia");
        Thread thread3 = new Thread(nt, "Bart");
        
        long startTime = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        thread3.start();
        
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(NameThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Duration: " + duration + "ms");
        
        StringBuilder sb = nt.getSb();
        
        System.out.println("Length: " + sb.length());
        System.out.println(sb.substring(0,1000));
        
    }
    
}
