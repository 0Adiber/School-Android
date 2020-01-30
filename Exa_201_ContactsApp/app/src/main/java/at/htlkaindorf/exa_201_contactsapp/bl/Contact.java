package at.htlkaindorf.exa_201_contactsapp.bl;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    private String firstname;
    private String lastname;
    private String language;
    private char gender;
    private Uri picture;
    private String phoneNumber;

    public Contact(String line) {
        String[] l = line.split(",");
        firstname = l[1];
        lastname = l[2];
        language = l[3];
        gender = l[4].charAt(0);
        picture = Uri.parse(l[5]);
        phoneNumber = l[6];
    }

    protected Contact(Parcel in) {
        firstname = in.readString();
        lastname = in.readString();
        language = in.readString();
        gender = (char) in.readInt();
        picture = in.readParcelable(Uri.class.getClassLoader());
        phoneNumber = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Uri getPicture() {
        return picture;
    }

    public void setPicture(Uri picture) {
        this.picture = picture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(language);
        dest.writeInt((int) gender);
        dest.writeParcelable(picture, flags);
        dest.writeString(phoneNumber);
    }
}
