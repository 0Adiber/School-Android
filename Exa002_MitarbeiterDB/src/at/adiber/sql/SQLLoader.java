package at.adiber.sql;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class SQLLoader {
    
    private static final Path path = Paths.get(System.getProperty("user.dir"), "src", "at", "adiber", "sql");
    
    public static String loadSql(String sqlName) {
        try {
            FileReader fr = new FileReader(Paths.get(path.toString(), sqlName).toFile());
            BufferedReader br = new BufferedReader(fr);
            
            return br.lines().collect(Collectors.joining("\n"));
            
        } catch (FileNotFoundException ex) {
            return "";
        }
    }
}
