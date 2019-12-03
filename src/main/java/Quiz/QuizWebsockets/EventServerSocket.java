package Quiz.QuizWebsockets;

import Quiz.Questions.Question;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

@ServerEndpoint(value = "/ChemistryBuddy/Answer/")
public class EventServerSocket {
    static HashSet<Session> sessions = new HashSet<>();
    static ArrayList<Quiz> quizes = new ArrayList();
    public static Question CurrentQuestion;
    public static int Round;

    public Quiz currentQuiz;


    EventServer eventServer = new EventServer();

    @OnOpen
    public void onConnect(Session session) {
        boolean roomAvailable = false;
        System.out.println("[Connected] Succesfully connected session:" + session.getId());
        sessions.add(session);
        //system.out.println("[sessions] number of sessions:" + sessions.size());
        Iterator quizIterator = quizes.iterator();

        while(quizIterator.hasNext()) {
            Quiz quiz = (Quiz)quizIterator.next();
            if (!quiz.roomFull()) {
                quiz.join(session);
                roomAvailable = true;

                if (quiz.roomFull()){
                    this.broadcast(session.getId(), quiz, "Quiz");
                    //quiz.addSessionsToList();
                }
                break;
            }
        }

        if (!roomAvailable) {
            Quiz quiz = new Quiz(session);
            quizes.add(quiz);
            System.out.println("new game made");
        }

        System.out.println("[#sessions]: " + sessions.size());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("[Session ID] : " + session.getId() + " [Received] : " + message);
        QuizLogic quizLogic = new QuizLogic();
        Round round = new Round();
        Quiz quiz = null;

        Iterator iterator = quizes.iterator();

        while (iterator.hasNext()) {
            Quiz allquiz = (Quiz) iterator.next();
            if (allquiz.isPlayerHere(session)) {
                quiz = allquiz;
            }
        }

        if (session.equals(quiz.getSession1())) {
            System.out.println("1");
            if (message.equals(CurrentQuestion.Answer)) {
                round.setSession1Awnser(new Answer(session, message, CurrentQuestion, true));
            } else {
                round.setSession1Awnser(new Answer(session, message, CurrentQuestion, false));

            }
        } else if (session.equals(quiz.getSession2())) {
            System.out.println("2");
            if (message.equals(CurrentQuestion.Answer)) {
                round.setSession2Awnser(new Answer(session, message, CurrentQuestion, true));
            } else {
                round.setSession2Awnser(new Answer(session, message, CurrentQuestion, false));

            }
        } else if (session.equals(quiz.getSession3())) {
            System.out.println("3");
            if (message.equals(CurrentQuestion.Answer)) {
                round.setSession3Awnser(new Answer(session, message, CurrentQuestion, true));
            } else {
                round.setSession3Awnser(new Answer(session, message, CurrentQuestion, false));
            }

        } else if (session.equals(quiz.getSession4())) {
            System.out.println("4");
            if (message.equals(CurrentQuestion.Answer)) {
                round.setSession4Awnser(new Answer(session, message, CurrentQuestion, true));
            } else {
                round.setSession4Awnser(new Answer(session, message, CurrentQuestion, false));

            }
        } else {
            System.out.println("5");
            if (message.equals(CurrentQuestion.Answer)) {
                round.setSession5Awnser(new Answer(session, message, CurrentQuestion, true));
            } else {
                round.setSession5Awnser(new Answer(session, message, CurrentQuestion, false));

            }
        }

        if (round.questionAnswered()) {
            //Round++;
            //if (Round < 2){
            //else{
            broadcast("", quiz, "EndRound");

            //}
        }

    }




//        if (message.equals("quiz")) {
//            try {
//
//                eventServer.sendMessage(CurrentQuestion.QuestionText, session);
//                questionCount++;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        else if (message.equals(CurrentQuestion.Answer)) {
//            eventServer.sendMessage("Correct", session);
//            if (questionCount < 2) {
//                CurrentQuestion = quizLogic.getRandomQuestion();
//                eventServer.sendMessage(CurrentQuestion.QuestionText, session);
//                questionCount++;
//            }
//        }
//        else {
//            eventServer.sendMessage("Wrong", session);
//
//        }



    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[Socket Closed: " + reason);
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }

    public void broadcast(String s, Quiz quiz, String broadcastType) {
        Round round = new Round();
        System.out.println("[Broadcast] { " + s + " } to:");
            try {
                //for (Session session : quiz.getAllSessions())
                //session.getBasicRemote().sendText("Test");
                if (broadcastType.equals("Quiz")){
                    QuizLogic quizLogic = new QuizLogic();
                    CurrentQuestion = quizLogic.getRandomQuestion();

                    quiz.getSession1().getBasicRemote().sendText(CurrentQuestion.QuestionText);
                    quiz.getSession2().getBasicRemote().sendText(CurrentQuestion.QuestionText);
                    quiz.getSession3().getBasicRemote().sendText(CurrentQuestion.QuestionText);
                    quiz.getSession4().getBasicRemote().sendText(CurrentQuestion.QuestionText);
                    quiz.getSession5().getBasicRemote().sendText(CurrentQuestion.QuestionText);
                }
                else if (broadcastType.equals("EndRound")){
                    quiz.getSession1().getBasicRemote().sendText(round.getSession1Awnser().getCorrect().toString());
                    quiz.getSession2().getBasicRemote().sendText(round.getSession2Awnser().getCorrect().toString());
                    quiz.getSession3().getBasicRemote().sendText(round.getSession3Awnser().getCorrect().toString());
                    quiz.getSession4().getBasicRemote().sendText(round.getSession4Awnser().getCorrect().toString());
                    quiz.getSession5().getBasicRemote().sendText(round.getSession5Awnser().getCorrect().toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        System.out.println("[End of Broadcast]");
    }
}
