package com.arevir26.smsblast.GUI;

import javax.swing.JFrame;

import com.arevir26.smsblast.Data.DataManager;
import com.arevir26.smsblast.Data.IConfig;

public class MainPanel extends JFrame{
	
	private static final String WINDOW_TITLE = "Bantay Presyo SMS Blast Tool";
	
	public MainPanel() {
		setWindowSize();
		setTitle(WINDOW_TITLE);
	}
	
	private void setWindowSize() {
		IConfig config = DataManager.getInstance().getConfig();
		setSize(config.getPreferredWindowSize());
		if(config.isFullScreen()) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			return;
		}
		
	}

}
