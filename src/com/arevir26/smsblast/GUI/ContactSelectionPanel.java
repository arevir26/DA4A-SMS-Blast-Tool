package com.arevir26.smsblast.GUI;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.arevir26.smsblast.GUI.CustomListItem.ColumnSizeManager;

public class ContactSelectionPanel extends JPanel implements ListDataListener{
	
	protected JTable table;
	protected ListModel<CustomListItem> model;
	
	
	public ContactSelectionPanel() {
		render();
	}
	
	public void setListModel(ListModel<CustomListItem> model) {
		this.model = model;
		model.addListDataListener(this);
		render();
	}
	
	public void render() {
		this.removeAll();
		if(model==null)return;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		int size = model.getSize();
		ColumnSizeManager sizemanager = new ColumnSizeManager() {
			
			@Override
			public int getColumnWidth(int index) {
				// TODO Auto-generated method stub
				return 100;
			}
		};
		for(int i=0; i < size; i++) {
			CustomListItem item = model.getElementAt(i);
			item.setColumnSizeManager(sizemanager);
			add(item);
		}
		
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
		// TODO Auto-generated method stub
		render();
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
		// TODO Auto-generated method stub
		render();
	}

	@Override
	public void contentsChanged(ListDataEvent e) {
		render();
		
	}

}
