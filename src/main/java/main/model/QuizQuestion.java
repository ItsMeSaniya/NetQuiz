package main.model;

import java.io.Serializable;
import java.util.List;

/**
 * Quiz question model
 */
public class QuizQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String question;
    private List<String> options;
    private int correctAnswer; // index of correct option (0-based)
    private int points;
    
    public QuizQuestion(String question, List<String> options, int correctAnswer, int points) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }
    
    public boolean isCorrect(int answer) {
        return answer == correctAnswer;
    }
    
    // Getters
    public String getQuestion() {
        return question;
    }
    
    public List<String> getOptions() {
        return options;
    }
    
    public int getCorrectAnswer() {
        return correctAnswer;
    }
    
    public int getPoints() {
        return points;
    }
}
