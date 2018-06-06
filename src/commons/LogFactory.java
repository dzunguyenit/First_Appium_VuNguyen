package commons;

import org.apache.log4j.Logger;

public class LogFactory {

    private static Logger log = Logger.getLogger(LogFactory.class.getName());

	public static void info(String message) {
		log.info(message);
		System.out.println(message);
	}

	public static void error(String message) {
		log.error(message);
		System.out.println(message);
	}

}