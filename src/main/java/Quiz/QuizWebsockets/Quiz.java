package Quiz.QuizWebsockets;

import Quiz.Questions.Question;
import Quiz.Questions.QuestionDictionary;

import javax.websocket.Session;
import java.util.Dictionary;
import java.util.List;

public class Quiz {
    private Session session1;
    private Session session2;
    private Session session3;
    private Session session4;
    private Session session5;


    private List<Session> allSessions;


    public Quiz(Session session1){
        this.session1 = session1;
        this.session2 = this.session2;
        this.session3 = this.session3;
        this.session4 = this.session4;
        this.session5 = this.session5;
    }

    public Session getSession1() {
        return this.session1;
    }
    public Session getSession2() {
        return this.session2;
    }
    public Session getSession3() {
        return this.session3;
    }
    public Session getSession4() {
        return this.session4;
    }
    public Session getSession5() {
        return this.session5;
    }

    public boolean roomFull(){return this.session1 != null && this.session2 != null && this.session3 != null && this.session4 != null && this.session5 != null;}

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
        if (this.session1 == null) {
            this.session1 = session;
        }
        else if (this.session2 == null) {
            this.session2 = session;
        }
        else if (this.session3 == null) {
            this.session3 = session;
        }
        else if (this.session4 == null) {
            this.session4 = session;
        }
        else{
            this.session5 = session;
        }

    }

    public boolean isPlayerHere(Session session) {
        return this.session1.getId() == session.getId() || this.session2.getId() == session.getId() || this.session3.getId() == session.getId() || this.session4.getId() == session.getId() || this.session5.getId() == session.getId();
    }


}
