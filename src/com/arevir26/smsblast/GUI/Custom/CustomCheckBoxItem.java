package com.arevir26.smsblast.GUI.Custom;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.arevir26.smsblast.Data.Contact;
import com.arevir26.smsblast.GUI.Custom.CustomList.CheckBoxItem;

public class CustomCheckBoxItem extends Contact implements CheckBoxItem {
	
	protected JCheckBox checkbox;

	public CustomCheckBoxItem(String name, String number) {
		super(name, number);
		checkbox = new JCheckBox();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isChecked() {
		// TODO Auto-generated method stub
		return checkbox.isSelected();
	}

	@Override
	public void setChecked(boolean isChecked) {
		// TODO Auto-generated method stub
		checkbox.setSelected(isChecked);
	}

	@Override
	public JCheckBox getCheckBox() {
		// TODO Auto-generated method stub
		return checkbox;
	}

	@Override
	public Component getLabelPart(CustomList parent, int index, int column) {
		switch(column) {
		case 0:
			return new JLabel(this.name);
		case 1:
			return new JLabel(this.number);
		default:
			return new JPanel();
		}
	}

}
