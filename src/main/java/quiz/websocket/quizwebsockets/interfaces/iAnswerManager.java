package quiz.websocket.quizwebsockets.interfaces;

import quiz.websocket.questions.Question;
import quiz.server.model.Quiz;
import quiz.websocket.quizwebsockets.Round;

import javax.websocket.Session;

public interface iAnswerManager {
    void setAnswerCorrect(Quiz targetQuiz, String Answer, Session session, Question AskedQuestion, Round Currentround);

    void setAnswerWrong(Quiz targetQuiz, String Answer, Session session, Question AskedQuestion, Round Currentround);

    void checkWhoAnswered(Quiz targetQuiz, String Answer, Session session, Question AskedQuestion);
}
