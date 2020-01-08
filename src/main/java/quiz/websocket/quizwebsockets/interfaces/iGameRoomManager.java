package quiz.websocket.quizwebsockets.interfaces;

import javax.websocket.Session;

public interface iGameRoomManager {
    void addToGameRoom(Session session);
    void createGameRoom(Session session);
}
