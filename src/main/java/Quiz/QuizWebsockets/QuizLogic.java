package Quiz.QuizWebsockets;

import Quiz.Questions.Question;
import Quiz.Questions.QuestionDictionary;

import java.util.Dictionary;

public class QuizLogic {
    public Question getRandomQuestion(){
        QuestionDictionary questionDictionary = new QuestionDictionary();
        Dictionary questions = questionDictionary.getQuestions();
        Question ChosenQuestion = (Question) questions.get("1");
        return ChosenQuestion;
    }
    public boolean checkAnswer(Question question, String answer){
        if (question.Answer == answer ){
            return true;
        }

        return false;
    }
}
