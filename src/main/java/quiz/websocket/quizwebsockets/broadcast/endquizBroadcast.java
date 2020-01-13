package quiz.websocket.quizwebsockets.broadcast;

import quiz.ServerLogger;
import quiz.server.logic.QuizLogic;
import quiz.server.model.Player;
import quiz.server.model.Question;
import quiz.server.model.Quiz;
import quiz.websocket.quizwebsockets.interfaces.iBroadcast;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class endquizBroadcast implements iBroadcast {

    private static final String message = "End of quiz"; // Compliant

    @Override
    public void broadcast(String s, Quiz quiz) {
        //Logger
        ServerLogger obj = new ServerLogger();
        obj.CreateLog();

        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        log.log(Level.INFO,"[Broadcast] { " + s + " } to:");

        QuizLogic quizLogic = new QuizLogic();
        Question currentQuestion = quizLogic.getRandomQuestion();

        try {
            for ( Player player : quiz.getPlayers()) {
                quiz.getSession(player).getBasicRemote().sendText("End of quiz!");
            }
        } catch (IOException e) {
            log.log(Level.SEVERE, (Supplier<String>) e);
        }
        log.log(Level.INFO, "[End of Broadcast]");
    }
}
