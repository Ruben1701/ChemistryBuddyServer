package Quiz.Questions;

import java.util.Dictionary;
import java.util.Hashtable;

public class QuestionDictionary {
        private Dictionary questions;

        private static final Question Question1 = new Question("Select Hydrogen", "H");


        public void questionDictionary(){
            // Initializing a Dictionary
            questions = new Hashtable();

            // info over alle elementen

            questions.put("1", Question1);
        }

        public Dictionary getQuestions(){
            questionDictionary();
            return questions;
        }
}
