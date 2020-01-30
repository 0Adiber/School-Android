package at.htlkaindorf.exa_107_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import at.htlkaindorf.exa_107_quiz.beans.QuizQuestion;
import at.htlkaindorf.exa_107_quiz.bl.QuestionPool;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private Random rand = new Random();

    private QuestionPool pool;

    private Object arr[];
    private String category;

    private List<QuizQuestion> questions;
    private int currentQ = 0;
    private int rightAnswer;

    private Button btWeiter;
    private Button btQuestion;

    private Button answer[] = new Button[4];
    private Button status[] = new Button[5];

    private int correctAnswered = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pool = new QuestionPool(getResources());

        btWeiter = findViewById(R.id.btWeiter);
        btQuestion = findViewById(R.id.btQuestion);

        answer[0] = findViewById(R.id.btAnswer1);
        answer[1] = findViewById(R.id.btAnswer2);
        answer[2] = findViewById(R.id.btAnswer3);
        answer[3] = findViewById(R.id.btAnswer4);

        status[0] = findViewById(R.id.bt1);
        status[1] = findViewById(R.id.bt2);
        status[2] = findViewById(R.id.bt3);
        status[3] = findViewById(R.id.bt4);
        status[4] = findViewById(R.id.bt5);


        for(int i = 0; i<answer.length; i++) {
            final int temp = i;
            answer[temp].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAnswerClick(temp);
                }
            });
        }

        btWeiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDisplayQuestion();
            }
        });

        onInitiate();
    }

    private void onInitiate() {
        findViewById(R.id.bt1).getBackground().setTint(Color.LTGRAY);
        findViewById(R.id.bt2).getBackground().setTint(Color.LTGRAY);
        findViewById(R.id.bt3).getBackground().setTint(Color.LTGRAY);
        findViewById(R.id.bt4).getBackground().setTint(Color.LTGRAY);
        findViewById(R.id.bt5).getBackground().setTint(Color.LTGRAY);

        arr = pool.getQuestionCategories().toArray();
        category = (arr[rand.nextInt(arr.length)]).toString();
        questions = pool.getQuestionsByCategory(category);

        ((TextView)findViewById(R.id.tvCategory)).setText("Kategorie: " + category);
        currentQ = 0;

        onDisplayQuestion();
    }

    private void onDisplayQuestion() {
        if(currentQ>4) {
            btWeiter.setText("Weiter");
            correctAnswered = 0;
            onInitiate();
            return;
        }
        QuizQuestion q = questions.get(currentQ);
        rightAnswer = q.getCorrectAnswer();
        btWeiter.setEnabled(false);
        btQuestion.setText(q.getQuestion());

        for(int i = 0; i<4; i++) {
            answer[i].setText(q.getAnswers().get(i));
            ViewCompat.setBackgroundTintList(answer[i], ColorStateList.valueOf(Color.BLACK));
        }

        for(int i = 0; i<answer.length; i++){
            answer[i].setEnabled(true);
        }
    }

    private void onAnswerClick(int id){

        for(int i = 0; i<answer.length; i++){
            answer[i].setEnabled(false);
        }

        if(id == rightAnswer) {
            ViewCompat.setBackgroundTintList(status[currentQ], ColorStateList.valueOf(Color.GREEN));

            ViewCompat.setBackgroundTintList(answer[id], ColorStateList.valueOf(Color.GREEN));
            correctAnswered++;
        } else {
            ViewCompat.setBackgroundTintList(status[currentQ], ColorStateList.valueOf(Color.RED));

            ViewCompat.setBackgroundTintList(answer[id], ColorStateList.valueOf(Color.RED));
            ViewCompat.setBackgroundTintList(answer[rightAnswer], ColorStateList.valueOf(Color.BLUE));
        }
        currentQ++;
        if(currentQ>4) {
            btWeiter.setText("You answered " + correctAnswered + "/5 Answers correct!\nClick to play again.");
        }
        btWeiter.setEnabled(true);
    }

}
