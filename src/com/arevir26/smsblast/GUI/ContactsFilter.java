package com.arevir26.smsblast.GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContactsFilter extends JPanel {
	
	protected JTextField searchField;
	protected JComboBox<String> groupFilter;
	protected GridBagConstraints cons;
	
	public ContactsFilter() {
		initialize();
	}
	
	private void initialize() {
		searchField = new JTextField();
		groupFilter = new JComboBox<String>();
		cons = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		cons.insets = new Insets(5, 5, 5, 5);
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.FIRST_LINE_START;
		cons.fill = GridBagConstraints.BOTH;
		cons.weightx = 0;
		cons.weighty = 0;
		add(new JLabel("Search:"), cons);
		
		cons.gridx ++;
		cons.weightx = 1;
		searchField.setSize(new Dimension(300, 20));
		add(searchField, cons);
		
		cons.gridx ++;
		cons.weightx = 1;
		cons.fill = GridBagConstraints.NONE;
		add(groupFilter, cons);
		
		
	}
	
	
}
