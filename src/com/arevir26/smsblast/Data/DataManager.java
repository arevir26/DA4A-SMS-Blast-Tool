package com.arevir26.smsblast.Data;

import java.awt.Dimension;

public class DataManager {
	private static DataManager instance;
	private IConfig config;
	private DataManager() {
		
	}
	
	public IConfig getConfig() {
		if(config==null) {
		return new IConfig() {
			
			@Override
			public boolean isFullScreen() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public Dimension getPreferredWindowSize() {
				// TODO Auto-generated method stub
				return new Dimension(1024, 768);
			}
		};
		}
		return config;
	}
	
	public static DataManager getInstance() {
		if(instance!=null)return instance;
		instance = new DataManager();
		return instance;
	}
	
	public String[] getTemplateList() {
		//returns dummy data
		return new String[] {"Default Template", "Template 2"};
	}
	
	
}
