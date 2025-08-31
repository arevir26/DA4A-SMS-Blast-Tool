package com.arevir26.smsblast.GUI;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataListener;

import com.arevir26.smsblast.Data.Contact;

public class AddContactPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7980224522985341662L;

	protected JTextField nameField,  contactField;
	protected JButton addNewBtn;
	protected JScrollPane groupScrollPane;
	protected JList<String> groups;
	protected DefaultListModel<String> groupDataModel;
	
	protected GridBagConstraints cons;
	protected List<ContactActionListener> listeners;
	
	public AddContactPanel() {
		intitialize();
		listeners = new ArrayList<>();
	}
	
	public void addGroupDalaListener(ListDataListener listener) {
		groupDataModel.addListDataListener(listener);
	}

	private void intitialize() {
		cons = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		nameField = new JTextField();
		
		groups = new JList<String>();
		groupScrollPane = new JScrollPane(groups);
		groupDataModel = new DefaultListModel<>();
		groups.setModel(groupDataModel);
		groups.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		groups.setLayout(new FlowLayout());
		groups.setVisibleRowCount(10);
		for(int i=0; i < 100; i++) {
			groupDataModel.addElement("Group" + i);
		}
		contactField = new JTextField();
		addNewBtn = new JButton("Savet");
		
		cons.gridy = 0;
		cons.gridx = 0;
		cons.weightx = 1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		add(new JLabel("Name:"),cons);
		cons.weighty=0;
		cons.gridx = 0;
		cons.gridy ++;
		add(nameField, cons);
		
		
		cons.gridy ++;
		add(new JLabel("Contact Number:"), cons);
		cons.gridy ++;
		add(contactField, cons);
		
		cons.gridy ++;
		add(new JLabel("Groups:"), cons);
		cons.gridy++;
		add(groupScrollPane, cons);
		
		
		cons.gridy ++;
		add(addNewBtn, cons);
		
		this.addNewBtn.addActionListener(this);
		
	}
	
	public void addListener(ContactActionListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeListener(ContactActionListener listener) {
		this.listeners.remove(listener);
	}
	
	
	
	public static interface ContactActionListener{
		public void onContactAdded(Contact contact);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()!=addNewBtn)return;
		String name = nameField.getText().trim();
		String number = contactField.getText().trim();
		Contact contact = new Contact(name, number);
		contact.groups = groups.getSelectedValuesList();
		for(ContactActionListener listener: listeners) {
			listener.onContactAdded(contact);
		}
	}
}
