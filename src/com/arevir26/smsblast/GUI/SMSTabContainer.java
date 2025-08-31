package com.arevir26.smsblast.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListModel;

import com.arevir26.smsblast.GUI.Custom.CheckedBoxContact;
import com.arevir26.smsblast.GUI.Custom.CustomList;
import com.arevir26.smsblast.GUI.Custom.CustomList.CheckBoxItem;

public class SMSTabContainer extends JPanel{
	private JSplitPane splitpane;
	private SendSMSPanel sendpanel;
	private GridBagConstraints cons;
	private JScrollPane contactlistScrollPane;
	//private ContactSelectionPanel contactlistPanel;
	private CustomList<CheckedBoxContact> contactlist;
	
	public SMSTabContainer() {
		splitpane = new JSplitPane();
		sendpanel = new SendSMSPanel();
		//contactlistPanel = new ContactSelectionPanel();
		contactlist = new CustomList<>();
		DefaultListModel<CheckedBoxContact> model = new DefaultListModel<CheckedBoxContact>();
		contactlist.setListModel(model);
		model.addElement(new CheckedBoxContact("Ryan", "Joseph"));
		
		contactlistScrollPane = new JScrollPane(contactlist);
		
		splitpane.setLeftComponent(sendpanel);
		splitpane.setRightComponent(contactlistScrollPane);
		
//		DefaultListModel<CustomListItem> items = new DefaultListModel<CustomListItem>();
//		items.add(0, new CustomListItem(true, "sdfsdf", "dddddd"));
//		for(int i=0; i < 200; i++) {
//			items.addElement(new CustomListItem(false, "Name" + i, " contact" , "Tanza"));
//		}
//		
//		contactlistPanel.setListModel(items);
		
		cons = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.gridwidth = 2;
		
		add(splitpane, cons);
	}
}
