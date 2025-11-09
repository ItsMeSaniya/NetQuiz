package main.model;

import java.io.Serializable;

/**
 * Quiz result/score for a participant
 */
public class QuizResult implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String quizId;
    private String username;
    private int totalQuestions;
    private int correctAnswers;
    private int totalPoints;
    private int earnedPoints;
    private double percentage;
    private long completionTime;
    
    public QuizResult(String quizId, String username, int totalQuestions, 
                      int correctAnswers, int totalPoints, int earnedPoints) {
        this.quizId = quizId;
        this.username = username;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.totalPoints = totalPoints;
        this.earnedPoints = earnedPoints;
        this.percentage = (totalQuestions > 0) ? 
            (correctAnswers * 100.0 / totalQuestions) : 0;
        this.completionTime = System.currentTimeMillis();
    }
    
    // Getters
    public String getQuizId() {
        return quizId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public int getTotalQuestions() {
        return totalQuestions;
    }
    
    public int getCorrectAnswers() {
        return correctAnswers;
    }
    
    public int getTotalPoints() {
        return totalPoints;
    }
    
    public int getEarnedPoints() {
        return earnedPoints;
    }
    
    public double getPercentage() {
        return percentage;
    }
    
    public long getCompletionTime() {
        return completionTime;
    }
    
    public String getGrade() {
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 50) return "D";
        return "F";
    }
}
