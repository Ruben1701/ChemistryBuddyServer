package quiz;

import quiz.rest.achievement.RestAchievementService;
import quiz.websocket.quizwebsockets.EventServer;
import quiz.rest.authentication.RestAuthenticationService;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class StartServices {


    public static void main(String[] args) {
        //Logger
        ServerLogger obj = new ServerLogger();
        obj.CreateLog();

        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        CountDownLatch latch = new CountDownLatch(1);
        Thread eventServerThread = new Thread(() -> EventServer.main(null));
        Thread restLoginServerThread = new Thread(() -> RestAuthenticationService.main(null));
        Thread restAchievementServerThread = new Thread(() -> RestAchievementService.main(null));


        eventServerThread.start();
        restLoginServerThread.start();
        restAchievementServerThread.start();

        log.log(Level.INFO, "All threads started");

    }
}

