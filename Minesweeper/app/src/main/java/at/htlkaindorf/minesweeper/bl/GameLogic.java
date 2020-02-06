package at.htlkaindorf.minesweeper.bl;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import androidx.core.content.ContextCompat;
import at.htlkaindorf.minesweeper.MyBounceInterpolator;
import at.htlkaindorf.minesweeper.R;

public class GameLogic {

    private static HashMap<Integer, Field> field = new HashMap<>();
    public static boolean start = false, gameOver = false;
    public static int bombsLeft = 10;

    public static void generateField(int first, Button[] buttons) {

        Random rand = new Random();

        int setBombs = 0;

        List<Integer> noBombId = new ArrayList<>();

        for(int i = -1; i<=1; i++) {
            for(int j = -1; j<=1; j++) {
                int tmp = first + (i*10) + j;
                noBombId.add(tmp);
            }
        }

        while (setBombs < bombsLeft) {
            int id = rand.nextInt(9) * 10 + rand.nextInt(9);
            if (!noBombId.contains(id)) {
                if (field.containsKey(id))
                    continue;

                field.put(id, new Field(true, false, 0, id));

                for (Button b : buttons) {
                    if (b.getId() == id) {
                        setBombs++;
                    }
                }

            }
        }

        for(Button b : buttons) {
            if(!field.containsKey(b.getId())) {
                field.put(b.getId(), new Field(false, false, getBombsAround(b.getId()), b.getId()));
            }
        }

        //do after other, so that no null pointer and also no indexoutofboundy by recursiveNull()
        for(Button b : buttons) {
            if (b.getId() == first) {
                //call this, to do the click
                shortClick(b, buttons);
            }
        }

        start = true;
    }

    public static void reset(Button[] buttons) {
        field.clear();
        start = false;
        bombsLeft = 10;
        for(Button b : buttons) {
            b.setText("");
            b.setClickable(true);
            b.setBackgroundResource(android.R.drawable.btn_default);
            b.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

            final Animation anim = AnimationUtils.loadAnimation(b.getContext(), R.anim.bounce);

            MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
            anim.setInterpolator(interpolator);
            b.startAnimation(anim);
        }
        gameOver = false;
    }

    public static int shortClick(Button bt, Button[] buttons) {
        if(gameOver)
            return -1;
        if(field.get(bt.getId()).isFlagged()) { //do nothing, because there is flag on it
            return -1;
        } else if(field.get(bt.getId()).isDiscovered()) { //field was already discovered, so do nothing
            return -1;
        } else if(field.get(bt.getId()).isMine()) { //clicked on bomb
            //uncover mines
            bt.setBackground(ContextCompat.getDrawable(bt.getContext(), R.drawable.bomb));
            bt.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            for(Button b : buttons) {
                if(field.get(b.getId()).isMine()) {
                    b.setBackground(ContextCompat.getDrawable(b.getContext(), R.drawable.bomb));
                }
                b.setClickable(false);
            }
            gameOver = true;

            return 1;
        } else if(field.get(bt.getId()).getMinesAround() == 0) { //clicked on zero
                recursiveNull2(bt.getId(), buttons, 0);
            return 3;
        } else { //it is a "normal" button
            bt.setText("" + field.get(bt.getId()).getMinesAround());
            bt.setTextColor((field.get(bt.getId()).getMinesAround()==1 ? Color.BLUE : (field.get(bt.getId()).getMinesAround()==2?Color.GREEN : Color.RED)));
            field.get(bt.getId()).setDiscovered(true);
        }

        return -1;
    }

    public static int longClick(Button bt) { //FLAG
        if(gameOver || !start)
            return bombsLeft;

        if(field == null)
            return bombsLeft;

        if(field.get(bt.getId()).isDiscovered())
            return bombsLeft;

        if(!field.get(bt.getId()).isFlagged()) {
            if(bombsLeft > 0) {
                bt.setBackground(ContextCompat.getDrawable(bt.getContext(), R.drawable.flag));
                field.get(bt.getId()).setFlagged(true);
                return --bombsLeft;
            }
            return bombsLeft;
        }
        else {
            bt.setBackgroundResource(android.R.drawable.btn_default);
            field.get(bt.getId()).setFlagged(false);
            return ++bombsLeft;
        }
    }

    private static int getBombsAround(int id) {
        int bombs = 0;

        for(int i = -1; i<=1; i++) {
            for(int j = -1; j<=1; j++) {
                int tmp = id + (i*10) + j;
                if(!field.containsKey(tmp))
                    continue;
                if(field.get(tmp).isMine())
                    bombs++;
            }
        }

        return bombs;
    }


    //DONT LOOK AT THIS (it does not work, because with threads there, for some reason, appear a lot of null pointer exceptions and index out of bounds)
    private static void recursiveNull(int id, Button[] buttons) {

        field.get(id).setDiscovered(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(Button b : buttons) {
                    if(b.getId() == id) {
                        b.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));

                        final Animation anim = AnimationUtils.loadAnimation(b.getContext(), R.anim.bounce);

                        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                        anim.setInterpolator(interpolator);
                        b.startAnimation(anim);

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }

                        for(int i = -1; i<=1; i++) {
                            for(int j = -1; j<=1; j++) {
                                int tmp = id + (i*10) + j;
                                if(!field.containsKey(tmp))
                                    continue;
                                if(!field.get(tmp).isDiscovered() && !field.get(tmp).isMine() && !field.get(tmp).isFlagged()) {
                                    if(field.get(tmp).getMinesAround() == 0)
                                        recursiveNull(tmp, buttons);
                                    else {
                                        for(Button tb : buttons) {
                                            if(tb.getId() == tmp) {
                                                tb.startAnimation(anim);
                                                tb.setText("" + field.get(tmp).getMinesAround());   //here index out of bounds
                                                tb.setTextColor((tb.getText() == "1" ? Color.BLUE : (tb.getText() == "2" ? Color.GREEN : Color.RED)));
                                                field.get(tmp).setDiscovered(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }).start();

    }

    private static void recursiveNull2(int id, Button[] buttons, int wave) {

        field.get(id).setDiscovered(true);

        for(Button b : buttons) {
            if(b.getId() == id) {
                if(field.get(id).getMinesAround() == 0) {
                    b.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                    final Animation anim = AnimationUtils.loadAnimation(b.getContext(), R.anim.bounce);

                    MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                    anim.setInterpolator(interpolator);
                    anim.setStartOffset(10*wave);
                    b.startAnimation(anim);
                    for(int i = -1; i<=1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int tmp = id + (i * 10) + j;
                            if (!field.containsKey(tmp))
                                continue;
                            if(!field.get(tmp).isDiscovered() && !field.get(tmp).isMine() && !field.get(tmp).isFlagged()) {
                                recursiveNull2(tmp, buttons, wave++);
                            }
                        }
                    }
                }
                else {
                    b.setText("" + field.get(b.getId()).getMinesAround());
                    b.setTextColor((field.get(b.getId()).getMinesAround()==1 ? Color.BLUE : (field.get(b.getId()).getMinesAround()==2?Color.GREEN : Color.RED)));
                    field.get(b.getId()).setDiscovered(true);
                    final Animation anim = AnimationUtils.loadAnimation(b.getContext(), R.anim.bounce);

                    MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                    anim.setInterpolator(interpolator);
                    anim.setStartOffset(10*wave);
                    b.startAnimation(anim);
                }
            }
        }

    }

    public static boolean checkIfWon() {
        for(Field f : field.values()) {
            if(!f.isDiscovered() && !f.isFlagged())
                return false;
        }

        return true;
    }

}
