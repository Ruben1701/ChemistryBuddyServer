package quiz.rest.service.test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;


public class TestRestService {
    public static void main(String[] args) {
        ServletContextHandler context = new
                ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        Server jettyServer = new Server(8090);
        jettyServer.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
                TestWebService.class.getCanonicalName());

        try {
            jettyServer.start();
            jettyServer.join();



        } catch (Exception ex) {
            System.exit(1);
        }finally {
            jettyServer.destroy();
        }


    }
}
