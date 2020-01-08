package quiz.websocket.quizwebsockets.broadcast;

import quiz.server.model.Quiz;

public interface iBroadcast {
    void broadcast(String s, Quiz quiz);
}
