package quiz.websocket.quizwebsockets.broadcast;

import quiz.server.model.Player;
import quiz.server.model.Quiz;
import quiz.websocket.quizwebsockets.interfaces.iBroadcast;

import java.io.IOException;

public class roomjoinedBroadcast implements iBroadcast {
    @Override
    public void broadcast(String s, Quiz targetQuiz) {
        for (Player player: targetQuiz.getPlayers()) {
            int neededplayers = 5 - targetQuiz.getPlayers().size();
            try {
                player.getSession().getBasicRemote().sendText("waiting on " + neededplayers + " player(s) to join");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
