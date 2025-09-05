package com.arevir26.smsblast.core;

import java.util.List;

import com.arevir26.smsblast.Data.APICredentials;
import com.arevir26.smsblast.Data.Contact;

public interface IDatabase {
	public boolean addContact(Contact contact);
	public boolean addGroup(String name);
	public List<String> getGroupList();
	public void addDataChageListener(DataChangeListener listener);
	public List<Contact> getContacts(DataFilter filter);
	public APICredentials getApiCredential(String keyname);
	
	
	public static interface DataChangeListener{
		public void onGroupDataChanged(IDatabase db);
		public void onContactDataChanged(IDatabase db);
	}
	
	public static interface DataFilter{
		public String getGroupName();
		public String getSearchText();
	}
}
