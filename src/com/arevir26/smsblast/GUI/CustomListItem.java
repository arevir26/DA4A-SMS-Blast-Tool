package com.arevir26.smsblast.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CustomListItem extends JPanel{
	
	protected JCheckBox checkbox;
	protected String[] strings;
	protected ColumnSizeManager columnSizeMngr;
	protected GridBagConstraints cons;
	
	public CustomListItem(boolean ischecked, String...strings) {
		cons = new GridBagConstraints();
		checkbox = new JCheckBox();
		setLayout(new GridBagLayout());
		checkbox.setSelected(ischecked);
		this.strings = strings;
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(200, 200, 200)));
		
		initializeColumns();
	}
	
	private void initializeColumns() {
		cons.anchor = GridBagConstraints.FIRST_LINE_END;
		cons.weightx = 0;
		add(this.checkbox, FlowLayout.LEFT);
		cons.weightx = 1;
		cons.gridx ++;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.insets = new Insets(5,5,5,5);
		for(int i=0; i < strings.length; i++) {
			addColumn(strings[i], i);
		}
	}
	
	public void setColumnSizeManager(ColumnSizeManager manager) {
		columnSizeMngr = manager;
		removeAll();
		initializeColumns();
	}
	
	private void addColumn(String text, int index) {
		
		JLabel label = new JLabel(text);
		if(columnSizeMngr!=null) {
			Dimension dimension = label.getPreferredSize();
			dimension.width = columnSizeMngr.getColumnWidth(index);
			label.setPreferredSize(dimension);
		}
		cons.gridx++;
		add(label, cons);
	}
	
	public void setChecked(boolean ischeck) {
		checkbox.setSelected(ischeck);
	}
	
	public static interface ColumnSizeManager{
		public int getColumnWidth(int index);
	}
}
