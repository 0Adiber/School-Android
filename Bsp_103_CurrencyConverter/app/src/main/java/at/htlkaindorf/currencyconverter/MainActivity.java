package at.htlkaindorf.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    boolean toUsd = true;
    double eurToUsd = 1.1;

    private ImageButton btCurrency;
    private TextView tvCLeft;
    private TextView tvCRight;
    private EditText etMoney;
    private TextView tvConverted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCurrency = findViewById(R.id.btCurrency);
        tvCLeft = findViewById(R.id.tvCLeft);
        tvCRight = findViewById(R.id.tvCRight);
        etMoney = findViewById(R.id.etMoney);
        tvConverted = findViewById(R.id.tvConverted);

        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                convertAndUpdate();
            }
        });

    }

    public void onChangeCurrency(View v) {
        toUsd = !toUsd;
        if(toUsd) {
            tvCLeft.setText("EUR");
            tvCRight.setText("USD");
            etMoney.setHint("1 Euro");
            convertAndUpdate();
        } else {
            tvCLeft.setText("USD");
            tvCRight.setText("EUR");
            etMoney.setHint("1 US-Dollar");
            convertAndUpdate();
        }
    }

    private void convertAndUpdate(){
        double input;
        try {
            input = Integer.parseInt(etMoney.getText().toString());
        } catch(NumberFormatException e) {
            input = 1;
        }

        if(toUsd) {
            double output = input*eurToUsd;
            tvConverted.setText(input + " € are " + Math.round(output*100.0)/100.0 + " $");
            return;
        }
        double output = input/eurToUsd;
        tvConverted.setText(input + " $ are " + Math.round(output*100.0)/100.0 + " €");
    }

}

