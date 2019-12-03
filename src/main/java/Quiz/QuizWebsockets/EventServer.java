package Quiz.QuizWebsockets;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.Session;
import javax.websocket.server.ServerContainer;

public class EventServer {
    public static void main(String[] args) {
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
            System.out.println("Server running :)");

            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String message, Session session) {
        try {
            session.getBasicRemote().sendObject(message);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
