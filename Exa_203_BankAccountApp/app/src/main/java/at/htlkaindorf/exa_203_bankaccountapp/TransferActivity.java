package at.htlkaindorf.exa_203_bankaccountapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;
import at.htlkaindorf.exa_203_bankaccountapp.beans.GiroAccount;

public class TransferActivity extends AppCompatActivity {

    private TextView tvAvailableT, tvBalanceT;
    private EditText etAmount;
    private AutoCompleteTextView acIban;
    private Button btTransfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        Account account = getIntent().getParcelableExtra("account");
        String[] ibans = getIntent().getStringArrayExtra("ibans");

        ((TextView) findViewById(R.id.tvTypeT)).setText(account instanceof GiroAccount ? "Giro-Account" : "Student-Account");
        ((TextView)findViewById(R.id.tvIvanT)).setText(account.getIvan());
        tvBalanceT = findViewById(R.id.tvBalanceT);
        tvBalanceT.setText(convMoney(account.getBalance()));

        if(account.getBalance()>0) tvBalanceT.setTextColor(Color.GREEN);
        else tvBalanceT.setTextColor(Color.RED);

        tvAvailableT = findViewById(R.id.tvAvailableT);
        tvAvailableT.setText(convMoney(account instanceof GiroAccount ? account.getBalance() + ((GiroAccount)account).getOverdraft() : account.getBalance()));
        btTransfer = findViewById(R.id.btTransfer);

        etAmount = findViewById(R.id.etAmount);
        acIban = findViewById(R.id.acIban);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, ibans);
        acIban.setAdapter(adapter);

        btTransfer.setBackgroundTintList(ColorStateList.valueOf(account.getBalance()>0?0xff30b032:0xffcccccc));

        btTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("account", account);
                intent.putExtra("iban", acIban.getText().toString());
                intent.putExtra("amount", Double.parseDouble(etAmount.getText().toString()));
                setResult(999, intent);
                finish();
            }
        });

        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(etAmount.getText().toString().isEmpty()) {
                    btTransfer.setBackgroundColor(Color.GRAY);
                    return;
                }
                double amount = Double.parseDouble(etAmount.getText().toString());


                tvBalanceT.setText(convMoney(account.getBalance()-amount));
                tvAvailableT.setText(convMoney((account instanceof GiroAccount ? account.getBalance()+((GiroAccount)account).getOverdraft() : account.getBalance())-amount));

                if(amount > (account instanceof GiroAccount ? account.getBalance()+((GiroAccount)account).getOverdraft():account.getBalance())) {
                    btTransfer.setClickable(false);
                    btTransfer.setBackgroundTintList(ColorStateList.valueOf(0xffcccccc));
                } else {
                    btTransfer.setClickable(true);
                    btTransfer.setBackgroundTintList(ColorStateList.valueOf(0xff30b032));
                }
                tvBalanceT.setTextColor(amount > account.getBalance()?0xffe32030:0xff30b032);
            }
        });

    }

    private String convMoney(double money) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        String formatted = format.format(money).replace(".", " ");
        return "€ " + formatted.subSequence(0,formatted.length()-1);
    }

}
