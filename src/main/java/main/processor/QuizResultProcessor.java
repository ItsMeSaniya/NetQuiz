package main.processor;

import main.model.Message;
import main.model.QuizResult;
import main.network.PeerConnection;

import java.util.function.Consumer;

/**
 * Processor for QUIZ_RESULT message type
 * Handles quiz result notifications from admin
 */
public class QuizResultProcessor implements MessageProcessor {
    private final Consumer<QuizResult> resultHandler;
    private final Consumer<String> statusLogger;
    
    /**
     * Constructor
     * @param resultHandler Callback to handle quiz result (QuizResult object)
     * @param statusLogger Callback to log status messages
     */
    public QuizResultProcessor(Consumer<QuizResult> resultHandler, Consumer<String> statusLogger) {
        this.resultHandler = resultHandler;
        this.statusLogger = statusLogger;
    }
    
    @Override
    public void process(Message message, PeerConnection connection) {
        QuizResult result = message.getQuizResult();
        if (result != null) {
            statusLogger.accept("[QUIZ] Result: " + result.getCorrectAnswers() + "/" + 
                result.getTotalQuestions() + " (" + 
                String.format("%.1f%%", result.getPercentage()) + ")");
            resultHandler.accept(result);
        }
    }
    
    @Override
    public Message.MessageType getHandledType() {
        return Message.MessageType.QUIZ_RESULT;
    }
}
