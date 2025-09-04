package com.arevir26.smsblast.GUI.Custom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.arevir26.smsblast.Data.Contact;

public class CustomListModel implements ListModel<CheckedBoxContact>{
	
	protected List<CheckedBoxContact> data;
	protected List<ListDataListener> listeners;
	
	public CustomListModel() {
		data = new ArrayList<CheckedBoxContact>();
		listeners = new ArrayList<ListDataListener>();
	}
	
	public void setData(List<Contact> d) {
		for(Contact c : d) {
			CheckedBoxContact newContact = new CheckedBoxContact(c.name, c.number);
			newContact.groups = c.groups;
			data.add(newContact);
		}
		dataUpdated(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, getSize(), getSize()));
	}
	
	
	private void dataUpdated(ListDataEvent event) {
		for(ListDataListener listener : listeners) {
			listener.contentsChanged(event);
		}
	}
	

	public void addData(Contact d) {
		CheckedBoxContact newContact = new CheckedBoxContact(d.name, d.number);
		newContact.groups = d.groups;
		data.add(newContact);
		dataUpdated(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, getSize(), getSize()));
	}
	
	public void removeData(CheckedBoxContact d) {
		this.data.remove(d);
		dataUpdated(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, getSize(), getSize()));
	}

	@Override
	public int getSize() {
		return data.size();
	}

	@Override
	public CheckedBoxContact getElementAt(int index) {
		return data.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}

}
