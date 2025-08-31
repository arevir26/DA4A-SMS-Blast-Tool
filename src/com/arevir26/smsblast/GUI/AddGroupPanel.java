package com.arevir26.smsblast.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddGroupPanel extends JPanel implements ActionListener{
	protected JTextField groupNameField;
	protected JButton addButton;
	protected AddGroupListener listener;
	
	public AddGroupPanel() {
		initialize();
	}

	private void initialize() {
		groupNameField = new JTextField();
		addButton = new JButton("Add Group");
		setLayout(new BorderLayout());
		add(new JLabel("Group Name: "),BorderLayout.PAGE_START);
		add(groupNameField,BorderLayout.CENTER);
		add(addButton, BorderLayout.PAGE_END);
		
		this.addButton.addActionListener(this);
	}
	

	public void setAddGroupListener(AddGroupListener listener) {
		this.listener = listener;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String groupname = groupNameField.getText().trim();
		if(groupname.length()==0)return;
		if(e.getSource()==addButton && this.listener!=null) {
			if(listener.onGroupAdded(groupNameField.getText().trim())) {
				// clears the field if successfully added to database
				groupNameField.setText("");
			};
		}
	}
	
	public static interface AddGroupListener{
		public boolean onGroupAdded(String group);
	}


}
