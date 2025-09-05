package com.arevir26.smsblast.GUI.Custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.arevir26.smsblast.GUI.Custom.CustomList.CheckBoxItem;

public class CustomList<T extends CheckBoxItem> extends JPanel implements ListDataListener{
	
	protected CustomListModel listModel;
	protected GridBagConstraints cons;
	protected JCheckBox	selectAllCheckBox;
	
	public CustomListModel getListModel() {
		
		return listModel;
	}


	public void setListModel(CustomListModel listModel) {
		this.listModel = listModel;
		this.listModel.addListDataListener(this);
	}

	protected int columns = 3;

	public CustomList() {
		cons = new GridBagConstraints();
		selectAllCheckBox = new JCheckBox();
		setLayout(new GridBagLayout());
		selectAllCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setAllCheckBoxSelection(selectAllCheckBox.isSelected());
			}
		});
	}
	
	private void setAllCheckBoxSelection(boolean c) {
		int size = listModel.getSize();
		for(int i = 0; i < size; i++) {
			listModel.getElementAt(i).getCheckBox().setSelected(c);
		}
	}
	
	private JPanel insertInPanel(Component c) {
		JPanel panel = new JPanel();
		panel.add(c);
		return panel;
	}
	
	
	public void renderItems() {
		removeAll();
		
		cons.gridy = 0;
		cons.insets = new Insets(0, 0, 0, 0);
		cons.fill = GridBagConstraints.BOTH;
		cons.anchor = GridBagConstraints.NORTH;
		//add header here
		cons.gridx = 0;
		cons.weightx = 0;
		add(insertInPanel(selectAllCheckBox), cons);
		// Add Column Header
		cons.gridx++;
		cons.weightx = 1;
		add(insertInPanel(new JLabel("Name")), cons);
		cons.gridx++;
		add(insertInPanel(new JLabel("Number")), cons);
		cons.gridx++;
		add(insertInPanel(new JLabel("Groups")), cons);
		
		
		for(int i=0; i < listModel.getSize(); i++) {
			CheckedBoxContact element = listModel.getElementAt(i);
			cons.gridy++;
			cons.gridx = 0;
			cons.weightx =0;
			add(element.getCheckBoxComponent(this, i),cons);
			for(int j = 0; j < columns; j++) {
				cons.gridx++;
				cons.weightx = 1;
				Component component = element.getLabelComponent(this, i, j);
				add(component, cons);
			}
		}
	}
	
	
	public List<CheckedBoxContact> getSelectedItems() {
		List<CheckedBoxContact> selected = new ArrayList<>();
		for(int i=0; i < 0; i++) {
			CheckedBoxContact item = listModel.getElementAt(i);
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
		public Component getLabelComponent(CustomList parent, int index, int column);
		public Component getCheckBoxComponent(Component parent, int index);
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
		// TODO Auto-generated method stub
		renderItems();
	}


	@Override
	public void intervalRemoved(ListDataEvent e) {
		// TODO Auto-generated method stub
		renderItems();
	}


	@Override
	public void contentsChanged(ListDataEvent e) {
		renderItems();
	}
	
}
