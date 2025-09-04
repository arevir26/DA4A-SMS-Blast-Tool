package com.arevir26.smsblast.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.arevir26.smsblast.Data.DataManager;
import com.arevir26.smsblast.GUI.Custom.CustomListModel;
import com.arevir26.smsblast.core.IDatabase;
import com.arevir26.smsblast.core.IDatabase.DataChangeListener;

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
		
		List<String> groupdata = DataManager.getInstance().getCurrentDatabase().getGroupList();
		contactsPanel.getFilterPanel().groupListModel.addAll(groupdata);
		CustomListModel selectionmodel = contactsPanel.getListPanel().getListModel();
		
		contactsPanel.getListPanel().getListModel().setData(DataManager.getInstance().getCurrentDatabase().getContacts());
		DataManager.getInstance().getCurrentDatabase().addDataChageListener(new DataChangeListener() {
			
			@Override
			public void onGroupDataChanged(IDatabase db) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onContactDataChanged(IDatabase db) {
				contactsPanel.getListPanel().getListModel().setData(db.getContacts());
				
			}
		});
		
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
