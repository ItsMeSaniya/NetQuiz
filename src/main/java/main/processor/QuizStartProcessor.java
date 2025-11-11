package main.processor;

import main.model.Message;
import main.model.Quiz;
import main.network.PeerConnection;

import java.util.function.Consumer;

/**
 * Processor for QUIZ_START message type
 * Handles quiz start notifications and displays quiz UI
 */
public class QuizStartProcessor implements MessageProcessor {
    private final Consumer<Quiz> quizStarter;
    private final Consumer<String> statusLogger;
    
    /**
     * Constructor
     * @param quizStarter Callback to start quiz UI (receives Quiz object)
     * @param statusLogger Callback to log status messages
     */
    public QuizStartProcessor(Consumer<Quiz> quizStarter, Consumer<String> statusLogger) {
        this.quizStarter = quizStarter;
        this.statusLogger = statusLogger;
    }
    
    @Override
    public void process(Message message, PeerConnection connection) {
        Quiz quiz = message.getQuizData();
        if (quiz != null) {
            statusLogger.accept("[QUIZ] Started: " + quiz.getTitle() + " (" + 
                quiz.getQuestions().size() + " questions)");
            quizStarter.accept(quiz);
        }
    }
    
    @Override
    public Message.MessageType getHandledType() {
        return Message.MessageType.QUIZ_START;
    }
}
