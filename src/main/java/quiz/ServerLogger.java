package quiz;

import java.util.logging.Logger;

public class ServerLogger {
    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // Get the Logger from the log manager which corresponds
    // to the given name <Logger.GLOBAL_LOGGER_NAME here>
    // static so that it is linked to the class and not to
    // a particular log instance because Log Manage is universal
    public void CreateLog()
    {
        // add some code of your choice here
        // Moving to the logging part now
        //LOGGER.log(Level.INFO, "Logging Working");

        // A log of INFO level with the message "My First Log Message"
    }
}
