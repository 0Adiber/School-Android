package at.htlkaindorf.bsp_105_pocketcalculator;

import androidx.appcompat.app.AppCompatActivity;
import at.htlkaindorf.bsp_105_pocketcalculator.bl.Stack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private static Button btDiv;
    private static Button btMul;
    private static Button btSub;
    private static Button btPlus;

    private static TextView tvOutput;

    private static Button btClr;
    private static Button btCom;
    private static ToggleButton btSign;

    private static Button btEnter;

    private static Stack stack = new Stack();

    public static final String TAG = MainActivity.class.getSimpleName();

    private static boolean append = false;
    private static boolean isErg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btDiv = findViewById(R.id.btDiv);
        btMul = findViewById(R.id.btMul);
        btSub = findViewById(R.id.btSub);
        btPlus = findViewById(R.id.btPlus);

        tvOutput = findViewById(R.id.tvOutput);

        btClr = findViewById(R.id.btClr);
        btCom = findViewById(R.id.btCom);
        btSign = findViewById(R.id.btSign);

        btEnter = findViewById(R.id.btEnter);

        btDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperator(v);
            }
        });

        btMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperator(v);
            }
        });

        btSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperator(v);
            }
        });

        btPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperator(v);
            }
        });

        btClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClear();
            }
        });

        btCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onKomma();
            }
        });

        btSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSign(v);
            }
        });

        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEnter();
            }
        });
    }

    public void onDigitClick(View view) {
        isErg = false;
        if(append)
            tvOutput.append(((Button)view).getText().toString());
        else {
            tvOutput.setText(((Button) view).getText().toString());
            append = true;
            btSign.setChecked(false);
        }
    }

    private void onClear() {
        tvOutput.setText("0,0");
        stack.clear();
        btSign.setChecked(false);
        append = false;
    }

    private void onKomma() {
        if(!tvOutput.getText().toString().contains(",")) {
            tvOutput.append(",");
            return;
        }
        Toast.makeText(getApplicationContext(), "Komma schon gesetzt", Toast.LENGTH_SHORT).show();
    }

    private void onSign(View v) {
        if(((ToggleButton)v).isChecked()) {
            if(!tvOutput.getText().toString().equals("0,0"))tvOutput.setText("-" + tvOutput.getText().toString());
            else btSign.setChecked(false);
            return;
        }

        tvOutput.setText(tvOutput.getText().toString().substring(1));
    }

    private void onOperator(View view) {
        if(!isErg) stack.push(Double.parseDouble(tvOutput.getText().toString().replace(",", ".")));
        double num1, num2, erg = 0.0;
        try {
            num1 = stack.pop();
        } catch(RuntimeException e){
            Toast.makeText(getApplicationContext(), "Not enough numbers found", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            num2 = stack.pop();
        } catch(RuntimeException e){
            Toast.makeText(getApplicationContext(), "Not enough numbers found", Toast.LENGTH_SHORT).show();
            stack.push(num1);
            return;
        }

        switch (((Button)view).getText().toString()){
            case "/":
                if(num1==0.0) {Toast.makeText(getApplicationContext(), "Devision by zero", Toast.LENGTH_SHORT).show(); return;}
                erg = num2 / num1;
                break;
            case "*":
                erg = num2*num1;
                break;
            case "+":
                erg = num2+num1;
                break;
            case "-":
                erg = num2-num1;
                break;
        }

        tvOutput.setText(String.valueOf(erg));
        stack.push(erg);
        append = false;
        isErg = true;
    }

    private void onEnter() {
        try {
            stack.push(Double.parseDouble(tvOutput.getText().toString().replace(",", ".")));
            append = false;
            tvOutput.setText("0,0");
            btSign.setChecked(false);
        } catch(RuntimeException e){
            Toast.makeText(getApplicationContext(), "Stack voll", Toast.LENGTH_SHORT).show();
        }
    }

}
