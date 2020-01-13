package quiz.server.model;

import javax.websocket.Session;

public class Player {
    private Session session;
    private int points;
    private Answer answer;
    private String userid;


    public Player(Session Session){
        this.session = Session;
    }

    public Session getSession() {
        return session;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
