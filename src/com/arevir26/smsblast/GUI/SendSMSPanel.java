package com.arevir26.smsblast.GUI;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.arevir26.smsblast.Data.DataManager;

public class SendSmsPanel extends JPanel{
	
	protected JComboBox<String> selectTemplate;
	
	public SendSmsPanel() {
		init();
	}
	
	private void init() {
		setBackground(Color.WHITE);
		selectTemplate = new JComboBox<String>();
		String[] templatelist = DataManager.getInstance().getTemplateList();
		selectTemplate.setModel(new DefaultComboBoxModel<String>(templatelist));
		
		add(selectTemplate);
	}
}
