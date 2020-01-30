package at.htlkaindorf.exa_206_pethome.beans;

import java.time.LocalDate;

public class Dog extends Pet implements Comparable{
    private Size size;

    public Dog(String name, LocalDate dateOfBirth, Gender gender, Size size) {
        super(name, dateOfBirth, gender);
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public int compareTo(Object o) {
        if(this.size.compareTo(((Dog)o).getSize()) == 0) {
            return this.getDateOfBirth().compareTo(((Dog)o).getDateOfBirth());
        }
        return this.size.compareTo(((Dog)o).getSize());
    }
}
