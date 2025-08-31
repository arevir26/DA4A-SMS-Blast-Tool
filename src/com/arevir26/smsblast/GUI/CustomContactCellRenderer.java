package com.arevir26.smsblast.GUI;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.arevir26.smsblast.Data.Contact;

public class CustomContactCellRenderer implements ListCellRenderer<Contact>{

	@Override
	public Component getListCellRendererComponent(JList<? extends Contact> list, Contact value, int index,
			boolean isSelected, boolean cellHasFocus) {
		CustomListItem item = new CustomListItem(false, value.name, value.number);
		return item;
	}
	


}
