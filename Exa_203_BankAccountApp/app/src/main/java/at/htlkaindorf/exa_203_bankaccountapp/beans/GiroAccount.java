package at.htlkaindorf.exa_203_bankaccountapp.beans;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class GiroAccount extends Account implements Parcelable {

    private double overdraft;

    public GiroAccount(String ivan, double balance, float intereset, double overdraft) {
        super(ivan, balance, intereset);
        this.overdraft = overdraft;
    }

    public GiroAccount(String line) {
        super("",0.0,0.0f);
        //id,type,iban,amount,overdraft,debitcard,interest
        String[] parts = line.split(",");
        super.setBalance(Double.parseDouble(parts[3]));
        super.setIntereset(Float.parseFloat(parts[parts.length-1]));
        super.setIvan(parts[2]);
        this.overdraft = Double.parseDouble(parts[parts.length-3]);
    }

    protected GiroAccount(Parcel in) {
        super(in);
        overdraft = in.readDouble();
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    public static final Creator<GiroAccount> CREATOR = new Creator<GiroAccount>() {
        @Override
        public GiroAccount createFromParcel(Parcel in) {
            return new GiroAccount(in);
        }

        @Override
        public GiroAccount[] newArray(int size) {
            return new GiroAccount[size];
        }
    };

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeDouble(overdraft);
    }
}
