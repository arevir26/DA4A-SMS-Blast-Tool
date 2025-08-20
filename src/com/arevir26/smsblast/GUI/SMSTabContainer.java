package com.arevir26.smsblast.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SMSTabContainer extends JPanel{
	private JSplitPane splitpane;
	private SendSmsPanel sendpanel;
	private GridBagConstraints cons;
	
	public SMSTabContainer() {
		splitpane = new JSplitPane();
		sendpanel = new SendSmsPanel();
		splitpane.setLeftComponent(sendpanel);
		
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
