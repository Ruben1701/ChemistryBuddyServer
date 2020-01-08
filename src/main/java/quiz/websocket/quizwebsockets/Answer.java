package quiz.websocket.quizwebsockets;

import quiz.websocket.questions.Question;

import javax.websocket.Session;

public class Answer {
    private Boolean Correct;
    private Session session;
    public Answer(Session sessionNo, String givenAnswer, Question askedQuestion, Boolean correct ){
        this.Correct = correct;
        this.session = sessionNo;
    }

    public Boolean getCorrect() {
        return Correct;
    }

    public Session getSession() {
        return session;
    }
}
