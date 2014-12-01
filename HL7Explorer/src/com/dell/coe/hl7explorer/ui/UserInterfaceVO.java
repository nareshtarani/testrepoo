package com.dell.coe.hl7explorer.ui;

import javax.swing.JTable;

public class UserInterfaceVO {
	
	private String[] arrayAllMessagesCols = {"All Messages"};
	private String[][] arrayAllMessages = {};
	
/*
 *{ 
{"MSH|^~&|MS4ADT|1|CONNEXION|A|20131015120012||ADT^A01|00000000015588107|P|2.3 EVN|A01|20131015120007|||YFRAZIER PID|1|004264575|10059606||BOYLE^MABEL^L^^||19370305|F||A|14810 GLENWOOD DRIVE^^MAGALIA^CA^95954^^^BUTTE       004|004|(530)873-5241||EN|M|NO|204857163||||NON|||||"},
{"MSH|^~&|MS4ADT|2|CONNEXION|B|20131015120012||ADT^A01|00000000015588107|P|2.3"},
{"MSH|^~&|MS4ADT|3|CONNEXION|C|20131015120012||ADT^A01|00000000015588107|P|2.3"}
};
*/
	
	JTable tblMasterTable = new JTable(arrayAllMessages, arrayAllMessagesCols){
		@Override
		public boolean isCellEditable(int row, int column) {
			return column == 0 ? false : true;
		}
	};

	
	

}
