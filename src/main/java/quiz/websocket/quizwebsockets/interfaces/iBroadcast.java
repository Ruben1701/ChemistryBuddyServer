package quiz.websocket.quizwebsockets.interfaces;

import quiz.server.model.Quiz;

public interface iBroadcast {
    void broadcast(String s, Quiz quiz);
}
