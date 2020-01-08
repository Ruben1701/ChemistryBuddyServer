package quiz.websocket.quizwebsockets;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import quiz.ServerLogger;
import javax.websocket.Session;
import javax.websocket.server.ServerContainer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class EventServer {

    public static void main(String[] args) {
        //Logger
        ServerLogger obj = new ServerLogger();
        obj.CreateLog();

        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8069);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        try{
            //intialize websocket
            ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(context);

            //add websocket endpoint
            wscontainer.addEndpoint(EventServerSocket.class);

            server.start();
            log.log(Level.INFO, "EventServer running :)");

            server.join();
        } catch (Exception e) {
           log.log(Level.SEVERE, (Supplier<String>) e);
        }

    }

    public void sendMessage(String message, Session session) {
        //Logger
        ServerLogger obj = new ServerLogger();
        obj.CreateLog();

        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        try {
            session.getBasicRemote().sendObject(message);
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, (Supplier<String>) e);
        }
    }
}
