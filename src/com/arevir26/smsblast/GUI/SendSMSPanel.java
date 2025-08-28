package com.arevir26.smsblast.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.arevir26.smsblast.Data.DataManager;
import com.arevir26.smsblast.Data.MarketData;
import com.arevir26.smsblast.core.CSVMarketDataParser;
import com.arevir26.smsblast.core.IMarketDataParser;
import com.arevir26.smsblast.core.MessageDataParser;

public class SendSMSPanel extends JPanel implements ActionListener{
	protected JTextField templateField;
	protected JButton selectTemplateButton;
	
	protected FileSelectionPanel fileSelector;
	protected JComboBox<String> templateComboBox;
	protected JComboBox<MarketData> marketDataComboBox;
	protected MarketDataComboBoxModel marketDataModel;
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
		
		templateField = new JTextField();
		selectTemplateButton = new JButton("Browse");
		
		templateComboBox = new JComboBox<String>();
		marketDataComboBox = new JComboBox<MarketData>();
		marketDataModel = new MarketDataComboBoxModel();
		marketDataComboBox.setModel(marketDataModel);
		generateMessageButton = new JButton("<html>Generate<br/>Message</html>");
		
		messageScrollPane = new JScrollPane();
		messageField = new JTextArea();
		messageField.setEditable(true);
		messageField.setLineWrap(true);
		messageField.setMargin(new Insets(5, 5, 5, 5));
		//messageField.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		//messageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		messageScrollPane.getViewport().setView(messageField);
		
		sendButton = new JButton("Send");
		
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(5, 5, 5, 5);
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.weighty = 0;
		cons.anchor = GridBagConstraints.WEST;
		add(new JLabel("Template"), cons);
		cons.gridx = 1;
		cons.weightx = 1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		add(templateField, cons);
		cons.gridx = 2;
		cons.weightx = 0;
		add(selectTemplateButton, cons);
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.gridx = 0;
		cons.gridy ++;
		cons.gridwidth = 3;
		
		fileSelector = new FileSelectionPanel();
		add(fileSelector, cons);


		
		cons.gridy ++;
		cons.gridx = 0;
		cons.weightx = 0;

		cons.gridwidth = 1;
		add(new JLabel("Market Data: "), cons);
		
		cons.gridx = 1;
		cons.weightx = 1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		add(marketDataComboBox, cons);
		
		cons.gridx = 2;
		cons.weightx = 0;
		cons.fill = GridBagConstraints.BOTH;
		add(generateMessageButton, cons);
		
		cons.gridy ++;
		cons.gridx = 0;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.gridwidth = 3;
		cons.fill = GridBagConstraints.BOTH;
		add(messageScrollPane, cons);
		
		cons.gridy ++;
		cons.gridx = 0;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.EAST;
		add(sendButton, cons);
		
		
		selectTemplateButton.addActionListener(this);
		generateMessageButton.addActionListener(this);
		////test
		this.fileSelector.readFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IMarketDataParser parser = new CSVMarketDataParser();
				try {
					List<MarketData> data = parser.getMarketData(new File("D:\\Workfiles\\Infographics\\data merge test.csv"));
					marketDataModel.setMarketData(data);
					//model.setMarketData(data);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == selectTemplateButton) {
			JFileChooser filechooser = new JFileChooser();
			filechooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
			int result = filechooser.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION) {
				templateField.setText(filechooser.getSelectedFile().getAbsolutePath());
			}
		}
		
		if(e.getSource()==generateMessageButton) {
			MarketData data = (MarketData)marketDataModel.getSelectedItem();
			String template = DataManager.getInstance().getTemplate(new File(templateField.getText()));
			String newMessage = MessageDataParser.parseData(template, data);
			messageField.setText(newMessage);
		}
	}
	
	
	
	
	
	
	
}
