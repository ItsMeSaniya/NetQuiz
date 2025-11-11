package main.controller;

import main.model.Message;
import main.model.Quiz;
import main.model.QuizAnswer;
import main.model.QuizResult;
import main.model.User;
import main.network.Client;
import main.network.PeerConnection;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Controller for quiz operations
 * Follows Single Responsibility Principle
 */
public class QuizController {
    private final User currentUser;
    private final List<Client> connectedPeers;
    private final Map<String, PeerConnection> peerConnections;
    private final Consumer<String> chatLogger;
    
    private Quiz activeQuiz;
    
    public QuizController(User currentUser,
                         List<Client> connectedPeers,
                         Map<String, PeerConnection> peerConnections,
                         Consumer<String> chatLogger) {
        this.currentUser = currentUser;
        this.connectedPeers = connectedPeers;
        this.peerConnections = peerConnections;
        this.chatLogger = chatLogger;
    }
    
    /**
     * Start a quiz (admin only)
     * @param quiz The quiz to start
     */
    public void startQuiz(Quiz quiz) {
        if (!isUserAdmin()) {
            chatLogger.accept("[ERROR] Only admin can start quizzes");
            return;
        }
        
        this.activeQuiz = quiz;
        
        Message quizMessage = new Message(
            currentUser.getUsername(),
            "ALL",
            "Quiz Started: " + quiz.getTitle(),
            Message.MessageType.QUIZ_START
        );
        quizMessage.setQuizData(quiz);
        
        // Broadcast quiz to all peers
        for (PeerConnection peer : peerConnections.values()) {
            peer.sendMessage(quizMessage);
        }
        
        chatLogger.accept("[QUIZ] Started: " + quiz.getTitle() + " (" + 
            quiz.getQuestions().size() + " questions)");
    }
    
    /**
     * Submit quiz answer
     * @param answer The quiz answer
     */
    public void submitQuizAnswer(QuizAnswer answer) {
        if (connectedPeers.isEmpty()) {
            chatLogger.accept("[ERROR] Not connected to server");
            return;
        }
        
        Message answerMessage = new Message(
            currentUser.getUsername(),
            "admin",
            "Quiz Answer Submitted",
            Message.MessageType.QUIZ_ANSWER
        );
        answerMessage.setQuizAnswer(answer);
        
        connectedPeers.get(0).sendMessage(answerMessage);
        
        chatLogger.accept("[QUIZ] Answer submitted for question " + 
            (answer.getAnswers().size()));
    }
    
    /**
     * Send quiz results to a user
     * @param targetUser The target user
     * @param result The quiz result
     */
    public void sendQuizResult(String targetUser, QuizResult result) {
        if (!isUserAdmin()) {
            chatLogger.accept("[ERROR] Only admin can send results");
            return;
        }
        
        PeerConnection targetConnection = peerConnections.get(targetUser);
        if (targetConnection == null) {
            chatLogger.accept("[ERROR] User not found: " + targetUser);
            return;
        }
        
        Message resultMessage = new Message(
            currentUser.getUsername(),
            targetUser,
            "Quiz Results",
            Message.MessageType.QUIZ_RESULT
        );
        resultMessage.setQuizResult(result);
        
        targetConnection.sendMessage(resultMessage);
        
        chatLogger.accept("[QUIZ] Sent results to " + targetUser + ": " + 
            result.getCorrectAnswers() + "/" + result.getTotalQuestions());
    }
    
    /**
     * Get the currently active quiz
     * @return Active quiz or null
     */
    public Quiz getActiveQuiz() {
        return activeQuiz;
    }
    
    /**
     * Set the active quiz (when received from server)
     * @param quiz The quiz
     */
    public void setActiveQuiz(Quiz quiz) {
        this.activeQuiz = quiz;
        chatLogger.accept("[QUIZ] Received: " + quiz.getTitle() + " (" + 
            quiz.getQuestions().size() + " questions)");
    }
    
    /**
     * Check if current user is admin
     * @return true if admin
     */
    private boolean isUserAdmin() {
        return currentUser.getUsername().equalsIgnoreCase("admin") && 
               currentUser.getPassword().equals("admin");
    }
}
