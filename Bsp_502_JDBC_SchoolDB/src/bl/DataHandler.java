package bl;

import beans.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DataHandler {
    private static final Path path = Paths.get(System.getProperty("user.dir"), "src", "res");
    
    public static List<Student> loadData() {
        try {
            FileReader fr = new FileReader(Paths.get(path.toString(),"Studentlist_3xHIF.csv").toFile());
            BufferedReader br = new BufferedReader(fr);
                                  
            return br.lines().skip(1).map(Student::new).collect(Collectors.toList());
            
        } catch (FileNotFoundException ex) {
            return null;
        }
    }
    
    public static boolean saveData(List<Student> students) {
        FileWriter fw;
        try {
            fw = new FileWriter(Paths.get(path.toString(), "output.csv").toFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(Student s : students) {
                bw.append(s.toString()+"\n");
            }
            bw.close();
            fw.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
}
