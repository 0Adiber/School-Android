package at.htlkaindorf.exa_108_numberpuzzlegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button[] buttons;
    private Button reset;

    private GestureDetectorCompat gestureDetectorCompat;

    public static final String TAG = MainActivity.class.getSimpleName();

    private Button fromSwipe;

    private int movements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //number buttons
        buttons = new Button[] {
                findViewById(R.id.bt0),
                findViewById(R.id.bt1),
                findViewById(R.id.bt2),
                findViewById(R.id.bt3),
                findViewById(R.id.bt4),
                findViewById(R.id.bt5),
                findViewById(R.id.bt6),
                findViewById(R.id.bt7),
                findViewById(R.id.bt8),
                findViewById(R.id.bt9),
                findViewById(R.id.bt10),
                findViewById(R.id.bt11),
                findViewById(R.id.bt12),
                findViewById(R.id.bt13),
                findViewById(R.id.bt14),
                findViewById(R.id.bt15),
        };

        MyGestureListener mgl = new MyGestureListener();

        gestureDetectorCompat = new GestureDetectorCompat(this,mgl);

        for(Button bt : buttons) {
            bt.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    fromSwipe = (Button)v;
                    return gestureDetectorCompat.onTouchEvent(event);
                }
            });
        }

        //reset button
        reset = findViewById(R.id.btReset);
        //init buttons
        initButtons();

        //onclick for reset
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initButtons();
            }
        });
    }

    public void initButtons() {
        movements = 0;

        int[] colors = {0xff404040,0xff4f7df0,Color.WHITE};
        int[] textC = {Color.BLACK,Color.WHITE,0xff4f7df0};

        List<Integer> nums = new ArrayList<>();

        nums.add(-1);
        for(int i = 1; i<16;i++) {
            nums.add(i);
        }

        Collections.shuffle(nums);

        for(int i = 0; i<16; i++) {
            int c = nums.get(i);

            if(c!=-1) buttons[i].setText(""+c);
            else buttons[i].setText("");

            buttons[i].setBackgroundColor(colors[(c%2)+1]);
            buttons[i].setTextColor(textC[(c%2)+1]);
        }
    }

    public boolean checkGameState() {
        for(int i = 1; i<15;i++) {
            int id = Integer.parseInt(getResources().getResourceEntryName(buttons[i].getId()).substring(2))+1;
            if(!buttons[i].getText().toString().equals(String.valueOf(id)))
                return false;
        }
        return true;
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        public static final int MIN_DIST = 50;
        public static final int MAX_DIST = 1000;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e1.getX()-e2.getX();
            float deltaY = e1.getY()-e2.getY();
            float deltaXAbs = Math.abs(deltaX);
            float deltaYAbs = Math.abs(deltaY);

            Button toSwipe = fromSwipe;

            float tox = e1.getRawX();
            float toy = e1.getRawY();

            if(deltaXAbs > MIN_DIST && deltaXAbs < MAX_DIST && deltaXAbs > deltaYAbs) {
                if(deltaX > 0){
//                    Log.d(TAG, "Swipe left");

                    tox-=fromSwipe.getWidth();
                } else {
//                    Log.d(TAG, "Swipe right");

                    tox+=fromSwipe.getWidth();
                }

            } else if(deltaYAbs > MIN_DIST && deltaYAbs < MAX_DIST) {
                if(deltaY > 0){
//                    Log.d(TAG, "Swipe up");

                    toy-=fromSwipe.getHeight();
                } else {
//                    Log.d(TAG, "Swipe down");
                    toy+=fromSwipe.getHeight();
                }
            }

            for(Button bt : buttons) {

                int[] location = new int[2];
                bt.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];

                if(tox >= x && (tox <= x+bt.getWidth())) {
                    if(toy >= y && (toy <= y+bt.getHeight())) {
                        toSwipe = bt;
                        break;
                    }
                }
            }

            if(toSwipe.getText().toString().equals(fromSwipe.getText().toString())) return true;
            if(!toSwipe.getText().equals("")) return true;

//            Toast.makeText(getApplicationContext(), "From: " + fromSwipe.getText().toString() + " To: " + toSwipe.getText().toString(),Toast.LENGTH_SHORT).show();

            String num = fromSwipe.getText().toString();

            int color = Color.WHITE;
            color = ((ColorDrawable)fromSwipe.getBackground()).getColor();
            int textC = fromSwipe.getCurrentTextColor();

            fromSwipe.setText(toSwipe.getText());
            fromSwipe.setBackgroundColor(((ColorDrawable)toSwipe.getBackground()).getColor());
            fromSwipe.setTextColor(toSwipe.getCurrentTextColor());

            toSwipe.setText(num);
            toSwipe.setBackgroundColor(color);
            toSwipe.setTextColor(textC);

            movements++;

            //check if won
            if(checkGameState()) {
                Toast.makeText(getApplicationContext(), "Gewonnen! Movements: "+movements, Toast.LENGTH_SHORT).show();
            }

            return true;
        }
    }

}
