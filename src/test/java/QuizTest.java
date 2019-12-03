import Quiz.Questions.Question;
import Quiz.QuizWebsockets.QuizLogic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuizTest {


    private Object Question;

    @Test
    public void multiplicationOfZeroIntegersShouldReturnZero() {
        QuizLogic tester = new QuizLogic();
        var receivedQuestion = tester.getRandomQuestion();
        // assert statements
        assertTrue(receivedQuestion instanceof Question);
    }
}