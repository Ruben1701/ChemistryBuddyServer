package quiz.websocket.quizwebsockets;

import quiz.server.model.Quiz;
import quiz.websocket.questions.Question;
import quiz.websocket.quizwebsockets.interfaces.iAnswerManager;

import javax.websocket.Session;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class AnswerManager implements iAnswerManager {

    @Override
    public void setAnswerCorrect(Quiz targetQuiz, String Answer, Session session, Question AskedQuestion, Round Currentround) {
        Currentround.setSession1Awnser(new Answer(session, Answer, AskedQuestion, true));
        targetQuiz.setPoints(session, Currentround);
    }

    @Override
    public void setAnswerWrong(Quiz targetQuiz, String Answer, Session session, Question AskedQuestion, Round Currentround) {
        Currentround.setSession1Awnser(new Answer(session, Answer, AskedQuestion, false));

    }

    @Override
    public void checkWhoAnswered(Quiz targetQuiz, String Answer, Session session, Question AskedQuestion) {

        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        Round round = new Round();


        if (session.equals(Objects.requireNonNull(targetQuiz).getSession1())) {
            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 1 answered");
            if (Answer.equals(AskedQuestion.Answer)) {

            } else {

            }
        } else if (session.equals(targetQuiz.getSession2())) {
            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 2 answered");
            if (Answer.equals(AskedQuestion.Answer)) {
                round.setSession2Awnser(new Answer(session, Answer, AskedQuestion, true));
                targetQuiz.setPoints(session, round);
            } else {
                round.setSession2Awnser(new Answer(session, Answer, AskedQuestion, false));
            }
        } else if (session.equals(targetQuiz.getSession3())) {
            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 3 answered");
            if (Answer.equals(AskedQuestion.Answer)) {
                round.setSession3Awnser(new Answer(session, Answer, AskedQuestion, true));
                targetQuiz.setPoints(session, round);
            } else {
                round.setSession3Awnser(new Answer(session, Answer, AskedQuestion, false));
            }

        } else if (session.equals(targetQuiz.getSession4())) {
            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 4 answered");
            if (Answer.equals(AskedQuestion.Answer)) {
                round.setSession4Awnser(new Answer(session, Answer, AskedQuestion, true));
                targetQuiz.setPoints(session, round);
            } else {
                round.setSession4Awnser(new Answer(session, Answer, AskedQuestion, false));

            }
        } else {
            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 5 answered");
            if (Answer.equals(AskedQuestion.Answer)) {
                round.setSession5Awnser(new Answer(session, Answer, AskedQuestion, true));
                targetQuiz.setPoints(session, round);
            } else {
                round.setSession5Awnser(new Answer(session, Answer, AskedQuestion, false));

            }
        }

    }
}
