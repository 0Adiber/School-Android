package test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;


public class HashTest {
    
    public static void main(String[] args) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String password = "java" + "shit" + "aaaaa";
            byte[] hash = md.digest(password.getBytes());
            String hashAsHexString = DatatypeConverter.printHexBinary(hash);
            System.out.println(hashAsHexString);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
