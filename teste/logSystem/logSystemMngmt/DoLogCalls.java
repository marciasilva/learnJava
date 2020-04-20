package logSystemMngmt;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DoLogCalls {

	static Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);
	static Logger personalizedLogger = Logger.getLogger("teste");

	static Logger pkgLogger = Logger.getLogger("logSystemMngmnt");

	// Is child of pkgLogger
	static Logger classLogger = Logger.getLogger("logSystemMngmt.DoLogCalls");

	/*
	 * OFF - Integer.MAX_VALUE Logger capture nothing SEVERE 1000 Serious failure
	 * WARNING 900 Potential problem INFO 800 General info CONFIG 700 Configuration
	 * info FINE 500 General developer info FINER 400 Detailed developer info FINEST
	 * 300 Specialized developer info ALL Integer.MIN_VALUE Logger capture
	 * everything
	 */
	public static void printlogMessages() throws SecurityException, IOException {
		logger.setLevel(Level.INFO); // indicate the levels we will capture
		logger.log(Level.SEVERE, "Uh Oh");
		logger.log(Level.INFO, "Just so you know");
		logger.log(Level.FINE, "Evertyhing will be fine");
		logger.log(Level.FINEST, "Stay at home");

		doWork();
		doWork("Stay", "positive");
		handlePersonalized();
		handleLogFile();
	}

	private static void doWork() {
		logger.entering("com.jwhh.support.Other", "doWork");
		logger.logp(Level.WARNING, "com.jwhh.support.Other", "doWork", "Empty Function");
		logger.exiting("com.jwhh.support.Other", "doWork");
	}

	private static void doWork(String left, String right) {
		logger.entering("com.jwhh.support.Other", "doWork", new Object[] { left, right });
		String result = "<" + left + right + ">";
		// level FINE
		logger.exiting("com.jwhh.support.Other", "doWork", result);
	}

	private static void handlePersonalized() {
		Handler h = new ConsoleHandler();
		Formatter f = new SimpleFormatter();
		h.setFormatter(f);
		personalizedLogger.addHandler(h);
		personalizedLogger.setLevel(Level.FINER);
		personalizedLogger.log(Level.INFO, "We're Logging");

		// Configure the child logger
		// Entering records information in FINER level
		// If not configured the log can be captured by the child and the parent
		classLogger.entering("logSystemMngmt.DoLogCalls", "handleClassLogs");
	}

	private static void handleLogFile() throws SecurityException, IOException {
		FileHandler fh = new FileHandler("%h/myapp_%g.log", 1000, 4);
		fh.setFormatter(new SimpleFormatter());
	}

}
