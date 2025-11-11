package main.controller;

import main.model.QuizResult;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Manager for leaderboard and scoring
 * Follows Single Responsibility Principle
 */
public class LeaderboardManager {
    private final Map<String, List<QuizResult>> userResults;
    
    public LeaderboardManager() {
        this.userResults = new HashMap<>();
    }
    
    /**
     * Add a quiz result for a user
     * @param username The username
     * @param result The quiz result
     */
    public void addResult(String username, QuizResult result) {
        userResults.computeIfAbsent(username, k -> new ArrayList<>()).add(result);
    }
    
    /**
     * Get all results for a user
     * @param username The username
     * @return List of quiz results
     */
    public List<QuizResult> getUserResults(String username) {
        return userResults.getOrDefault(username, new ArrayList<>());
    }
    
    /**
     * Get leaderboard sorted by average score
     * @return List of entries [username, averageScore]
     */
    public List<LeaderboardEntry> getLeaderboard() {
        return userResults.entrySet().stream()
            .map(entry -> {
                String username = entry.getKey();
                List<QuizResult> results = entry.getValue();
                
                double avgScore = results.stream()
                    .mapToDouble(QuizResult::getPercentage)
                    .average()
                    .orElse(0.0);
                
                int totalQuizzes = results.size();
                int totalScore = results.stream()
                    .mapToInt(QuizResult::getEarnedPoints)
                    .sum();
                
                return new LeaderboardEntry(username, avgScore, totalQuizzes, totalScore);
            })
            .sorted(Comparator.comparingDouble(LeaderboardEntry::getAverageScore).reversed())
            .collect(Collectors.toList());
    }
    
    /**
     * Get formatted leaderboard string
     * @return Formatted leaderboard text
     */
    public String getFormattedLeaderboard() {
        List<LeaderboardEntry> leaderboard = getLeaderboard();
        
        if (leaderboard.isEmpty()) {
            return "No quiz results yet";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== LEADERBOARD ===\n\n");
        sb.append(String.format("%-5s %-20s %-10s %-10s %-10s\n", 
            "Rank", "Username", "Avg Score", "Quizzes", "Total Pts"));
        sb.append("─".repeat(60)).append("\n");
        
        int rank = 1;
        for (LeaderboardEntry entry : leaderboard) {
            sb.append(String.format("%-5d %-20s %-10.1f%% %-10d %-10d\n",
                rank++,
                entry.getUsername(),
                entry.getAverageScore(),
                entry.getTotalQuizzes(),
                entry.getTotalScore()));
        }
        
        return sb.toString();
    }
    
    /**
     * Get user's rank
     * @param username The username
     * @return Rank (1-based) or -1 if not found
     */
    public int getUserRank(String username) {
        List<LeaderboardEntry> leaderboard = getLeaderboard();
        for (int i = 0; i < leaderboard.size(); i++) {
            if (leaderboard.get(i).getUsername().equals(username)) {
                return i + 1;
            }
        }
        return -1;
    }
    
    /**
     * Clear all results
     */
    public void clearResults() {
        userResults.clear();
    }
    
    /**
     * Inner class for leaderboard entry
     */
    public static class LeaderboardEntry {
        private final String username;
        private final double averageScore;
        private final int totalQuizzes;
        private final int totalScore;
        
        public LeaderboardEntry(String username, double averageScore, 
                               int totalQuizzes, int totalScore) {
            this.username = username;
            this.averageScore = averageScore;
            this.totalQuizzes = totalQuizzes;
            this.totalScore = totalScore;
        }
        
        public String getUsername() {
            return username;
        }
        
        public double getAverageScore() {
            return averageScore;
        }
        
        public int getTotalQuizzes() {
            return totalQuizzes;
        }
        
        public int getTotalScore() {
            return totalScore;
        }
    }
}
