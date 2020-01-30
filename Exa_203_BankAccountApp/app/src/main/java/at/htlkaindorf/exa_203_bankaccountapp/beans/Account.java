package at.htlkaindorf.exa_203_bankaccountapp.beans;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Account implements Parcelable {
    private String ivan;
    private double balance;
    private float intereset;

    public Account(String ivan, double balance, float intereset) {
        this.ivan = ivan;
        this.balance = balance;
        this.intereset = intereset;
    }

    protected Account(Parcel in) {
        ivan = in.readString();
        balance = in.readDouble();
        intereset = in.readFloat();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return getIvan();
    }

    public String getIvan() {
        return ivan;
    }

    public void setIvan(String ivan) {
        this.ivan = ivan;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public float getIntereset() {
        return intereset;
    }

    public void setIntereset(float intereset) {
        this.intereset = intereset;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ivan);
        dest.writeDouble(balance);
        dest.writeFloat(intereset);
    }
}
