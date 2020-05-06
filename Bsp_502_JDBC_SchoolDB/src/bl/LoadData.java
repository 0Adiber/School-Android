package bl;

import beans.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class LoadData {
    private static final Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "Studentlist_3xHIF.csv");
    
    public static List<Student> loadData() {
        try {
            FileReader fr = new FileReader(Paths.get(path.toString()).toFile());
            BufferedReader br = new BufferedReader(fr);
                                  
            return br.lines().skip(1).map(Student::new).collect(Collectors.toList());
            
        } catch (FileNotFoundException ex) {
            return null;
        }
    }
}
