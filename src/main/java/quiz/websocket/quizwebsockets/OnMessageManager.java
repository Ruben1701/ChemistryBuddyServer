package quiz.websocket.quizwebsockets;

import quiz.server.logic.AnswerManager;
import quiz.server.logic.QuizRoomManager;
import quiz.server.model.Player;
import quiz.server.model.Quiz;
import quiz.server.interfaces.iOnMessageManager;

import javax.websocket.Session;

public class OnMessageManager implements iOnMessageManager {

    @Override
    public Quiz targetedQuiz(Session session) {

        QuizRoomManager quizRoomManager = new QuizRoomManager();
        Quiz targetQuiz = null;

        for (Quiz allquiz : quizRoomManager.getQuizes()) {
            if (allquiz.isPlayerHere(session)) {
                targetQuiz = allquiz;
            }
        }

        return targetQuiz;
    }

    public void convertMessagetoAnswer(Session session, String message){
        if (message.contains("UserId")){
            setUserId(session, message);
        }
        else {
            AnswerManager answerManager = new AnswerManager();
            Quiz targetQuiz = targetedQuiz(session);
            for (Player player : targetQuiz.getPlayers()) {
                if (session.equals(player.getSession())){
                    if (message.equals(targetQuiz.getCurrentQuestion().Answer)){
                        answerManager.setAnswerCorrect(targetQuiz, message, session);
                    }
                    else {
                        answerManager.setAnswerWrong(targetQuiz, message, session);
                    }
                }
            }
            QuizRoomManager quizRoomManager = new QuizRoomManager();
            quizRoomManager.nextRound(targetQuiz);
        }

    }

    public void setUserId(Session session, String message){
        Quiz targetQuiz = targetedQuiz(session);
        for (Player player : targetQuiz.getPlayers()) {
            if (session.equals(player.getSession())){
                player.setUserid(message.replaceAll("\\D+",""));
                System.out.println(player.getUserid());
            }
        }
    }

//    public void test(Session session) {
//        //Logger
//        ServerLogger obj = new ServerLogger();
//        obj.CreateLog();
//
//        LogManager lgmngr = LogManager.getLogManager();
//        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
//
//        //log.log(Level.INFO, "[Session ID] : " + session.getId() + " [Received] : " + message);
//        Round round = new Round();
//        Quiz quiz = null;
//
////        for (Quiz allquiz : quizzes) {
////            if (allquiz.isPlayerHere(session)) {
////                quiz = allquiz;
////            }
////        }
//
////        if (session.equals(Objects.requireNonNull(quiz).getSession1())) {
////            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 1 answered");
////            if (message.equals(CurrentQuestion.Answer)) {
////                round.setSession1Awnser(new Answer(session, message, CurrentQuestion, true));
////                quiz.setPoints(session, round);
////            } else {
////                round.setSession1Awnser(new Answer(session, message, CurrentQuestion, false));
////
////            }
////        } else if (session.equals(quiz.getSession2())) {
////            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 2 answered");
////            if (message.equals(CurrentQuestion.Answer)) {
////                round.setSession2Awnser(new Answer(session, message, CurrentQuestion, true));
////                quiz.setPoints(session, round);
////            } else {
////                round.setSession2Awnser(new Answer(session, message, CurrentQuestion, false));
////            }
////        } else if (session.equals(quiz.getSession3())) {
////            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 3 answered");
////            if (message.equals(CurrentQuestion.Answer)) {
////                round.setSession3Awnser(new Answer(session, message, CurrentQuestion, true));
////                quiz.setPoints(session, round);
////            } else {
////                round.setSession3Awnser(new Answer(session, message, CurrentQuestion, false));
////            }
////
////        } else if (session.equals(quiz.getSession4())) {
////            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 4 answered");
////            if (message.equals(CurrentQuestion.Answer)) {
////                round.setSession4Awnser(new Answer(session, message, CurrentQuestion, true));
////                quiz.setPoints(session, round);
////            } else {
////                round.setSession4Awnser(new Answer(session, message, CurrentQuestion, false));
////
////            }
////        } else {
////            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 5 answered");
////            if (message.equals(CurrentQuestion.Answer)) {
////                round.setSession5Awnser(new Answer(session, message, CurrentQuestion, true));
////                quiz.setPoints(session, round);
////            } else {
////                round.setSession5Awnser(new Answer(session, message, CurrentQuestion, false));
////
////            }
////        }
////
////        if (round.questionAnswered()) {
////            Round++;
////            if (Round < 3){
////                broadcast("Results", quiz, "EndRound");
////                try {
////                    TimeUnit.SECONDS.sleep(5);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////                round.setAllAwnsersnull();
////                broadcast("Question", quiz, "quiz");
////
////            }
////            else{
////                broadcast("Results", quiz, "EndRound");
////                try {
////                    TimeUnit.SECONDS.sleep(5);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////                broadcast("End of quiz", quiz, "EndQuiz");
////            }
////        }
//    }
}
