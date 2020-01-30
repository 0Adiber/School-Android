package at.htlkaindorf.exa_205_game2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import at.htlkaindorf.exa_205_game2048.bl.GameLogic;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons;
    private GameLogic gm;
    private TextView tvPoints;

    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPoints = findViewById(R.id.tvPoints);
        tvPoints.setText("Points: 0");

        buttons = new Button[][] {
                {findViewById(R.id.bt0),findViewById(R.id.bt1),findViewById(R.id.bt2),findViewById(R.id.bt3)},
                {findViewById(R.id.bt4),findViewById(R.id.bt5),findViewById(R.id.bt6),findViewById(R.id.bt7)},
                {findViewById(R.id.bt8),findViewById(R.id.bt9),findViewById(R.id.bt10),findViewById(R.id.bt11)},
                {findViewById(R.id.bt12),findViewById(R.id.bt13),findViewById(R.id.bt14),findViewById(R.id.bt15)},
        };

        SwipeListener mgl = new SwipeListener();

        gestureDetectorCompat = new GestureDetectorCompat(this,mgl);

        gm = new GameLogic();

        for(int i = 0; i<buttons.length;i++) {
            for(int j = 0; j<buttons.length;j++) {
                buttons[i][j].setText(""+gm.getValues()[i][j]);
                buttons[i][j].setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return gestureDetectorCompat.onTouchEvent(event);
                    }
                });
                for(ColorScheme c : ColorScheme.values()) {
                    if(gm.getValues()[i][j] == c.getValue()) {
                        buttons[i][j].setBackgroundTintList(ColorStateList.valueOf(c.getBackgroundColor()));
                        buttons[i][j].setTextColor(c.getFontColor());
                        break;
                    }
                }
            }
        }


        findViewById(R.id.btReset).setOnClickListener(v -> {
            gm.resetGame();
            tvPoints.setText("Points: 0");
            for(int i = 0; i<buttons.length;i++) {
                for(int j = 0; j<buttons.length;j++) {
                    buttons[i][j].setText(""+gm.getValues()[i][j]);
                    for(ColorScheme c : ColorScheme.values()) {
                        if(gm.getValues()[i][j] == c.getValue()) {
                            buttons[i][j].setBackgroundTintList(ColorStateList.valueOf(c.getBackgroundColor()));
                            buttons[i][j].setTextColor(c.getFontColor());
                            break;
                        }
                    }
                }
            }
        });

    }

    private class SwipeListener extends GestureDetector.SimpleOnGestureListener {
        public static final int MIN_DIST = 50;
        public static final int MAX_DIST = 1000;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e1.getX()-e2.getX();
            float deltaY = e1.getY()-e2.getY();
            float deltaXAbs = Math.abs(deltaX);
            float deltaYAbs = Math.abs(deltaY);

            if(deltaXAbs > MIN_DIST && deltaXAbs < MAX_DIST && deltaXAbs > deltaYAbs) {
                if(deltaX > 0){
//                    Log.d(TAG, "Swipe left");

                    gm.makeMove("left");
                } else {
//                    Log.d(TAG, "Swipe right");

                    gm.makeMove("right");
                }

            } else if(deltaYAbs > MIN_DIST && deltaYAbs < MAX_DIST) {
                if(deltaY > 0){
//                    Log.d(TAG, "Swipe up");

                    gm.makeMove("up");
                } else {
//                    Log.d(TAG, "Swipe down");

                    gm.makeMove("down");
                }
            }


            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons.length; j++) {
                    buttons[i][j].setText("" + gm.getValues()[i][j]);
                    for (ColorScheme c : ColorScheme.values()) {
                        if (gm.getValues()[i][j] == c.getValue()) {
                            buttons[i][j].setBackgroundTintList(ColorStateList.valueOf(c.getBackgroundColor()));
                            buttons[i][j].setTextColor(c.getFontColor());
                            break;
                        }
                    }
                }
            }

            tvPoints.setText("Points: " + gm.getPoints());


            return true;
        }
    }

}
