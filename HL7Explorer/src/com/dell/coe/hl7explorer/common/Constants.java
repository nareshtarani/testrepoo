package com.dell.coe.hl7explorer.common;

/**
 * Constants for HL7 Explorer .
 * @author Tikam Ahuja 
 */

public interface Constants{

	public static String EMPTY_STRING = "".intern();
	//Log Levels
	public static final int LOGLEVEL_DEBUG = 0;
	public static final int LOGLEVEL_INFO = 1;
	public static final int LOGLEVEL_WARN = 2;
	public static final int LOGLEVEL_ERROR = 3;
	public static final int LOGLEVEL_FATAL = 4;

	/*The delay in millisecond after which
	the change in log level will be effective*/
	public static final int LOG4J_DELAY = 60000;
	
	// Prop Files
	public static final String LOGMSG_CONFIG_FILE_PROP_ID = "resources/logMessages.properties";  //Log4j Configuration Constants
	public static final String LOG4J_CONFIG_FILE_PROP_ID = "resources/log4j.properties";
	

	//File extensions
	public static String DAT_EXT = ".dat";
	public static String TXT_EXT = ".txt";
	public static String TXT_log = ".log";
	
	
	// HL7 Explorer Release Version
	public static String RELEASE_VERSION = "v1.0";
	

}
