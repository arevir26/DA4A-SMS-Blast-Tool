package com.arevir26.smsblast.GUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import com.arevir26.smsblast.Data.MarketData;

public class MarketDataComboBoxModel implements ComboBoxModel<MarketData> {
	
	protected List<MarketData> marketdata;
	protected List<ListDataListener> listDataListeners;
	protected Object selectedItem;
	
	public MarketDataComboBoxModel(List<MarketData> data) {
		this.listDataListeners = new ArrayList<ListDataListener>();
		
		this.marketdata =data;
		
	}
	
	public void setMarketData(List<MarketData> data) {
		this.marketdata = data;
	}
	
	

	@Override
	public int getSize() {
		return marketdata.size();
	}

	@Override
	public MarketData getElementAt(int index) {
		// TODO Auto-generated method stub
		return marketdata.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		this.listDataListeners.add(l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		this.listDataListeners.remove(l);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.selectedItem = anItem;
	}

	@Override
	public Object getSelectedItem() {
		return this.selectedItem;
	}
	
	

}
