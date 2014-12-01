package com.dell.coe.hl7explorer.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.dell.coe.hl7explorer.common.Constants;
import com.dell.coe.hl7explorer.common.MainLogger;
import com.dell.coe.hl7explorer.common.Utils;


/**
 * GUI for HL7 Explorer .
 * @author Tikam Ahuja 
 */

public class ExplorerUI {
		private static final Log log = LogFactory.getLog(ExplorerUI.class);
		private JLabel lbConnectionDetails;
		private JButton btnNew, btnOpen, btnSave,btnReports, btnFilter, btnSearch, btnExit; 
		public DefaultTableModel dtmMessages ;
		public DefaultTableModel dtmSegments ;
		public DefaultTableModel dtmFields ;
		
	public JPanel createBasePanel (){
			
		//Setting Look and Feel...
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} 
		catch (Exception e)
		{
			MainLogger.logMessage(log, "Unable to set look and feel", Constants.LOGLEVEL_WARN);
		}
		MainLogger.logMessage(log, "Building User Interface", Constants.LOGLEVEL_INFO);

		
		final JPanel gui = new JPanel(new BorderLayout(5,5));
		gui.setBorder( new TitledBorder("") );
		
		//Add and arrange components
		
		JPanel toolbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3,3));
		toolbarPanel.setBorder(BorderFactory.createTitledBorder(" "));
		GridBagLayout gbLayout = new GridBagLayout();

		 btnNew = new JButton("New", new ImageIcon("images/New.png"));
		 btnOpen = new JButton("Open", new ImageIcon("images/Open.png"));
		 btnSave = new JButton("Save", new ImageIcon("images/Save.png"));
		 btnFilter = new JButton("Filter Records", new ImageIcon("images/Filter.png"));
		 btnReports = new JButton("Reports", new ImageIcon("images/Reports.png"));
		 btnSearch = new JButton("Search", new ImageIcon("images/Pie.png"));
		 btnExit = new JButton("Exit", new ImageIcon("images/Exit.png"));
		
		addItem(toolbarPanel , btnNew , 0, 0, 40, 30, GridBagConstraints.CENTER);
		addItem(toolbarPanel , btnOpen , 40, 0, 40, 30, GridBagConstraints.CENTER);
		addItem(toolbarPanel , btnSave , 80, 0, 40, 30, GridBagConstraints.CENTER);
		addItem(toolbarPanel , btnFilter , 120, 0, 40, 30, GridBagConstraints.CENTER);
		addItem(toolbarPanel , btnReports , 160, 0, 40, 30, GridBagConstraints.CENTER);
		addItem(toolbarPanel , btnSearch , 200, 0, 40, 30, GridBagConstraints.CENTER);
		addItem(toolbarPanel , btnExit , 240, 0, 40, 30, GridBagConstraints.CENTER);		
		addItem(toolbarPanel , btnExit , 280, 0, 40, 30, GridBagConstraints.CENTER);
		 
		gui.add(toolbarPanel, BorderLayout.NORTH); 
		
		//Creating left Panel
		JPanel leftPanel = new JPanel(new BorderLayout(4,4));
		leftPanel.setBorder(new TitledBorder("") );
		gui.add(leftPanel, BorderLayout.WEST);
		
		Font font = new Font(Font.SERIF, Font.BOLD, 12);
		TitledBorder border = BorderFactory.createTitledBorder("TCP/IP Connection");
		border.setTitleFont(font);
		JPanel baseTcpIpPanel = new JPanel();
		baseTcpIpPanel.setBorder(border);
		baseTcpIpPanel.setLayout(new GridLayout(3,4));

		//TCP IP Panel Items
		JLabel lblConn = new JLabel("Select Connection:");
		baseTcpIpPanel.add(lblConn );
		JLabel lblIP = new JLabel("IP/Host Name:");
		baseTcpIpPanel.add( lblIP );
		JTextField txtIP = new JTextField();
		baseTcpIpPanel.add( txtIP );
		JRadioButton rbClient = new JRadioButton("Client (send)");
		JRadioButton rbServer = new JRadioButton("Server (recv)");
		ButtonGroup group = new ButtonGroup();
		group.add(rbClient);
		group.add(rbServer);
		baseTcpIpPanel.add( rbClient );

		JLabel lblPort = new JLabel("Port:");
		baseTcpIpPanel.add( lblPort );
		JTextField txtPort = new JTextField();
		baseTcpIpPanel.add( txtPort );
		baseTcpIpPanel.add( rbServer);				
		JButton btnStart = new JButton("Start");
		baseTcpIpPanel.add( btnStart, BorderLayout.CENTER );
		JButton btnStop = new JButton("Stop");
		baseTcpIpPanel.add( btnStop, BorderLayout.CENTER );
		
		System.out.println("Added TCP IP Panel");
		
		// Creating table for displaying all messages in left bottom
		String[] arrayMessagesCols = {"All Messages"};
		Object[][] arrayMessages = {{""}};
		
		// JTable tblMasterTable = new JTable(arrayAllMessages, arrayAllMessagesCols){
		dtmMessages = new DefaultTableModel(arrayMessages, arrayMessagesCols);
		JTable tblMessages = new JTable(dtmMessages){
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0 ? false : true;
			}
		};
		dtmMessages.setColumnIdentifiers(arrayMessagesCols);
		tblMessages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		JScrollPane scpMessagesTable = new JScrollPane(tblMessages);
		Dimension tablePreferredAll = scpMessagesTable.getPreferredSize();
		scpMessagesTable.setPreferredSize(new Dimension(tablePreferredAll.width, tablePreferredAll.height/3) );

		// Left split panel
		JSplitPane leftsplitPane = new JSplitPane(
				JSplitPane.VERTICAL_SPLIT,
				new JScrollPane(baseTcpIpPanel),
				scpMessagesTable);
		leftPanel.add( leftsplitPane, BorderLayout.CENTER );
		
		/*LEFT PANEL COMPLETE*/
		
		
		//Creating right Panel
		JPanel rightPanel = new JPanel(new BorderLayout(4,4));
		leftPanel.setBorder(new TitledBorder("") );
		gui.add(rightPanel, BorderLayout.EAST);
		
		String[] arraySegmentsCols = {"Selected Message"}; 
		Object[][] arraySegments = {{""}};
		
		/*
				{"MSH|^~&|MS4ADT|164|CONNEXION|164|20131015120012||ADT^A01|00000000015588107|P|2.3"},
				{"EVN|A01|20131015120007|||YFRAZIER"},
				{"PID|1|004264575|10059606||BOYLE^MABEL^L^^||19370305|F||A|14810 GLENWOOD DRIVE^^MAGALIA^CA^95954^^^BUTTE       004|004|(530)873-5241||EN|M|NO|204857163||||NON|||||"}
				};
		*/
		
		dtmSegments = new DefaultTableModel(arraySegments, arraySegmentsCols);
		JTable tblSegments = new JTable(dtmSegments){
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0 ? false : true;
			}
		};
		tblSegments.setShowGrid(false);
		tblSegments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scpSegmentTable = new JScrollPane(tblSegments);
		Dimension tablePreferredSel = scpSegmentTable.getPreferredSize();
		scpSegmentTable.setPreferredSize(new Dimension(tablePreferredSel.width, tablePreferredSel.height/3) );

		
		
		// Creating detail view with segments & fields in right bottom
		String[] arrayFieldsCols = {"Field", "Description", "Value"}; 
		Object[][] arrayFields = {{""}};
		
		
		/*		{"MSH.1", "Field Seperator", "|"},
				{"MSH.2", "Encoding Characters", "^^"},
				{"MSH.3", "Sending Application", "ConneXion"}
		};*/

		
		dtmFields = new DefaultTableModel(arrayFields, arrayFieldsCols);
		JTable tblFields = new JTable(dtmFields){
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0 ? false : true;
			}
		};
		tblFields.setShowGrid(false);
		tblFields.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scpFieldsTable = new JScrollPane(tblFields);
		Dimension tablePreferredDet = scpFieldsTable.getPreferredSize();
		scpFieldsTable.setPreferredSize(new Dimension(tablePreferredDet.width, tablePreferredDet.height/3) );

		// Right Split Panel
		JSplitPane rightSplitPane = new JSplitPane(
				JSplitPane.VERTICAL_SPLIT,
				scpSegmentTable,
				scpFieldsTable);
		rightPanel.add( rightSplitPane, BorderLayout.CENTER );

		//MAIN SPLIT PANE
		JSplitPane splitPane = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT,
				leftPanel,
				rightPanel);
		gui.add( splitPane, BorderLayout.CENTER );
		return gui;
		
	}
	

	/*Adds components to the panels as per defined layout*/
	public static void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
		
		try{
		GridBagConstraints gc = new GridBagConstraints();
	
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.gridheight = height;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.anchor = align;
		gc.fill = GridBagConstraints.HORIZONTAL;
		p.add(c, gc);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public void registerListeners(){

		//private JButton btnNew, btnOpen, btnSave,btnReports, btnFilter, btnSearch, btnExit;
		
		//New or Clear button
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dtmMessages.setNumRows(0);
				dtmSegments.setNumRows(0);
				dtmFields.setNumRows(0);
				System.out.println("Reset the all objects and UI components.");
			}});
		
		
		//Open button
		btnOpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Utils.openFileDiaglogue("Input");
				
				dtmMessages.setNumRows(0);
				String [] messagesFromFile  ={"1234"};
				dtmMessages.addRow(messagesFromFile);
				// TODO: Parse the file and iterate the ArrayList <String> and add each row here.
				
				System.out.println("Open File");
			}});
		
		//Save button
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Save File");
			}});
	
		//Reports button
		btnReports.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Open a popup for Reports here");
			}});
		
		
		
		//Filter button
		btnFilter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Open a popup for Message Filter here");
			}});
		
		
		//Search button
		btnSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Open a popup for Searching Message in the File");
			}});
		
		
		//Exit button
		btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("\nClosing HL7 Explorer.");
				System.exit(0);
			}});

	}

}


