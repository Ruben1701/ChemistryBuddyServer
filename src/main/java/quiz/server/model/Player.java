package quiz.server.model;

import javax.websocket.Session;

public class Player {
    private Session session;
    private int points;

    public Player(Session Session){
        this.session = Session;
    }

    public Session getSession() {
        return session;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
