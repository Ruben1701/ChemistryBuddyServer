package quiz.server;

import quiz.websocket.questions.Question;
import quiz.server.model.Quiz;
import quiz.websocket.quizwebsockets.broadcast.questionBroadcast;
import quiz.websocket.quizwebsockets.interfaces.iGameRoomManager;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class QuizRoomManager implements iGameRoomManager {
    static HashSet<Session> sessions = new HashSet<>();
    static ArrayList<Quiz> quizes = new ArrayList();
    public static Question CurrentQuestion;
    public static int Round;
    public static boolean roomAvailable;

    @Override
    public void addToGameRoom(Session session) {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        roomAvailable = false;
        log.log(Level.INFO,"[Connected] Succesfully connected session:" + session.getId());
        //System.out.println("[Connected] Succesfully connected session:" + session.getId());
        sessions.add(session);

        for (Quiz quiz : quizes) {
            if (!quiz.roomFull()) {
                quiz.join(session);
                //this.broadcast(session.getId(), quiz, "roomjoined");
                if (quiz.roomFull()) {
                    //this.broadcast(session.getId(), quiz, "quiz");
                    questionBroadcast questionBroadcast = new questionBroadcast();
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

        //this.broadcast(session.getId(), quiz, "roomjoined");

        log.log(Level.INFO, "New Gameroom made");
    }


}
