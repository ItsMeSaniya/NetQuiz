package main.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Quiz answer submission from client
 */
public class QuizAnswer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String quizId;
    private String username;
    private Map<Integer, Integer> answers; // questionIndex -> answerIndex
    private long submissionTime;
    
    public QuizAnswer(String quizId, String username) {
        this.quizId = quizId;
        this.username = username;
        this.answers = new HashMap<>();
        this.submissionTime = System.currentTimeMillis();
    }
    
    public void addAnswer(int questionIndex, int answerIndex) {
        answers.put(questionIndex, answerIndex);
    }
    
    // Getters
    public String getQuizId() {
        return quizId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public Map<Integer, Integer> getAnswers() {
        return answers;
    }
    
    public long getSubmissionTime() {
        return submissionTime;
    }
}
