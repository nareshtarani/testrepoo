package com.dell.coe.hl7explorer.common;

import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is used to log the messages and is based on common logging API.
 * @author Deepjyot Singh
 */
public class MainLogger {
	
	private static final Log log = LogFactory.getLog(MainLogger.class);

	public static void log(Log logger, String key, int logLevel) {
		switch (logLevel) {
		case Constants.LOGLEVEL_DEBUG:

			if (logger.isDebugEnabled()) {
				logger.debug(retrieveMessage(key));
			}
			break;

		case Constants.LOGLEVEL_INFO:

			if (logger.isInfoEnabled()) {
				logger.info(retrieveMessage(key));
			}
			break;
		case Constants.LOGLEVEL_WARN:

			if (logger.isWarnEnabled()) {
				logger.warn(retrieveMessage(key));
			}
			break;
		case Constants.LOGLEVEL_ERROR:

			if (logger.isErrorEnabled()) {
				logger.error(retrieveMessage(key));
			}
			break;
		case Constants.LOGLEVEL_FATAL:

			if (logger.isFatalEnabled()) {
				logger.fatal(retrieveMessage(key));
			}
			break;
		default:
			if (logger.isInfoEnabled()) {
				logger.info(retrieveMessage(key));
			}
			break;
		}
	}

	public static void logMessage(Log logger, String logMessage, int logLevel) {
		switch (logLevel) {
		case Constants.LOGLEVEL_DEBUG:

			if (logger.isDebugEnabled()) {
				logger.debug(logMessage);
			}
			break;

		case Constants.LOGLEVEL_INFO:
			if (logger.isInfoEnabled()) {
				logger.info(logMessage);
			}
			break;
		case Constants.LOGLEVEL_WARN:

			if (logger.isWarnEnabled()) {
				logger.warn(logMessage);
			}
			break;
		case Constants.LOGLEVEL_ERROR:

			if (logger.isErrorEnabled()) {
				logger.error(logMessage);
			}
			break;
		case Constants.LOGLEVEL_FATAL:

			if (logger.isFatalEnabled()) {
				logger.fatal(logMessage);
			}
			break;
		default:
			if (logger.isInfoEnabled()) {
				logger.info(logMessage);
			}
			break;
		}
	}
	/**
	 * Property which gets loaded from the flat file, used to store the log
	 * messages.
	 */
	 private static Properties props;

	public static void setProperties(Properties prop){
		props = prop;
	}

	public static String retrieveMessage(String key) {
		String value = null;
		try {
			value = (String) props.getProperty(key);
		} catch (Throwable th) {
			logMessage(log
					, "Unable to retreive message"
					, Constants.LOGLEVEL_WARN);
		}
		if (value != null) {
			return value;
		} else {
			return key;
		}

	}
}
