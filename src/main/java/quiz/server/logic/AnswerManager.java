package quiz.server.logic;

import quiz.server.model.Answer;
import quiz.server.model.Quiz;
import quiz.server.model.Question;
import quiz.server.interfaces.iAnswerManager;

import javax.websocket.Session;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class AnswerManager implements iAnswerManager {

    @Override
    public void setAnswerCorrect(Quiz targetQuiz, String Answer, Session session) {
        targetQuiz.getCurrentRound().setAnswer(new Answer(session, Answer, targetQuiz.getCurrentQuestion(), true), targetQuiz, session);

        //targetQuiz.setPoints(session, Currentround);
    }

    @Override
    public void setAnswerWrong(Quiz targetQuiz, String Answer, Session session) {
        targetQuiz.getCurrentRound().setAnswer(new Answer(session, Answer, targetQuiz.getCurrentQuestion(), false), targetQuiz, session);

    }

    @Override
    public void checkWhoAnswered(Quiz targetQuiz, String Answer, Session session, Question AskedQuestion) {

        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

    }
}
