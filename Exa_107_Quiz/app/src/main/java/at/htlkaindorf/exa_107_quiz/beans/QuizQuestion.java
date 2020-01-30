package at.htlkaindorf.exa_107_quiz.beans;

import java.util.List;

public class QuizQuestion {
    private String question;
    private List<String> answers;
    private int correctAnswer;

    public QuizQuestion(String question, List<String> answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
