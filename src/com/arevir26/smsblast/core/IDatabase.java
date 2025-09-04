package com.arevir26.smsblast.core;

import java.util.List;

import com.arevir26.smsblast.Data.Contact;
import com.arevir26.smsblast.Data.SmsTemplate;

public interface IDatabase {
	public List<SmsTemplate> getTemplates();
	public boolean addTemplate(SmsTemplate template);
	public boolean addContact(Contact contact);
	public boolean addGroup(String name);
	public List<String> getGroupList();
	public void addDataChageListener(DataChangeListener listener);
	public List<Contact> getContacts();
	
	public static interface DataChangeListener{
		public void onGroupDataChanged(IDatabase db);
		public void onContactDataChanged(IDatabase db);
	}
}
