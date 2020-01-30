package at.htlkaindorf.exa_106_textfieldformatter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox cbBold;
    private CheckBox cbItalic;
    private SeekBar sbSize;
    private RadioGroup rgFont;
    private EditText etText;
    private TextView tvSize;

    private int currentFontId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbBold = findViewById(R.id.cbBold);
        cbItalic = findViewById(R.id.cbItalic);
        sbSize = findViewById(R.id.sbSize);
        rgFont = findViewById(R.id.rgFont);
        etText = findViewById(R.id.etText);
        tvSize = findViewById(R.id.tvSize);

        onChangeFontStyle cfs = new onChangeFontStyle();
        cbBold.setOnClickListener(cfs);
        cbItalic.setOnClickListener(cfs);

        sbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onChangeFontSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        rgFont.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onChangeFont();
            }
        });
    }

    private void onChangeFont() {
        Typeface font;
        if(rgFont.getCheckedRadioButtonId() == R.id.rbUbuntu) {
            font = ResourcesCompat.getFont(getApplicationContext(), R.font.ubuntu_light);
            etText.setTypeface(font);
            currentFontId=R.font.ubuntu_light;
        } else if(rgFont.getCheckedRadioButtonId() == R.id.rbGrandHotel) {
            font = ResourcesCompat.getFont(getApplicationContext(), R.font.grand_hotel);
            etText.setTypeface(font);
            currentFontId=R.font.grand_hotel;
        } else if(rgFont.getCheckedRadioButtonId() == R.id.rbPermanent) {
            font = ResourcesCompat.getFont(getApplicationContext(), R.font.permanent_marker);
            etText.setTypeface(font);
            currentFontId = R.font.permanent_marker;
        }
    }

    private void onChangeFontSize(int progress) {
        etText.setTextSize((float)progress);
        tvSize.setText("Size: "+progress);
    }

    public class onChangeFontStyle implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Typeface font = Typeface.defaultFromStyle(Typeface.NORMAL);
            if(currentFontId != 0) font = ResourcesCompat.getFont(getApplicationContext(), currentFontId);

            if(cbItalic.isChecked() && cbBold.isChecked()) {
                etText.setTypeface(font, Typeface.BOLD_ITALIC);
            } else if(cbBold.isChecked()) {
                etText.setTypeface(font, Typeface.BOLD);
            } else if(cbItalic.isChecked()) {
                etText.setTypeface(font, Typeface.ITALIC);
            } else {
                etText.setTypeface(font,Typeface.NORMAL);
            }

        }
    }

}
