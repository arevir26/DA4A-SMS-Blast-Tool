package com.arevir26.smsblast.GUI;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import com.arevir26.smsblast.Data.Contact;
import com.arevir26.smsblast.Data.DataManager;
import com.arevir26.smsblast.GUI.AddContactPanel.ContactActionListener;
import com.arevir26.smsblast.GUI.AddGroupPanel.AddGroupListener;
import com.arevir26.smsblast.core.IDatabase;

public class ContactsPanel extends JPanel implements ContactActionListener, AddGroupListener{
	protected AddContactPanel addContactPanel;
	protected AddGroupPanel addGroupPanel;
	protected GridBagConstraints cons;
	
	public ContactsPanel() {
		intitialize();
	}

	private void intitialize() {
		cons = new GridBagConstraints();

		addContactPanel = new AddContactPanel();
		addGroupPanel = new AddGroupPanel();
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1;
		add(addContactPanel, cons);
		
		cons.gridy ++;
		add(addGroupPanel, cons);
		
		addContactPanel.addListener(this);
		addGroupPanel.setAddGroupListener(this);
		
	}

	@Override
	public void onContactAdded(Contact contact) {
		IDatabase db = DataManager.getInstance().getCurrentDatabase();
		db.addContact(contact);
	}

	@Override
	public boolean onGroupAdded(String group) {
		return DataManager.getInstance().getCurrentDatabase().addGroup(group);
	}
}
