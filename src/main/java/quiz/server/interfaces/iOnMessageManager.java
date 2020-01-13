package quiz.server.interfaces;

import quiz.server.model.Quiz;

import javax.websocket.Session;

public interface iOnMessageManager {
    Quiz targetedQuiz(Session session);
}
