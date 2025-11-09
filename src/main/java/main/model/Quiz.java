package main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Quiz model for real-time quiz functionality
 */
public class Quiz implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String quizId;
    private String title;
    private List<QuizQuestion> questions;
    private long startTime;
    private int duration; // in seconds
    private boolean active;
    
    public Quiz(String title, int duration) {
        this.quizId = "QUIZ_" + System.currentTimeMillis();
        this.title = title;
        this.questions = new ArrayList<>();
        this.duration = duration;
        this.active = false;
    }
    
    public void addQuestion(QuizQuestion question) {
        questions.add(question);
    }
    
    public void start() {
        this.startTime = System.currentTimeMillis();
        this.active = true;
    }
    
    public void stop() {
        this.active = false;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public boolean isExpired() {
        if (!active) return false;
        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
        return elapsed > duration;
    }
    
    // Getters and Setters
    public String getQuizId() {
        return quizId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public List<QuizQuestion> getQuestions() {
        return questions;
    }
    
    public long getStartTime() {
        return startTime;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public int getRemainingTime() {
        if (!active) return 0;
        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
        return Math.max(0, duration - (int)elapsed);
    }
    
    public String getId() {
        return quizId;
    }
    
    public int getTotalPoints() {
        int total = 0;
        for (QuizQuestion q : questions) {
            total += q.getPoints();
        }
        return total;
    }
    
    public QuizResult gradeQuiz(QuizAnswer answer) {
        int correctAnswers = 0;
        int earnedPoints = 0;
        
        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = questions.get(i);
            Integer userAnswer = answer.getAnswers().get(i);
            
            if (userAnswer != null && question.isCorrect(userAnswer)) {
                correctAnswers++;
                earnedPoints += question.getPoints();
            }
        }
        
        return new QuizResult(quizId, answer.getUsername(), 
            questions.size(), correctAnswers, getTotalPoints(), earnedPoints);
    }
}
