package quiz.websocket.quizwebsockets;

import quiz.server.logic.QuizRoomManager;
import quiz.server.model.Quiz;
import quiz.server.model.Question;
import quiz.ServerLogger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@ServerEndpoint(value = "/ChemistryBuddy/Quiz/")
public class EventServerSocket {
    static HashSet<Session> sessions = new HashSet<>();
    public static ArrayList<Quiz> quizes = new ArrayList();
    public static Question CurrentQuestion;
    public static int Round;
    public static boolean roomAvailable;
    QuizRoomManager quizRoomManager = new QuizRoomManager();


    @OnOpen
    public void onConnect(Session session) {
//        LogManager lgmngr = LogManager.getLogManager();
//        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
//
//        roomAvailable = false;
//        log.log(Level.INFO,"[Connected] Succesfully connected session:" + session.getId());
//        //System.out.println("[Connected] Succesfully connected session:" + session.getId());
//        sessions.add(session);
//
//        for (Quiz quiz : quizes) {
//            if (!quiz.roomFull()) {
//                quiz.join(session);
//                roomAvailable = true;
//                this.broadcast(session.getId(), quiz, "roomjoined");
//
//                if (quiz.roomFull()) {
//                    this.broadcast(session.getId(), quiz, "quiz");
//                }
//                break;
//            }
//        }
//
//        if (!roomAvailable) {
//            Quiz quiz = new Quiz(session);
//            quizes.add(quiz);
//            log.log(Level.INFO, "New Gameroom made");
//            this.broadcast(session.getId(), quiz, "roomjoined");
//        }
//
//        log.log(Level.INFO, "[#sessions]: " + sessions.size());
        quizRoomManager.addToGameRoom(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        //Logger
        ServerLogger obj = new ServerLogger();
        obj.CreateLog();

        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        log.log(Level.INFO, "[Session ID] : " + session.getId() + " [Received] : " + message);

        OnMessageManager onMessageManager = new OnMessageManager();

        onMessageManager.convertMessagetoAnswer(session, message);
//        quiz.server.model.Round round = new Round();
//        Quiz quiz = null;
//
//        for (Quiz allquiz : quizes) {
//            if (allquiz.isPlayerHere(session)) {
//                quiz = allquiz;
//            }
//        }

//        if (session.equals(Objects.requireNonNull(quiz).getSession1())) {
//            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 1 answered");
//            if (message.equals(CurrentQuestion.Answer)) {
//                round.setSession1Awnser(new Answer(session, message, CurrentQuestion, true));
//                quiz.setPoints(session, round);
//            } else {
//                round.setSession1Awnser(new Answer(session, message, CurrentQuestion, false));
//
//            }
//        } else if (session.equals(quiz.getSession2())) {
//            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 2 answered");
//            if (message.equals(CurrentQuestion.Answer)) {
//                round.setSession2Awnser(new Answer(session, message, CurrentQuestion, true));
//                quiz.setPoints(session, round);
//            } else {
//                round.setSession2Awnser(new Answer(session, message, CurrentQuestion, false));
//            }
//        } else if (session.equals(quiz.getSession3())) {
//            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 3 answered");
//            if (message.equals(CurrentQuestion.Answer)) {
//                round.setSession3Awnser(new Answer(session, message, CurrentQuestion, true));
//                quiz.setPoints(session, round);
//            } else {
//                round.setSession3Awnser(new Answer(session, message, CurrentQuestion, false));
//            }
//
//        } else if (session.equals(quiz.getSession4())) {
//            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 4 answered");
//            if (message.equals(CurrentQuestion.Answer)) {
//                round.setSession4Awnser(new Answer(session, message, CurrentQuestion, true));
//                quiz.setPoints(session, round);
//            } else {
//                round.setSession4Awnser(new Answer(session, message, CurrentQuestion, false));
//
//            }
//        } else {
//            log.log(Level.INFO, "[Session ID] : " + session.getId() + " 5 answered");
//            if (message.equals(CurrentQuestion.Answer)) {
//                round.setSession5Awnser(new Answer(session, message, CurrentQuestion, true));
//                quiz.setPoints(session, round);
//            } else {
//                round.setSession5Awnser(new Answer(session, message, CurrentQuestion, false));
//
//            }
//        }
//
//        if (round.questionAnswered()) {
//            Round++;
//            if (Round < 3){
//                broadcast("Results", quiz, "EndRound");
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                round.setAllAwnsersnull();
//                broadcast("Question", quiz, "quiz");
//
//            }
//            else{
//                broadcast("Results", quiz, "EndRound");
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                broadcast("End of quiz", quiz, "EndQuiz");
//            }
//        }
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        log.log(Level.WARNING, "[Session ID] : " + session.getId() + "[Socket Closed: " + reason);
        sessions.remove(session);
    }


    @OnError
    public void onError(Throwable cause, Session session) {
        //Logger
        ServerLogger obj = new ServerLogger();
        obj.CreateLog();

        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        log.log(Level.SEVERE, "[Session ID] : " + session.getId() + "[ERROR]: ");
        log.log(Level.SEVERE, (Supplier<String>) System.err);
        //cause.printStackTrace(System.err);
    }

    public void broadcast(String s, Quiz quiz, String broadcastType) {
        //Logger
        ServerLogger obj = new ServerLogger();
        obj.CreateLog();

        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        //Round round = new Round();
        log.log(Level.INFO, "[Broadcast] { " + s + " } to:");
        //try {
            //for (Session session : quiz.getAllSessions())
            //session.getBasicRemote().sendText("Test");
//                switch (broadcastType) {
//                    case "quiz":
//                        QuizLogic quizLogic = new QuizLogic();
//                        CurrentQuestion = quizLogic.getRandomQuestion();
//
//                        quiz.getSession1().getBasicRemote().sendText(CurrentQuestion.QuestionText);
//                        quiz.getSession2().getBasicRemote().sendText(CurrentQuestion.QuestionText);
//                        quiz.getSession3().getBasicRemote().sendText(CurrentQuestion.QuestionText);
//                        quiz.getSession4().getBasicRemote().sendText(CurrentQuestion.QuestionText);
//                        quiz.getSession5().getBasicRemote().sendText(CurrentQuestion.QuestionText);
//                        break;
//                    case "EndRound":
//                        quiz.getSession1().getBasicRemote().sendText(round.getSession1Awnser().getCorrect().toString() + ":" + quiz.getSession1points());
//                        quiz.getSession2().getBasicRemote().sendText(round.getSession2Awnser().getCorrect().toString() + ":" + quiz.getSession2points());
//                        quiz.getSession3().getBasicRemote().sendText(round.getSession3Awnser().getCorrect().toString() + ":" + quiz.getSession3points());
//                        quiz.getSession4().getBasicRemote().sendText(round.getSession4Awnser().getCorrect().toString() + ":" + quiz.getSession4points());
//                        quiz.getSession5().getBasicRemote().sendText(round.getSession5Awnser().getCorrect().toString() + ":" + quiz.getSession5points());
//                        break;
//                    case "EndQuiz":
//                        quiz.getSession1().getBasicRemote().sendText("End of quiz");
//                        quiz.getSession2().getBasicRemote().sendText("End of quiz");
//                        quiz.getSession3().getBasicRemote().sendText("End of quiz");
//                        quiz.getSession4().getBasicRemote().sendText("End of quiz");
//                        quiz.getSession5().getBasicRemote().sendText("End of quiz");
//                        break;
//                    case "roomjoined":
//                        if (quiz.getSession1() != null) {
//                            quiz.getSession1().getBasicRemote().sendText("Joined quiz room waiting on more players");
//                        }
//                        if (quiz.getSession2() != null){
//                            quiz.getSession2().getBasicRemote().sendText("Joined quiz room waiting on more players");
//                        }
//                        if (quiz.getSession3() != null) {
//                            quiz.getSession3().getBasicRemote().sendText("Joined quiz room waiting on more players");
//                        }
//                        if (quiz.getSession4() != null){
//                            quiz.getSession4().getBasicRemote().sendText("Joined quiz room waiting on more players");
//                        }
//                        break;
//                }
//            } catch (IOException e) {
//                log.log(Level.SEVERE, (Supplier<String>) e);
//            }
          //  log.log(Level.INFO, "[End of Broadcast]");
    }
}
