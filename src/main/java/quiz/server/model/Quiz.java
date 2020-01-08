package quiz.server.model;

import quiz.websocket.quizwebsockets.Round;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Quiz {
    private Session session1;
    private Session session2;
    private Session session3;
    private Session session4;
    private Session session5;

    private int session1points;
    private int session2points;
    private int session3points;
    private int session4points;
    private int session5points;

    private List<Session> allSessions;
    private List<Player> Players = new ArrayList<>();


    public Quiz(Session session){
        Player player = new Player(session);
        Players.add(player);
//        this.session1 = session1;
//        this.session2 = this.session2;
//        this.session3 = this.session3;
//        this.session4 = this.session4;
//        this.session5 = this.session5;
    }

    public List<Player> getPlayers() {
        return Players;
    }

    public Session getSession(Player player){
        return player.getSession();
    }

    public boolean roomFull(){return Players.size() == 5;}

    public void addSessionsToList(){
        allSessions.add(this.session1);
        allSessions.add(this.session2);
        allSessions.add(this.session3);
        allSessions.add(this.session4);
        allSessions.add(this.session5);
    }

    public List<Session> getAllSessions() {
        return allSessions;
    }

    public void join(Session session) {
        Player player = new Player(session);
        Players.add(player);
//        if (this.session1 == null) {
//            this.session1 = session;
//        }
//        else if (this.session2 == null) {
//            this.session2 = session;
//        }
//        else if (this.session3 == null) {
//            this.session3 = session;
//        }
//        else if (this.session4 == null) {
//            this.session4 = session;
//        }
//        else{
//            this.session5 = session;
//        }

    }

    public void setPoints(Session session, Round round){
        if (session.getId().equals(session1.getId())) {
            if (Boolean.TRUE.equals(round.getSession1Awnser().getCorrect())){
                this.session1points = this.session1points + 100;
            }
        }
        else if (session.getId().equals(session2.getId())) {
            if (Boolean.TRUE.equals(round.getSession2Awnser().getCorrect())){
                this.session2points = this.session2points + 100;
            }
        }
        else if (session.getId().equals(session3.getId())) {
            if (Boolean.TRUE.equals(round.getSession3Awnser().getCorrect())){
                this.session3points = this.session3points + 100;
            }
        }
        else if (session.getId().equals(session4.getId())) {
            if (Boolean.TRUE.equals(round.getSession4Awnser().getCorrect())){
                this.session4points = this.session4points + 100;
            }
        }
        else if (session.getId().equals(session5.getId())){
            if (Boolean.TRUE.equals(round.getSession5Awnser().getCorrect())){
                this.session5points = this.session5points + 100;
            }
        }
    }


//    public int[] createLeaderboard(){
//        int session1 = getSession1points();
//        int session2 = getSession2points();
//        int session3 = getSession3points();
//        int session4 = getSession4points();
//        int session5 = getSession5points();
//
//        int[] items = new int[] {session1, session2, session3, session4, session5};
//
//        for (int i = 0; i < items.length - 1; ++i) {
//            for (int j = i + 1; j < items.length; ++j) {
//                if (items[i] > items[j]) {
//                    int temp = items[i];
//                    items[i] = items[j];
//                    items[j] = temp;
//                }
//            }
//        }
//        return items;
//        //TODO
//    }

    public boolean isPlayerHere(Session session) {
        return Objects.equals(this.session1.getId(), session.getId()) || Objects.equals(this.session2.getId(), session.getId()) || Objects.equals(this.session3.getId(), session.getId()) || Objects.equals(this.session4.getId(), session.getId()) || Objects.equals(this.session5.getId(), session.getId());
    }


}
