package com.arevir26.smsblast.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.arevir26.smsblast.Data.DataManager;
import com.arevir26.smsblast.Data.IConfig;

public class MainPanel extends JFrame{
	
	private static final String WINDOW_TITLE = "Bantay Presyo SMS Blast Tool";
	
	protected JTabbedPane tabpane;
	protected SMSTabContainer smspanel;
	
	public MainPanel() {
		setWindowSize();
		setTitle(WINDOW_TITLE);
		
		initComponents();
	}
	
	private void setWindowSize() {
		IConfig config = DataManager.getInstance().getConfig();
		setSize(config.getPreferredWindowSize());
		if(config.isFullScreen()) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			return;
		}
		
	}
	
	private void initComponents(){
		tabpane = new JTabbedPane();
		smspanel = new SMSTabContainer();
		tabpane.add("Send SMS", smspanel);
		tabpane.add("Contacts", new JLabel("Contacts"));
		add(tabpane);
	}
}
