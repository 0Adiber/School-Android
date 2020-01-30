package at.htlkaindorf.exa_206_pethome.beans;

import android.net.Uri;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class Cat extends Pet implements Comparable{
    private CatColor color;
    private transient Uri pictureUri;

    public Cat(String name, LocalDate dateOfBirth, Gender gender, CatColor color, Uri pictureUri) {
        super(name, dateOfBirth, gender);
        this.color = color;
        this.pictureUri = pictureUri;
    }

    public CatColor getColor() {
        return color;
    }

    public Uri getPictureUri() {
        return pictureUri;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeUTF(pictureUri.toString());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        pictureUri = Uri.parse(ois.readUTF());
    }

    @Override
    public int compareTo(Object o) {
        return this.getDateOfBirth().compareTo(((Cat)o).getDateOfBirth());
    }
}
