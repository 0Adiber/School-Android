package at.htlkaindorf.exa_203_bankaccountapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;
import at.htlkaindorf.exa_203_bankaccountapp.bl.AccountAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AccountAdapter aa;

    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        recyclerView = findViewById(R.id.rvAccounts);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));

        aa = new AccountAdapter();

        recyclerView.setAdapter(aa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.miStudents) { //filter student
            aa.filterAccounts("Student");
        }else if(item.getItemId() == R.id.miGiro) { //filter giro
            aa.filterAccounts("Giro");
        } else { //no filter
            aa.filterAccounts("none");
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99 && resultCode == 999) {
            String iban = data.getStringExtra("iban");
            if(iban == null) return;
            Account acc = data.getParcelableExtra("account");
            double amount = data.getDoubleExtra("amount", 0.0);

            aa.transferMoney(acc, iban, amount);
        }
    }
}
