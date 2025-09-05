package com.arevir26.smsblast.GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.arevir26.smsblast.GUI.Custom.CheckedBoxContact;
import com.arevir26.smsblast.GUI.Custom.CustomList;

public class ContactSelectionPanel extends JPanel{
	
	protected CustomList<CheckedBoxContact> listPanel;
	protected ContactsFilter filterPanel;
	public CustomList<CheckedBoxContact> getListPanel() {
		return listPanel;
	}

	public ContactsFilter getFilterPanel() {
		return filterPanel;
	}

	protected GridBagConstraints cons;
	
	public ContactSelectionPanel() {
		initialize();
	}
	
	protected void initialize() {
		listPanel = new CustomList<CheckedBoxContact>();
		filterPanel = new ContactsFilter();
		cons = new GridBagConstraints();
		
		this.setLayout(new GridBagLayout());
		
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.anchor = GridBagConstraints.NORTH;
		add(filterPanel, cons);
		
		cons.gridy ++;
		cons.weighty = 1;
		
		add(listPanel, cons);
	}

}
