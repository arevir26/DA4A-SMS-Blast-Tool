package com.arevir26.smsblast.GUI.Custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

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
	
	private String getGroups() {
		if(this.groups !=null)return "";
		String group = "";
		int groupCount = this.groups.size();
		for(int i=0; i< groupCount ; i++) {
			group += this.groups.get(i);
			if(i < groupCount-1 ) {
				group += ",";
			}
		}
		return group;
	}
	
	private Color getRowColor(int row) {
		if((row%2)==0) {
			return new Color(230,230,230);
		}else {
			return Color.WHITE;
		}
	}

	@Override
	public Component getLabelComponent(CustomList parent, int index, int column) {
		JPanel panel = new JPanel();
		panel.setBorder(new Border() {
			
			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
				g.setColor(Color.BLACK);
				g.drawLine(0, 0, width, 0);
			}
			
			@Override
			public boolean isBorderOpaque() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public Insets getBorderInsets(Component c) {
				// TODO Auto-generated method stub
				return new Insets(0,0,0,0);
			}
		});
		panel.setBackground(getRowColor(index));
		switch(column) {
		case 0:
			panel.add(new JLabel(this.name));
			break;
		case 1:
			panel.add(new JLabel(this.number));
			break;
		case 2:
			panel.add(new JLabel(getGroups()+" "));
			break;
		default:
			panel.add(new JLabel("       "));
		}
		return panel;
	}

	@Override
	public Component getCheckBoxComponent(Component parent, int index) {
		JPanel panel = new JPanel();
		panel.add(checkbox);
		panel.setBorder(new Border() {
			
			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
				g.setColor(Color.BLACK);
				g.drawLine(0, 0, width, 0);
			}
			
			@Override
			public boolean isBorderOpaque() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public Insets getBorderInsets(Component c) {
				// TODO Auto-generated method stub
				return new Insets(0,0,0,0);
			}
		});
		panel.setBackground(getRowColor(index));
		return panel;
	}
	
}


