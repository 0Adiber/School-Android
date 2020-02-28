package at.htlkaindorf.bsp_development.collections;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;

public class Student1 implements Parcelable {
    private String name;
    private int catNr;
    private LocalDate dateOfBirth;
    private Uri uri;

    public Student1(String name, int catNr, LocalDate dateOfBirth, Uri uri) {
        this.name = name;
        this.catNr = catNr;
        this.dateOfBirth = dateOfBirth;
        this.uri = uri;
    }

    protected Student1(Parcel in) {
        name = in.readString();
        catNr = in.readInt();
        uri = in.readParcelable(Uri.class.getClassLoader());
        dateOfBirth = LocalDate.ofEpochDay(in.readLong());
    }

    public static final Creator<Student1> CREATOR = new Creator<Student1>() {
        @Override
        public Student1 createFromParcel(Parcel in) {
            return new Student1(in);
        }

        @Override
        public Student1[] newArray(int size) {
            return new Student1[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCatNr() {
        return catNr;
    }

    public void setCatNr(int catNr) {
        this.catNr = catNr;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(catNr);
        dest.writeParcelable(uri, flags);
        dest.writeLong(dateOfBirth.toEpochDay());
    }
}
