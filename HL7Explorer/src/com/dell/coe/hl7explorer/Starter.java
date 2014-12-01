package com.dell.coe.hl7explorer;

import java.awt.ComponentOrientation;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;



import com.dell.coe.hl7explorer.common.Constants;
import com.dell.coe.hl7explorer.common.MainLogger;
import com.dell.coe.hl7explorer.common.Utils;
import com.dell.coe.hl7explorer.ui.ExplorerUI;

public class Starter {

	private static final Log log = LogFactory.getLog(Starter.class);
	

	/**
	 * Main Class for HL7 Explorer.
	 * @author Tikam Ahuja
	 */
	
	
	
	
	public static void main(String[] args) {
		
		

		// Initialize Logging
		try {
				PropertyConfigurator.configureAndWatch(Constants.LOG4J_CONFIG_FILE_PROP_ID, Constants.LOG4J_DELAY);
				MainLogger.setProperties(Utils.loadProperties(Constants.LOGMSG_CONFIG_FILE_PROP_ID));
				MainLogger.logMessage(log, "************ Logging Initialized Successfully ************", Constants.LOGLEVEL_DEBUG);
			}
			catch (IOException e)
			{
				System.exit(1);
			}

		
		try
		{
			
			ExplorerUI myUI = new ExplorerUI();
			JPanel finalPanel = myUI.createBasePanel();
			myUI.registerListeners();
			
			
			JFrame frame = new JFrame("HL7 Explorer " + Constants.RELEASE_VERSION);
			frame.setIconImage(new ImageIcon("images/mail_send.png").getImage());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
			frame.setPreferredSize(new Dimension (1020,760));
			System.out.println("width" + Toolkit.getDefaultToolkit().getScreenSize().width);
			System.out.println("height" + Toolkit.getDefaultToolkit().getScreenSize().height);
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);
		//	frame.set
			try
			{
				MainLogger.logMessage(log, "Setting JMenuBar on GUI ", Constants.LOGLEVEL_DEBUG);
				//frame.setJMenuBar(getMainToolBar());
			}
			catch (Exception e)
			{
				MainLogger.logMessage(log, "Failed to Set MenuBar", Constants.LOGLEVEL_DEBUG);
			}
			MainLogger.logMessage(log, "JMenuBar inserted on GUI successfully ", Constants.LOGLEVEL_DEBUG);
			frame.setContentPane(finalPanel);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		catch (Exception e)
		{
			System.out.println(e.getStackTrace());
			System.exit(1);
		}
		
		
		
	}
	
	
	
	/* 
	 * This function is not in use. Can be deleted later.
	 * */
	public static JMenuBar getMainToolBar () throws Exception
	{
		//JMenuBar
		JMenuBar menubar = new JMenuBar();
		JMenuItem itemNewFile,itemOpenFile,itemSave,itemFilter,itemReports,itemHelp,itemExit;

		//Items on JMenuBar
		
		itemNewFile= new JMenuItem("New File", new ImageIcon("images/New.png"));
		//itemNewFile.setVerticalTextPosition(SwingConstants.TOP);
	
		
		itemOpenFile = new JMenuItem(new ImageIcon("images/Open.png"));
		itemSave= new JMenuItem(new ImageIcon("images/Save.png"));
		itemFilter = new JMenuItem(new ImageIcon("images/Filter.png"));
		itemReports = new JMenuItem(new ImageIcon("images/Reports.png"));
		itemHelp=new JMenuItem(new ImageIcon("images/help.png"));
		itemExit=new JMenuItem(new ImageIcon("images/Exit.png"));

		JMenuBar.setDefaultLocale(null);
		
		
		//ToolTipText and Orientation of menuItems
		itemReports.setToolTipText("Click to see the options");
	
	/*	itemNewFile.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		itemOpenFile.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		itemSave.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		itemFilter.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		itemReports.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		itemHelp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);	
		*/

		menubar.add(Box.createHorizontalGlue());
		//Adding items to JMenuBar
		menubar.add(itemNewFile);
		menubar.add(itemOpenFile); 	
		menubar.add(itemSave);	
		menubar.add(itemFilter); 	
		menubar.add(itemReports); 	
		menubar.add(itemHelp);
		menubar.add(itemExit);

		

		
		itemHelp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
					MainLogger.logMessage(log, "Opening help document ", Constants.LOGLEVEL_INFO);
				//	Desktop.getDesktop().open(new File(Constants.CONNEXION_AUTOMATION_APPROACH_FILE));
				
			} });
		
		
		itemExit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);
			} });
	
	
		
		return menubar;
	}

	
	

}
