package com.arevir26.smsblast.GUI.Custom;

import java.awt.Component;

import javax.swing.JCheckBox;

import com.arevir26.smsblast.Data.Contact;
import com.arevir26.smsblast.GUI.Custom.CustomList.CheckBoxItem;

public class CheckedBoxContact extends Contact implements CheckBoxItem{
	
	protected JCheckBox checkbox;

	public CheckedBoxContact(String name, String number) {
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
		checkbox.setSelected(isChecked);
	}

	@Override
	public JCheckBox getCheckBox() {
		// TODO Auto-generated method stub
		return checkbox;
	}

	@Override
	public Component getLabelPart(CustomList parent, int index, int column) {
		// TODO Auto-generated method stub
		return null;
	}
	
}


