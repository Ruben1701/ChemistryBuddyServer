package quiz.server.interfaces;

import quiz.server.model.Question;

public interface iQuiz {
    Question getRandomQuestion();
    boolean checkAnswer(Question question, String answer);
}
