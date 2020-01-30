package at.htlkaindorf.exa_203_bankaccountapp.beans;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class StudentAccount extends Account implements Parcelable {

    private boolean debitCard;

    public StudentAccount(String ivan, double balance, float intereset, boolean debitCard) {
        super(ivan, balance, intereset);
        this.debitCard = debitCard;
    }

    public StudentAccount(String line) {
        super("",0.0,0.0f);
        //id,type,iban,amount,overdraft,debitcard,interest
        String[] parts = line.split(",");
        super.setBalance(Double.parseDouble(parts[3]));
        super.setIntereset(Float.parseFloat(parts[parts.length-1]));
        super.setIvan(parts[2]);
        this.debitCard = parts[parts.length - 2].equals("T");
    }

    protected StudentAccount(Parcel in) {
        super(in);
        debitCard = in.readBoolean();
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public boolean isDebitCard() {
        return debitCard;
    }

    public void setDebitCard(boolean debitCard) {
        this.debitCard = debitCard;
    }

    public static final Creator<StudentAccount> CREATOR = new Creator<StudentAccount>() {
        @Override
        public StudentAccount createFromParcel(Parcel in) {
            return new StudentAccount(in);
        }

        @Override
        public StudentAccount[] newArray(int size) {
            return new StudentAccount[size];
        }
    };

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeBoolean(debitCard);
    }
}
