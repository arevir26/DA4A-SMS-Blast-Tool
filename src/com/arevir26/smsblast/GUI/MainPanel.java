package com.arevir26.smsblast.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.arevir26.smsblast.Data.DataManager;
import com.arevir26.smsblast.Data.IConfig;
import com.arevir26.smsblast.core.MessageSender;

public class MainPanel extends JFrame{
	
	private static final String WINDOW_TITLE = "Bantay Presyo SMS Blast Tool";
	
	protected JTabbedPane tabpane;
	protected SMSTabContainer smspanel;
	protected ContactsPanel contactspanel;
	
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
		MessageSender sender = new MessageSender();
		tabpane = new JTabbedPane();
		smspanel = new SMSTabContainer();
		contactspanel = new ContactsPanel();
		tabpane.add("Send SMS", smspanel);
		tabpane.add("Contacts", contactspanel);
		add(tabpane);
	}
}
