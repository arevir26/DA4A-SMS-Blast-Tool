package com.arevir26.smsblast.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public class FileSelectionPanel extends JPanel implements ActionListener {
	
	protected JTextField fileField;
	protected JButton selectFileButton, readFileButton;
	
	protected GridBagConstraints cons;
	protected List<FileSelectorActionListener> listeners;
	protected File selectedFile;
	
	public FileSelectionPanel(){
		initialize();
	}
	
	private void initialize() {
		listeners = new ArrayList<FileSelectionPanel.FileSelectorActionListener>();
		setMinimumSize(new Dimension(400,400));
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
		cons = new GridBagConstraints();
		fileField = new JTextField();
		selectFileButton = new JButton("Browse");
		readFileButton = new JButton("Read File");
		
		
		cons.anchor = GridBagConstraints.CENTER;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.weightx = 0;
		cons.insets = new Insets(5, 5, 5, 5);
		
		//add label
		add(new JLabel("Data: "), cons);
		
		cons.gridx = 1;
		cons.weightx = 1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weighty = 1;
		add(fileField, cons);
		
		cons.gridx = 2;
		cons.weightx = 0;
		
		add(selectFileButton, cons);
		
		cons.gridx = 3;
		add(readFileButton, cons);
		
		selectFileButton.addActionListener(this);
		readFileButton.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==selectFileButton) {
			showFileChooser();
		}
		
		// read button Clicked
		if(e.getSource()==readFileButton) {
			
		}
	}
	
	public void checkFiles() {
		
	}
	
	
	
	private void showFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new CSVFileFilter());
		int selectionStatus = fileChooser.showOpenDialog(this);
		
		if(selectionStatus == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
			fileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
		}
	}
	
	public void addFileSelectorActionListener(FileSelectorActionListener listener) {
		if(listener==null)return;
		this.listeners.add(listener);
	}
	
	public void removeFileSelectorActionListener(FileSelectorActionListener listener) {
		this.listeners.remove(listener);
	}
	
	public static interface FileSelectorActionListener{
		public void onFileSelected(File file);
		public void onSelectionError(String error);
	}

}
