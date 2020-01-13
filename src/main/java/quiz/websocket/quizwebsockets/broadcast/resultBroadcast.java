package quiz.websocket.quizwebsockets.broadcast;

import quiz.ServerLogger;
import quiz.server.model.Player;
import quiz.server.model.Quiz;
import quiz.websocket.quizwebsockets.interfaces.iBroadcast;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class resultBroadcast implements iBroadcast {
    @Override
    public void broadcast(String s, Quiz quiz) {
        //Logger
        ServerLogger obj = new ServerLogger();
        obj.CreateLog();

        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        //Round round = new Round();
        log.log(Level.INFO,"[Broadcast] { " + s + " } to:");

        try {
            for ( Player player : quiz.getPlayers()) {
                quiz.getSession(player).getBasicRemote().sendText(player.getAnswer().getCorrect().toString());
            }
        } catch (IOException e) {
            log.log(Level.SEVERE, (Supplier<String>) e);
        }
        log.log(Level.INFO, "[End of Broadcast]");
    }
}
