package bl;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class AccountUser implements Runnable{

    private String name;
    private Account account;
    private int moves;
    private JTextArea debug;
    private static final Random RAND = new Random();
    
    public AccountUser(String name, Account account, int moves, JTextArea debug) {
        this.name = name;
        this.account = account;
        this.moves = moves;
        this.debug = debug;
    }
    
    @Override
    public void run() {
        
        int deadlocks;
        
        for(int i = 0; i<moves; i++) {
            deadlocks = 0;
            long amount = (RAND.nextInt(41)+10) * (RAND.nextBoolean() ? (-1): (1));
            
            synchronized(account) {
                while((account.getMoney()+amount) < 0 && deadlocks < 3) {
                    
                    //event queue to send to debug
                    try {
                        EventQueue.invokeAndWait(new Runnable() {
                            @Override
                            public void run() {
                                debug.append(name + " Can't perform Transaction \n");
                            }
                        });
                    } catch (InterruptedException ex) {
                    } catch (InvocationTargetException ex) {
                    }
                    
                    try {    
                        account.wait(2000);
                    } catch(InterruptedException ex) {}
                    
                    deadlocks++;
                    
                    //event queue to send to debug
                    try {
                        EventQueue.invokeAndWait(new Runnable() {
                            @Override
                            public void run() {
                                debug.append(name + " finished waiting \n");
                            }
                        });
                    } catch (InterruptedException ex) {
                    } catch (InvocationTargetException ex) {
                    }
                }
                
                if(deadlocks >= 3) {
                    i--;
                    continue;
                }
                
                account.performMoneyTransaction(amount);
                
                account.notifyAll();
            
                //event queue to send to debug
                try {
                    EventQueue.invokeAndWait(new Runnable() {
                        @Override
                        public void run() {
                            debug.append(name + " performed transaction: " + amount + "\n");
                        }
                    });
                } catch (InterruptedException ex) {
                } catch (InvocationTargetException ex) {
                }
            }            
            
            try {
                Thread.sleep(RAND.nextInt(1000)+1);
            } catch (InterruptedException ex) {}
        }
        
        debug.append(">>> " + name + " has finished <<<\n");
        
    }

    public String getName() {
        return name;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccountUser other = (AccountUser) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
 
    
    
}
