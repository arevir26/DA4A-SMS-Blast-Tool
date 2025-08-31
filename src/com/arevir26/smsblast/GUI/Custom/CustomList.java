package com.arevir26.smsblast.GUI.Custom;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.ListModel;

import com.arevir26.smsblast.GUI.Custom.CustomList.CheckBoxItem;

public class CustomList<T extends CheckBoxItem> extends JPanel{
	
	protected ListModel<T> listModel;
	public ListModel<T> getListModel() {
		
		return listModel;
	}


	public void setListModel(ListModel<T> listModel) {
		this.listModel = listModel;
	}

	protected int columns = 2;

	public CustomList() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void renderItems() {
		for(int i=0; i < listModel.getSize(); i++) {
			T element = listModel.getElementAt(i);
			add(element.getCheckBox());
			for(int j = 0; j < columns; j++) {

				add(element.getLabelPart(this, i, j));
			}
		}
	}
	
	
	public List<T> getSelectedItems() {
		List<T> selected = new ArrayList<T>();
		for(int i=0; i < 0; i++) {
			T item = listModel.getElementAt(i);
			if(item.isChecked()) {
				selected.add(item);
			}
		}
		return selected;
	}
	
	public static interface CheckBoxItem{
		public boolean isChecked();
		public void setChecked(boolean isChecked);
		public JCheckBox getCheckBox();
		public Component getLabelPart(CustomList parent, int index, int column);
	}
	
}
