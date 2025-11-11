package main.processor;

import main.model.Message;
import main.model.QuizAnswer;
import main.network.PeerConnection;

import java.util.function.BiConsumer;

/**
 * Processor for QUIZ_ANSWER message type
 * Handles quiz answer submissions from clients
 */
public class QuizAnswerProcessor implements MessageProcessor {
    private final BiConsumer<QuizAnswer, String> answerHandler;
    
    /**
     * Constructor
     * @param answerHandler Callback to handle quiz answer (answer, username)
     */
    public QuizAnswerProcessor(BiConsumer<QuizAnswer, String> answerHandler) {
        this.answerHandler = answerHandler;
    }
    
    @Override
    public void process(Message message, PeerConnection connection) {
        QuizAnswer answer = message.getQuizAnswer();
        if (answer != null) {
            String username = message.getSender();
            answerHandler.accept(answer, username);
        }
    }
    
    @Override
    public Message.MessageType getHandledType() {
        return Message.MessageType.QUIZ_ANSWER;
    }
}
