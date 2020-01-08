import quiz.websocket.questions.Question;
import quiz.websocket.quizwebsockets.Answer;
import quiz.websocket.quizwebsockets.Round;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.websocket.Session;

import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {

    private Round tester = new Round();

    @BeforeAll
    public static void init(){

    }

    //Test Constructors

    @Test
    public void getsetAnswerSession1(){
        Session testSession = null;
        Question testQuestion = null;
        Answer testAnswer = new Answer(null,"Test", null,true);

        tester.setSession1Awnser(testAnswer);

        assertEquals(testAnswer, tester.getSession1Awnser());
    }

    @Test
    public void getsetAnswerSession2(){
        Session testSession = null;
        Question testQuestion = null;
        Answer testAnswer = new Answer(null,"Test", null,true);

        tester.setSession2Awnser(testAnswer);

        assertEquals(testAnswer, tester.getSession2Awnser());
    }

    @Test
    public void getsetAnswerSession3(){
        Session testSession = null;
        Question testQuestion = null;
        Answer testAnswer = new Answer(null,"Test", null,true);

        tester.setSession3Awnser(testAnswer);

        assertEquals(testAnswer, tester.getSession3Awnser());
    }

    @Test
    public void getsetAnswerSession4(){
        Session testSession = null;
        Question testQuestion = null;
        Answer testAnswer = new Answer(null,"Test", null,true);

        tester.setSession4Awnser(testAnswer);

        assertEquals(testAnswer, tester.getSession4Awnser());
    }

    @Test
    public void getsetAnswerSession5(){
        Session testSession = null;
        Question testQuestion = null;
        Answer testAnswer = new Answer(null,"Test", null,true);

        tester.setSession5Awnser(testAnswer);

        assertEquals(testAnswer, tester.getSession5Awnser());
    }

    //Test Methods

    @Test
    public void questionAnsweredTrue() {
        Session testSession = null;
        Question testQuestion = null;
        Answer testAnswer = new Answer(null,"Test", null,true);
        tester.setSession1Awnser(testAnswer);
        tester.setSession2Awnser(testAnswer);
        tester.setSession3Awnser(testAnswer);
        tester.setSession4Awnser(testAnswer);
        tester.setSession5Awnser(testAnswer);

        assertTrue(tester.questionAnswered());
    }

    @Test
    public void questionAnsweredFalse() {
        tester.setSession1Awnser(null);
        tester.setSession2Awnser(null);
        tester.setSession3Awnser(null);
        tester.setSession4Awnser(null);
        tester.setSession5Awnser(null);

        assertFalse(tester.questionAnswered());
    }
}
