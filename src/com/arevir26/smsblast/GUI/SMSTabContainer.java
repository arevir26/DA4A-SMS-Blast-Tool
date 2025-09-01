package com.arevir26.smsblast.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListModel;

import com.arevir26.smsblast.GUI.Custom.CheckedBoxContact;
import com.arevir26.smsblast.GUI.Custom.CustomList;
import com.arevir26.smsblast.GUI.Custom.CustomList.CheckBoxItem;
import com.arevir26.smsblast.GUI.Custom.CustomListModel;

public class SMSTabContainer extends JPanel{
	private JSplitPane splitpane;
	private SendSMSPanel sendpanel;
	private GridBagConstraints cons;
	private JScrollPane contactlistScrollPane;
	
	private ContactSelectionPanel contactsPanel;
	
	public SMSTabContainer() {
		splitpane = new JSplitPane();
		sendpanel = new SendSMSPanel();
		contactsPanel = new ContactSelectionPanel();
		
		//test
		CustomListModel model = new CustomListModel();
		contactsPanel.getListPanel().setListModel(model);
		for(int i = 0; i < 20; i++) {
			CheckedBoxContact contact = new CheckedBoxContact("RJ", " "+i);
			contact.groups = new ArrayList<String>();
			contact.groups.add("Tanza");
			model.addData(new CheckedBoxContact("RJ", ""+i));
		}
		
		
		contactlistScrollPane = new JScrollPane(contactsPanel);
		
		splitpane.setLeftComponent(sendpanel);
		splitpane.setRightComponent(contactlistScrollPane);
		
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
