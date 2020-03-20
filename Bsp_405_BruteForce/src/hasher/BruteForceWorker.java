package hasher;

import beans.Person;
import static hasher.BruteForceManager.signs;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import test.HashTest;

public class BruteForceWorker implements Callable<String>{
    
    private Person person;
    
    public BruteForceWorker(Person p) {
        this.person = p;
    }

    @Override
    public String call() throws Exception {
        //get the current time to calculate how long it took
        long millis = System.currentTimeMillis();
        
        //print which thread is running and what person
        System.out.println(String.format("Running... (%s) -> %s %s", Thread.currentThread().getName(), person.getFirstname(), person.getLastname()));
        
        for(char c1 : BruteForceManager.signs) {
            for(char c2 : BruteForceManager.signs) {
                for(char c3 : BruteForceManager.signs) {
                    for(char c4 : BruteForceManager.signs) {
                        for(char c5 : BruteForceManager.signs) {
                            
                            try {
                                MessageDigest md = MessageDigest.getInstance("MD5");
                                String password = person.getFirstname() + person.getLastname() + String.format("%s%s%s%s%s", c1,c2,c3,c4,c5);
                                byte[] hash = md.digest(password.getBytes());
                                String hashAsHexString = DatatypeConverter.printHexBinary(hash);
                                if(hashAsHexString.equalsIgnoreCase(person.getHash())) { //check hash
                                    long time = (System.currentTimeMillis()-millis); //calculate time it took
                                    return String.format("%s %s's password: %s", person.getFirstname(), person.getLastname(), String.format("%s%s%s%s%s", c1,c2,c3,c4,c5) + "(" + ((time-time%1000)/1000) + "s " + (time%1000) + "ms)"); //return string for nice reading
                                }
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(HashTest.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        }
                    }
                }
            }
        }
        
        
        return null;
    }
    
}
