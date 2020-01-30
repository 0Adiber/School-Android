package at.htlkaindorf.exa_107_quiz.bl;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import at.htlkaindorf.exa_107_quiz.R;
import at.htlkaindorf.exa_107_quiz.beans.QuizQuestion;

public class QuestionPool {
    private Map<String, List<QuizQuestion>> pool = new HashMap<>();

    public QuestionPool(Resources res) {
        pool.put("Sport", parseCSV("sport", res));
        pool.put("PC Components", parseCSV("pc_comp", res));
    }

    public List<QuizQuestion> getQuestionsByCategory(String category) {
        return pool.get(category);
    }

    public Set<String> getQuestionCategories() {
        return pool.keySet();
    }

    private List<QuizQuestion> parseCSV(String cat, Resources resources) {
        List<QuizQuestion> pool = new ArrayList<>();
        InputStream stream = null;
        if (cat.equalsIgnoreCase("sport")) {
            stream = resources.openRawResource(R.raw.sport);
        } else if (cat.equalsIgnoreCase("pc_comp")) {
            stream = resources.openRawResource(R.raw.pc_components);
        }
        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(streamReader);
        try {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                String[] answers = parts[1].split(",");
                List<String> answerList = new ArrayList<>(Arrays.asList(answers));
                int correct = Integer.parseInt(parts[2]);

                pool.add(new QuizQuestion(parts[0], answerList, correct));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pool;
    }

}
