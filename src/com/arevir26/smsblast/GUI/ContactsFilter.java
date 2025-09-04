package com.arevir26.smsblast.GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ContactsFilter extends JPanel  {

	protected JTextField searchField;
	protected JComboBox<String> groupFilter;
	protected GridBagConstraints cons;
	protected DefaultComboBoxModel<String> groupListModel;
	protected List<FilterListener> listeners;
	private String searchtext, grouptext;
	
	public DefaultComboBoxModel<String> getGroupListModel() {
		return groupListModel;
	}

	public void setGroupListModel(DefaultComboBoxModel<String> groupListModel) {
		this.groupListModel = groupListModel;
		groupFilter.setModel(this.groupListModel);
	}

	public ContactsFilter() {
		listeners = new ArrayList<ContactsFilter.FilterListener>();
		initialize();
	}
	
	private void initialize() {
		searchField = new JTextField();
		groupFilter = new JComboBox<String>();
		groupListModel = new DefaultComboBoxModel<String>();
		this.groupFilter.setModel(groupListModel);
		groupListModel.addElement("");
		
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
		
		groupFilter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onFilterGroupChanged();
			}
		});
		searchField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				onSearchFieldChanged();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				onSearchFieldChanged();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				onSearchFieldChanged();
			}
		});
		
	}
	
	public void removeFilterListener(FilterListener l) {
		this.listeners.remove(l);
	}
	
	public void addFilterListener(FilterListener l) {
		this.listeners.add(l);
	}
	

	
	private void onSearchFieldChanged() {
		String newSearch = searchField.getText();
		if(newSearch==searchtext) return; // no update
		for(FilterListener listener : listeners) {
			listener.onSearchBoxChanged(newSearch);
		}
		this.searchtext = newSearch;
	}
	
	private void onFilterGroupChanged() {
		String groupname = groupListModel.getElementAt(groupFilter.getSelectedIndex());
		if(grouptext==groupname) return;//no update
		for(FilterListener listener : listeners) {
			listener.onGroupFilterChanged(groupname);
		}
		grouptext = groupname;
	}

	
	public static interface FilterListener{
		public void onGroupFilterChanged(String groupname);
		public void onSearchBoxChanged(String search);
		
	}
}
