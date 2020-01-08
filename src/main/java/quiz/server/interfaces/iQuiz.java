package quiz.server.interfaces;

import quiz.websocket.questions.Question;

public interface iQuiz {
    Question getRandomQuestion();
    boolean checkAnswer(Question question, String answer);
}
