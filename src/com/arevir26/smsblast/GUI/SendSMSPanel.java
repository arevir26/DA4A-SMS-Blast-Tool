package com.arevir26.smsblast.GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class SendSMSPanel extends JPanel{
	
	protected FileSelectionPanel fileSelector;
	
	protected GridBagConstraints cons;
	
	
	
	public SendSMSPanel() {
		init();
	}
	
	private void init() {
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
		cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1;
		
		fileSelector = new FileSelectionPanel();
		add(fileSelector, cons);
	}
	
	
}
