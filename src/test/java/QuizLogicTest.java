import quiz.server.logic.QuizLogic;
import quiz.websocket.questions.Question;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuizLogicTest {


    private Object Question;
    private QuizLogic tester = new QuizLogic();

    @BeforeAll
    public static void init(){

    }

    @Test
    public void getRandomQuestionReturnsQuestionType() {

        var receivedQuestion = tester.getRandomQuestion();

        assertTrue(receivedQuestion != null);
    }

    @Test
    public void checkAnswerCorrect(){
        Question testQuestion = new Question("Test question", "ShouldPass");

        assertTrue(tester.checkAnswer(testQuestion, "ShouldPass"));
    }

    @Test
    public void checkAnswerWrong(){
        Question testQuestion = new Question("Test question", "ShouldFail");

        assertFalse(tester.checkAnswer(testQuestion, "ShouldPass"));
    }
}