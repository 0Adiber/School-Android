package at.htlkaindorf.exa_203_bankaccountapp;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;
import at.htlkaindorf.exa_203_bankaccountapp.beans.GiroAccount;
import at.htlkaindorf.exa_203_bankaccountapp.beans.StudentAccount;

public class IO_Access {

    public static List<Account> loadAccounts() {
        List<Account> accounts = new LinkedList<>();
        AssetManager am = MainActivity.mainActivity.getAssets();

        try {
            InputStream in = am.open("pet.csv");
            accounts = new BufferedReader(new InputStreamReader(in))
                    .lines()
                    .skip(1)
                    .map(l -> (l.split(",")[1].equalsIgnoreCase("student")) ? new StudentAccount(l) : new GiroAccount(l))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            Log.e("Main", e.toString());
        }

        return accounts;
    }

}
