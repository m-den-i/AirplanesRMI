package logging;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by denis on 11/11/14.
 */
public class UseLogger {
    private static Logger LOGGER;

    /** use the classname for the logger, this way you can refactor
     *
     * @param s
     */
        public UseLogger(String s)
        {
            LOGGER = Logger.getLogger(s);
        }

        public void doSomeThingAndLog() {

            // set the LogLevel to Info, severe, warning and info will be written
            // finest is still not written
            LOGGER.setLevel(Level.INFO);
            LOGGER.info("Everything has been done, further info in Logging.txt\n");
        }
    }
