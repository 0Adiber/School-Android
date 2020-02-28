package at.htlkaindorf.bsp_development;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import at.htlkaindorf.bsp_development.collections.Person;
import at.htlkaindorf.bsp_development.collections.Student1;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;

    private GestureDetectorCompat gestureDetectorCompat;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager assetManager = getAssets();
        try {
            InputStream in = assetManager.open("daten.csv");
            List<Person> persons = new BufferedReader(new InputStreamReader(in))
                                    .lines()
                                    .skip(1) //erste zeile skippen, weil überschriftszeile
                                    .map(Person::new) //wandelt in person objekte um
                                    .collect(Collectors.toList()); //wandelt es in liste um
        } catch (IOException e) {
            Log.e(TAG,e.toString());
        }

        Intent intent = new Intent(this, TestActivity.class);
        double val = 1/3.;
        intent.putExtra("value", val);
        Student1 student = new Student1("y", 1, LocalDate.now(), Uri.parse("https://google.at"));
        intent.putExtra("student",student);
        startActivity(intent);

//        bt1 = findViewById(R.id.bt1);
//        bt2 = findViewById(R.id.bt2);
//        bt3 = findViewById(R.id.bt3);
//        bt4 = findViewById(R.id.bt4);
//        bt5 = findViewById(R.id.bt5);
//
//        MyClickListener mcl = new MyClickListener();
//        bt1.setOnClickListener(mcl);
//        bt2.setOnClickListener(mcl);
//        bt3.setOnClickListener(mcl);
//        bt3.setOnLongClickListener(this);
//
//        bt5.setOnClickListener( v -> Toast.makeText(getApplicationContext(), "Second Method for event handling",Toast.LENGTH_SHORT).show());
//
//        bt5.setOnClickListener(this::onButton5Click); //übergabeparameter muss passen & void methode
//
//        bt5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Second Method for event handling",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        bt5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onButton5Click(v);
//            }
//        });
//
//        MyGestureListener mgl = new MyGestureListener();
//
//        gestureDetectorCompat = new GestureDetectorCompat(this,mgl);
//
//        bt5.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return gestureDetectorCompat.onTouchEvent(event);
//            }
//        });


//        Picasso.with(this)
//                .load(Uri.fromFile(""))
//                .placeholder(R.drawable.ic_launcher_background)
//                .resize(200,200)
//                .into(ivPicture);


    }

    private void onButton5Click(View v) {
        Toast.makeText(getApplicationContext(), "Button 5 Clicked (still second)",Toast.LENGTH_SHORT).show();
    }

    public void onButton4Click(View v) {
        Toast.makeText(getApplicationContext(), "3te Methode für Event handling", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(getApplicationContext(), "Long Click", Toast.LENGTH_SHORT).show();
        return false;
    }

    public class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button bt = (Button)v;
            Toast.makeText(getApplicationContext(), "Button " + bt.getText(),Toast.LENGTH_SHORT).show();
        }
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        public static final int MIN_DIST = 100;
        public static final int MAX_DIST = 1000;
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e1.getX()-e2.getX();
            float deltaY = e1.getY()-e2.getY();
            float deltaXAbs = Math.abs(deltaX);
            float deltaYAbs = Math.abs(deltaY);

            if(deltaXAbs > MIN_DIST && deltaXAbs < MAX_DIST) {
                if(deltaX > 0){
                    Log.d(TAG, "Swipe left");
                } else {
                    Log.d(TAG, "Swipe right");
                }
            }

            if(deltaYAbs > MIN_DIST && deltaYAbs < MAX_DIST) {
                if(deltaY > 0){
                    Log.d(TAG, "Swipe up");
                } else {
                    Log.d(TAG, "Swipe down");
                }
            }

            return true;
        }
    }

}
