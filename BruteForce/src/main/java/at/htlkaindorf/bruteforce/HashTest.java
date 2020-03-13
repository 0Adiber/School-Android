package at.htlkaindorf.bruteforce;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashTest {

    public static void main(String[] args) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String pw = "Lamar" + "Shinton" + "9j0g3";
            byte[] hash = md.digest(pw.getBytes());
            
            String hashAsHexString = DatatypeConverter.printHexBinary(hash);
            System.out.println(hashAsHexString);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
