package com.arevir26.smsblast.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SendSMSPanel extends JPanel{
	private JSplitPane splitpane;
	private GridBagConstraints cons;
	
	public SendSMSPanel() {
		splitpane = new JSplitPane();
		cons = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.weightx = 1;
		cons.gridwidth = 2;
		
		add(splitpane, cons);
	}
}
