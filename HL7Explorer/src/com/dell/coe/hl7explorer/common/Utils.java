package com.dell.coe.hl7explorer.common;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hpsf.MarkUnsupportedException;
import org.apache.poi.hpsf.NoPropertySetStreamException;
import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.hpsf.PropertySetFactory;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hpsf.UnexpectedPropertySetTypeException;
import org.apache.poi.hpsf.WritingNotSupportedException;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * This class contains all the utility methods.
 * @author Tikam Ahuja
 */
public class Utils {

	private static final Log log = LogFactory.getLog(Utils.class);
	public static Date date = new Date();
	public static Calendar cal = Calendar.getInstance();


	public static Properties loadProperties(String propFileName) throws IOException {
		Properties props = null;
		MainLogger.logMessage(log, " ************ Opening Log File ************ ", Constants.LOGLEVEL_INFO);
		MainLogger.logMessage(log, "log4j Property File Name--->" + propFileName, Constants.LOGLEVEL_INFO);
		try {
			props = new Properties();
			props.load(new java.io.FileInputStream(propFileName));
		} catch (IOException e) {
			MainLogger.logMessage(log, "Unable to open log file" + propFileName, Constants.LOGLEVEL_ERROR);
		}
		return props;
	}
	
	public static String openFileDiaglogue (String whatToAsk)
 	{
		MainLogger.logMessage(log, "Retreiving Path...", Constants.LOGLEVEL_INFO);
 		
 		String fileName=null;
		int r;
		
		try	{
			JFileChooser chooser;
			chooser = new JFileChooser();					
			
			chooser.setCurrentDirectory(new java.io.File("."));				
			
			chooser.setDialogTitle("Select "+ whatToAsk +" File");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			r = chooser.showOpenDialog(new JFrame());
			if (r == JFileChooser.APPROVE_OPTION)
			{
				fileName = chooser.getSelectedFile().getPath();
				
			}
			MainLogger.logMessage(log, whatToAsk +" File selected- " + fileName, Constants.LOGLEVEL_INFO);
		} catch (Exception ee){
			String message = ee.getMessage();
			MainLogger.log(log, message, Constants.LOGLEVEL_ERROR);
		}
		MainLogger.logMessage(log, "Path Retreived Successfully", Constants.LOGLEVEL_INFO);
		
 		return fileName ;	 		
 	}

	
}
