package modernjavainaction.chap09;

import com.typesafe.sslconfig.util.LoggerFactory;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.Level;
public class logging {

  private static final Logger logger =Logger.getLogger(logging.class.getName());

  public static void main(String[] args) {
    logger.setLevel(Level.FINER);
    Handler consoleHandler = new ConsoleHandler();
    consoleHandler.setLevel(Level.FINER);
    logger.addHandler(consoleHandler);
    if (logger.isLoggable(Level.FINER)) {
        logger.finer("Problem: " + "generateDiagnostic()");
    }


    logger.log(Level.FINER, () -> "Problem: " + "generateDiagnostic()");
  }



}
