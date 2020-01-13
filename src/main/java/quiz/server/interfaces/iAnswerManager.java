package quiz.server.interfaces;

import quiz.server.model.Question;
import quiz.server.model.Quiz;

import javax.websocket.Session;

public interface iAnswerManager {

    void setAnswerCorrect(Quiz targetQuiz, String Answer, Session session);

    void setAnswerWrong(Quiz targetQuiz, String Answer, Session session);

    void checkWhoAnswered(Quiz targetQuiz, String Answer, Session session, Question AskedQuestion);
}
