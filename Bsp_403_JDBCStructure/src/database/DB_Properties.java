package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB_Properties {
    
    private static final Properties PROPS = new Properties();
    
    static {
        FileInputStream fis = null;
        try {
            Path propertyFile = Paths.get(System.getProperty("user.dir"), "src", "res", "dbConnect.properties");
            fis = new FileInputStream(propertyFile.toFile());
            PROPS.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DB_Properties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DB_Properties.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(DB_Properties.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static String getProp(String key) {
        return PROPS.getProperty(key);
    }
    
}
