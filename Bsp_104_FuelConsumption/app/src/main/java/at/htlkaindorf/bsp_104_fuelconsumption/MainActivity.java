package at.htlkaindorf.bsp_104_fuelconsumption;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btCalculate;
    private EditText etFuel;
    private EditText etDistance;
    private TextView tvConsumption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCalculate = findViewById(R.id.btCalculate);
        etFuel = findViewById(R.id.etFuel);
        etDistance = findViewById(R.id.etDistance);
        tvConsumption = findViewById(R.id.tvConsumption);

        btCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCalcConsumption();
            }
        });

    }

    private void onCalcConsumption() {
        try {
            double fuel = Double.parseDouble(etFuel.getText().toString());
            int dist = Integer.parseInt(etDistance.getText().toString());
            double res = fuel/dist*100;
            tvConsumption.setText("Consumption: " + res + " l/km");
            if(res<10) {
                tvConsumption.setTextColor(Color.GREEN);
            } else {
                tvConsumption.setTextColor(Color.RED);
            }
        } catch(NumberFormatException e) {
            tvConsumption.setText("Illegal Input");
        }
    }
}
