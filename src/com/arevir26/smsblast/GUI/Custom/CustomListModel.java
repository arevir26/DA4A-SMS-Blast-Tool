package com.arevir26.smsblast.GUI.Custom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class CustomListModel implements ListModel<CheckedBoxContact>{
	
	protected List<CheckedBoxContact> data;
	protected List<ListDataListener> listeners;
	
	public CustomListModel() {
		data = new ArrayList<CheckedBoxContact>();
		listeners = new ArrayList<ListDataListener>();
	}
	
	public void setData(List<CheckedBoxContact> d) {
		this.data = d;
	}
	
	private void dataUpdated(ListDataEvent event) {
		for(ListDataListener listener : listeners) {
			listener.contentsChanged(event);
		}
	}
	
	public void addData(CheckedBoxContact d) {
		data.add(d);
		dataUpdated(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, data.size(), data.size()));
	}
	
	public void removeData(CheckedBoxContact d) {
		this.data.remove(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, data.size(), data.size()));
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
