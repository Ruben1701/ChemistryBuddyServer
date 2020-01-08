package quiz.websocket.quizwebsockets.broadcast;

import quiz.ServerLogger;
import quiz.server.logic.QuizLogic;
import quiz.websocket.questions.Question;
import quiz.server.model.Quiz;
import quiz.websocket.quizwebsockets.Round;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class endquizBroadcast implements iBroadcast {

    private Question CurrentQuestion;
    private static final String message = "End of quiz"; // Compliant

    @Override
    public void broadcast(String s, Quiz quiz) {
        //Logger
        ServerLogger obj = new ServerLogger();
        obj.CreateLog();

        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        Round round = new Round();
        log.log(Level.INFO,"[Broadcast] { " + s + " } to:");

        QuizLogic quizLogic = new QuizLogic();
        CurrentQuestion = quizLogic.getRandomQuestion();

        try {
            quiz.getSession1().getBasicRemote().sendText(message);
            quiz.getSession2().getBasicRemote().sendText(message);
            quiz.getSession3().getBasicRemote().sendText(message);
            quiz.getSession4().getBasicRemote().sendText(message);
            quiz.getSession5().getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.log(Level.SEVERE, (Supplier<String>) e);
        }
        log.log(Level.INFO, "[End of Broadcast]");
    }
}
