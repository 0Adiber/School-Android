package at.htlkaindorf.bsp_development.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListCompTest {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Marge", "Simpson"));
        students.add(new Student("Bart", "Simpson"));
        students.add(new Student("Donald", "Duck"));
        students.add(new Student("Dagobert", "Duck"));

        students.sort((s1,s2) -> s1.getLastname().compareTo(s2.getLastname())); //braucht language level 8!!

        students.sort(Comparator.comparing(Student::getLastname)
                                .thenComparing(Student::getFirstname)
                                .reversed());


        for(Student st : students) {
            System.out.println(st);
        }


//        students.sort(new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return o1.getLastname().compareTo(o2.getLastname());
//            }
//        });

    }
}
