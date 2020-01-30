package at.htlkaindorf.helloWorld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private Button btContinue;
    private TextView tvMessage;
    public static final String TAG = MainActivity.class.getSimpleName();
    private boolean firstClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get instances of views
        etInput = (EditText) findViewById(R.id.etName);
        btContinue = (Button) findViewById(R.id.btContinue);
        tvMessage = (TextView) findViewById(R.id.tvMessage);

        //create event handler for onclick-event
        btContinue.setEnabled(false);
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstClick) {
                    tvMessage.setText(getString(R.string.output_msg, etInput.getText().toString()));
                    etInput.setVisibility(View.INVISIBLE);
                    btContinue.setText(R.string.finished);
                    firstClick = false;
                } else {
                    finish();
                }
            }
        });

        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btContinue.setEnabled(s.length() > 0);
            }
        });

    }
}
