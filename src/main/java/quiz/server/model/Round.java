package quiz.server.model;

import javax.websocket.Session;

public class Round {
    private int roundNo;

    public Round(int RoundNo){
        this.roundNo = RoundNo;
    }

    public int getRoundNo() {
        return roundNo;
    }

    public void setAnswer(Answer answer, Quiz targetQuiz, Session session){
        for (Player player : targetQuiz.getPlayers()){
            if (player.getSession().equals(session)){
                player.setAnswer(answer);
            }
        }
    }

    public boolean questionAnswered(Quiz targetQuiz){
        for (Player player: targetQuiz.getPlayers()) {
            if (player.getAnswer() == null){
                return false;
            }
        }
        return true;
    }
}
