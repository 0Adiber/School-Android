package io;

import beans.Gender;
import beans.Student;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IO_Acess {

    private List<Student> students = new ArrayList<>();
    Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "students.ser");

    public IO_Acess() {
        students.add(new Student("Leon", "Anghel", LocalDate.now().minusYears(16), Gender.MALE));
        students.add(new Student("Nico", "Baumann", LocalDate.now().minusYears(17), Gender.FEMALE));
    }
    
    public void writeToFile() throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(path.toFile());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        oos.writeObject(students);
        
//        for(Student s : students) {
//            oos.writeObject(s);
//        }
        oos.close();   
    }
    
    public void readFile() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(path.toFile());
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        List<Student> studentsFromFile = (List<Student>)ois.readObject();
        
//        List<Student> studentsFromFile = new LinkedList<>();
//        
//        try {
//            while(true) {
//                Student s = (Student)ois.readObject();
//                studentsFromFile.add(s);
//            }
//        }catch(EOFException e) {
//        }
//        
        for(Student s : studentsFromFile) {
            System.out.println(s);
        }
        ois.close();
        
    }
    
    public static void main(String[] args) {
        IO_Acess io = new IO_Acess();
        try {
            io.writeToFile();
            io.readFile();
        } catch (IOException ex) {
            Logger.getLogger(IO_Acess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IO_Acess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
