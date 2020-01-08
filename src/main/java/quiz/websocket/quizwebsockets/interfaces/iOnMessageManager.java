package quiz.websocket.quizwebsockets.interfaces;

import quiz.server.model.Quiz;

import javax.websocket.Session;
import java.util.ArrayList;

public interface iOnMessageManager {
    Quiz targetedQuiz(Session session, ArrayList<Quiz> quizzes);
}
