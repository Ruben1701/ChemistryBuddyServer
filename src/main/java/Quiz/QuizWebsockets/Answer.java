package Quiz.QuizWebsockets;

import Quiz.Questions.Question;

import javax.websocket.Session;

public class Answer {
    private Session SessionNo;
    private String GivenAnswer;
    private Question AskedQuestion;
    private Boolean Correct;
    public Answer(Session sessionNo, String givenAnswer, Question askedQuestion, Boolean correct ){
        this.SessionNo = sessionNo;
        this.GivenAnswer = givenAnswer;
        this.AskedQuestion = askedQuestion;
        this.Correct = correct;
    }

    public Boolean getCorrect() {
        return Correct;
    }
}
