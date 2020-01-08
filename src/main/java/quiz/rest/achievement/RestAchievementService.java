package quiz.rest.achievement;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import quiz.ServerLogger;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class RestAchievementService {
    public static void main(String[] args) {
        //Logger
        ServerLogger obj = new ServerLogger();
        obj.CreateLog();

        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

        ServletContextHandler context = new
                ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        Server jettyServer = new Server(8091);
        jettyServer.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
                ChemistryBuddyAchievementRESTService.class.getCanonicalName());
        try {
            jettyServer.start();
            log.log(Level.INFO, "RestAchievementService running :)");
            //System.out.println("RestAchievementService running :)");
            jettyServer.join();

        } catch (Exception ex) {
            System.exit(1);
        }finally {
            jettyServer.destroy();
        }
    }
}
