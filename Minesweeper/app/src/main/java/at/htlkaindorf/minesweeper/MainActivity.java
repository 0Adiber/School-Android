package at.htlkaindorf.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;
import at.htlkaindorf.minesweeper.bl.GameLogic;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;

    private Button[] buttons = new Button[81];

    private ImageButton btReset;
    private TextView tvBombs;
    private Chronometer tvTimer;

    private MediaPlayer audio = new MediaPlayer();
    private MediaPlayer sfx = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audio = MediaPlayer.create(getApplicationContext(), R.raw.bouncing_seals);
        audio.setLooping(true);

        gridLayout = findViewById(R.id.glField);
        btReset = findViewById(R.id.btReset);
        tvBombs = findViewById(R.id.tvBombs);
        tvTimer = findViewById(R.id.tvTimer);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Button bt = new Button(this);
                bt.setId(i*10 + j);
                gridLayout.addView(bt);
                ViewGroup.LayoutParams params = bt.getLayoutParams();
                params.width = width / 9;
                params.height = width / 9;
                bt.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

                bt.setOnClickListener(v -> {

                    final Animation anim = AnimationUtils.loadAnimation(this, R.anim.bounce);

                    MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                    anim.setInterpolator(interpolator);
                    bt.startAnimation(anim);

                    if(GameLogic.start) {
                        int res = GameLogic.shortClick(bt, buttons);
                        if(res == 1) {
                            sfx = MediaPlayer.create(getApplicationContext(), R.raw.bomb);
                            sfx.start();
                        }
                    }

                    else {
                        GameLogic.generateField(bt.getId(), buttons);
                        tvTimer.setBase(SystemClock.elapsedRealtime());
                        tvTimer.start();
                        audio.seekTo(0);
                        audio.start();
                    }

                    boolean won = GameLogic.checkIfWon();
                    if(won|| GameLogic.gameOver) {
                        audio.pause();
                        tvTimer.stop();
                        if(won && !sfx.isPlaying()) {
                            sfx = MediaPlayer.create(getApplicationContext(), R.raw.won);
                            sfx.start();
                        }
                    }

                });

                bt.setOnLongClickListener(v -> {
                    tvBombs.setText("" + GameLogic.longClick(bt));
                    return false;
                });

                buttons[i*10+j-i] = bt;
            }
        }

        GameLogic.reset(buttons);
        tvBombs.setText("" + GameLogic.bombsLeft);

        btReset.setOnClickListener(v -> {
            GameLogic.reset(buttons);
            tvBombs.setText("" + GameLogic.bombsLeft);
            tvTimer.setBase(SystemClock.elapsedRealtime());
            tvTimer.stop();
            audio.pause();
            sfx.stop();
        });

        tvTimer.setOnChronometerTickListener(v -> {
            tvTimer = v;
        });

    }

}
