package quiz.websocket.questions;

import java.util.Dictionary;
import java.util.Hashtable;

public class QuestionDictionary {
        private Dictionary questions;

        private static final Question Question1 = new Question("Select Hydrogen", "H");
        private static final Question Question2 = new Question("Select Lithium", "Li");
        private static final Question Question3 = new Question("Select Natrium", "Na");
        private static final Question Question4 = new Question("Select Potassium", "K");



    public void questionDictionary(){
            // Initializing a Dictionary
            questions = new Hashtable();

            questions.put("1", Question1);
            questions.put("2", Question2);
            questions.put("3", Question3);
            questions.put("4", Question4);


    }

        public Dictionary getQuestions(){
            questionDictionary();
            return questions;
        }
}
