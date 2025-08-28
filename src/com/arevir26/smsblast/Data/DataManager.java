package com.arevir26.smsblast.Data;

import java.awt.Dimension;
import java.io.File;
import java.net.URI;

import com.arevir26.smsblast.core.IDatabase;
import com.arevir26.smsblast.core.SqliteDatabase;

public class DataManager {
	private static DataManager instance;
	private IConfig config;
	private IDatabase database;
	private DataManager() {
		database = new SqliteDatabase();
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
	
	public String getBearer() {
		return "sdjfsdljflsdfjlsdfldj";
	}
	
	public URI getApiUri() {
		try {
			URI uri = new URI("http://www.bantaypresyo.da.gov.ph");
			return uri;
		} catch (Exception e) {
			e.printStackTrace();
		};
		return null;
	}
	
	public String getTemplate(File file) {
		
		return "Message: {Sugar (Washed)}";
	}
	
	
}
