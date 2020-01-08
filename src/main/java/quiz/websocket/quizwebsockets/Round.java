package quiz.websocket.quizwebsockets;

public class Round {

    private static Answer session1Awnser;
    private static Answer session2Awnser;
    private static Answer session3Awnser;
    private static Answer session4Awnser;
    private static Answer session5Awnser;

//    public Round(Answer answer){
//        this.session1Awnser = se;
//        this.session2Awnser = ;
//        this.session3Awnser = this.session3Awnser;
//        this.session4Awnser = this.session4Awnser;
//        this.session5Awnser = this.session5Awnser;
//    }

    public void setAllAwnsersnull(){
        setSession1Awnser(null);
        setSession2Awnser(null);
        setSession3Awnser(null);
        setSession4Awnser(null);
        setSession5Awnser(null);
    }

    public void setSession1Awnser(Answer session1Awnser) { this.session1Awnser = session1Awnser; }
    public void setSession2Awnser(Answer session2Awnser) { this.session2Awnser = session2Awnser; }
    public void setSession3Awnser(Answer session3Awnser) { this.session3Awnser = session3Awnser; }
    public void setSession4Awnser(Answer session4Awnser) { this.session4Awnser = session4Awnser; }
    public void setSession5Awnser(Answer session5Awnser) { this.session5Awnser = session5Awnser; }

    public Answer getSession1Awnser() { return session1Awnser; }
    public Answer getSession2Awnser() { return session2Awnser; }
    public Answer getSession3Awnser() { return session3Awnser; }
    public Answer getSession4Awnser() { return session4Awnser; }
    public Answer getSession5Awnser() { return session5Awnser; }

    public boolean questionAnswered(){return this.session1Awnser != null && this.session2Awnser != null && this.session3Awnser != null && this.session4Awnser != null && this.session5Awnser != null;}
}
