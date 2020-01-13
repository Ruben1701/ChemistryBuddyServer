package quiz.server.model;

import quiz.server.logic.QuizLogic;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private List<Player> Players = new ArrayList<>();
    private static Question currentQuestion;
    private static Round currentRound;


    public Quiz(Session session){
        Player player = new Player(session);
        Players.add(player);
    }

    public List<Player> getPlayers() {
        return Players;
    }

    public Session getSession(Player player){
        return player.getSession();
    }

    public boolean roomFull(){return Players.size() == 2;}

    public void join(Session session) {
        Player player = new Player(session);
        Players.add(player);
    }

    public boolean isPlayerHere(Session session) {
        for (Player player: Players) {
            if (player.getSession() == session){
                return true;
            }
        }
        return false;
    }

    public void setCurrentQuestion() {
        QuizLogic quizLogic = new QuizLogic();
        Quiz.currentQuestion = quizLogic.getRandomQuestion();
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentRound(Round currentRound) {
        Quiz.currentRound = currentRound;
    }

    public Round getCurrentRound() {
        return currentRound;
    }
}
