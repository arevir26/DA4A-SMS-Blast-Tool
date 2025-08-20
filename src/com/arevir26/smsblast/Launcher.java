package com.arevir26.smsblast;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.arevir26.smsblast.GUI.MainPanel;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainPanel window = new MainPanel();
				window.setVisible(true);
				window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			
		});
	}

}
