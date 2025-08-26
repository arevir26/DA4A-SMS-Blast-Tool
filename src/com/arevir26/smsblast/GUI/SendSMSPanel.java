package com.arevir26.smsblast.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.arevir26.smsblast.Data.MarketData;
import com.arevir26.smsblast.core.CSVMarketDataParser;
import com.arevir26.smsblast.core.IMarketDataParser;

public class SendSMSPanel extends JPanel{
	
	protected FileSelectionPanel fileSelector;
	protected JComboBox<String> templateComboBox;
	protected JComboBox<MarketData> marketDataComboBox;
	protected JScrollPane messageScrollPane;
	protected JButton generateMessageButton;
	protected JTextArea messageField;
	protected JButton sendButton;
	
	protected GridBagConstraints cons;
	
	
	
	public SendSMSPanel() {
		init();
	}
	
	private void init() {
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(400, 400));
		
		templateComboBox = new JComboBox<String>();
		marketDataComboBox = new JComboBox<MarketData>();
		generateMessageButton = new JButton("<html>Generate<br/>Message</html>");
		
		messageScrollPane = new JScrollPane();
		messageField = new JTextArea();
		messageField.setEditable(true);
		messageField.setLineWrap(true);
		messageField.setMargin(new Insets(5, 5, 5, 5));
		//messageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		messageScrollPane.getViewport().setView(messageField);
		
		sendButton = new JButton("Send");
		
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(5, 5, 5, 5);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 3;
		
		fileSelector = new FileSelectionPanel();
		add(fileSelector, cons);
		

		cons.anchor = GridBagConstraints.FIRST_LINE_START;
		cons.gridwidth = 1;
		cons.gridy = 1;
		cons.weightx = 0;
		cons.fill = GridBagConstraints.NONE;
		add(new JLabel("Template: "), cons);
		cons.gridx = 1;
		cons.weightx = 1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		add(templateComboBox, cons);
		
		cons.gridx = 2;
		cons.weightx = 0;
		cons.gridheight = 2;
		cons.fill = GridBagConstraints.BOTH;
		add(generateMessageButton, cons);
		
		cons.gridy = 2;
		cons.gridx = 0;
		cons.weightx = 0;
		cons.gridheight = 1;
		cons.fill = GridBagConstraints.NONE;
		add(new JLabel("Market Data: "), cons);
		cons.gridx = 1;
		cons.weightx = 1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		add(marketDataComboBox, cons);
		
		cons.gridy = 3;
		cons.gridx = 0;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.gridwidth = 3;
		cons.fill = GridBagConstraints.BOTH;
		add(messageScrollPane, cons);
		
		cons.gridy = 4;
		cons.gridx = 0;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.EAST;
		add(sendButton, cons);
		
		////test
		this.fileSelector.readFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IMarketDataParser parser = new CSVMarketDataParser();
				try {
					List<MarketData> data = parser.getMarketData(new File("C:\\Users\\Ryan Joseph\\Documents\\data merge test.csv"));
					MarketDataComboBoxModel model = new MarketDataComboBoxModel(data);
					marketDataComboBox.setModel(model);
					//model.setMarketData(data);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	
	
	
}
