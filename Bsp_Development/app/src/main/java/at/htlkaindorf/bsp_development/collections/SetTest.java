package at.htlkaindorf.bsp_development.collections;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {

    public static void main(String[] args) {
        Set<Integer> lottoNumbers = new TreeSet<>();

        Random rand = new Random();

        do {
            lottoNumbers.add(rand.nextInt(45)+1);
        } while(lottoNumbers.size()<6);

        for(Integer val : lottoNumbers) {
            System.out.print(val+",");
        }
        System.out.println();

        Set<Student> students = new HashSet<>();
        Student s1 = new Student("Bart", "Simpson");
        System.out.println(students.add(s1));
        System.out.println(students.contains(s1));
        s1.setFirstname("Homer");
        System.out.println(students.contains(s1));
        Student s2 = new Student("Homer", "Simpson");
        students.add(s2);
        for(Student su : students) {
            System.out.println(su);
        }

    }

}
