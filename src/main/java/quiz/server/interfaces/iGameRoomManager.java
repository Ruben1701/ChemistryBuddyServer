package quiz.server.interfaces;

import quiz.server.model.Quiz;

import javax.websocket.Session;

public interface iGameRoomManager {
    void addToGameRoom(Session session);
    void createGameRoom(Session session);

    void nextRound(Quiz targetedQuiz);
}
