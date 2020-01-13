package quiz.server.logic;

import quiz.server.model.Player;
import quiz.server.model.Round;
import quiz.server.model.Quiz;
import quiz.websocket.quizwebsockets.broadcast.endquizBroadcast;
import quiz.websocket.quizwebsockets.broadcast.questionBroadcast;
import quiz.websocket.quizwebsockets.broadcast.resultBroadcast;
import quiz.websocket.quizwebsockets.broadcast.roomjoinedBroadcast;
import quiz.server.interfaces.iGameRoomManager;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class QuizRoomManager implements iGameRoomManager {
    static HashSet<Session> sessions = new HashSet<>();
    public static ArrayList<Quiz> quizes = new ArrayList();
    public static boolean roomAvailable;
    private roomjoinedBroadcast roomjoinedBroadcast = new roomjoinedBroadcast();
    private questionBroadcast questionBroadcast = new questionBroadcast();
    private AchievementManager achievementManager = new AchievementManager();



    @Override
    public void addToGameRoom(Session session) {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        roomAvailable = false;
        log.log(Level.INFO,"[Connected] Succesfully connected session:" + session.getId());
        sessions.add(session);

        for (Quiz quiz : quizes) {
            if (!quiz.roomFull()) {
                roomAvailable = true;
                quiz.join(session);
                roomjoinedBroadcast.broadcast("Joined Room", quiz);
                if (quiz.roomFull()) {
                    quiz.setCurrentQuestion();
                    quiz.setCurrentRound(new Round(1));
                    questionBroadcast.broadcast("Question", quiz);
                }
            }
        }

        if (!roomAvailable) {
            createGameRoom(session);
        }

        log.log(Level.INFO, "[#sessions]: " + sessions.size());
    }

    @Override
    public void createGameRoom(Session session) {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        Quiz quiz = new Quiz(session);
        quizes.add(quiz);

        roomjoinedBroadcast.broadcast("Joined Room", quiz);
        log.log(Level.INFO, "New Gameroom made");
    }

    @Override
    public void nextRound(Quiz targetedQuiz){
        if (targetedQuiz.getCurrentRound().questionAnswered(targetedQuiz)){
            targetedQuiz.setCurrentRound(new Round(targetedQuiz.getCurrentRound().getRoundNo() + 1));
            if (targetedQuiz.getCurrentRound().getRoundNo() > 3){
                resultBroadcast resultBroadcast = new resultBroadcast();
                resultBroadcast.broadcast("round results", targetedQuiz);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                achievementManager.achievement(targetedQuiz);
                endquizBroadcast endquizBroadcast = new endquizBroadcast();
                endquizBroadcast.broadcast("End of quiz", targetedQuiz);

            }
        else {
                resultBroadcast resultBroadcast = new resultBroadcast();
                resultBroadcast.broadcast("round results", targetedQuiz);
                targetedQuiz.setCurrentQuestion();
                for (Player player: targetedQuiz.getPlayers()) {
                    player.setAnswer(null);
                }
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                questionBroadcast.broadcast("Question", targetedQuiz);
            }
        }
    }

    public ArrayList<Quiz> getQuizes() {
        return quizes;
    }
}
