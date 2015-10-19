package com.omr.app.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class ProgressDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton cancelButton,okButton;
	JProgressBar SheetProgressBar;
	public JLabel SheetNameLabel;
	HashMap<String,String> DecodedSheetsList;
	JLabel CounterLabel;
	private int current,total;
	private boolean CancelButtonStatus;
	
	public JButton getOkButton()
	{
		return okButton;
	}
	public JButton getCancelButton(){
		return cancelButton;
	}
	
	public ProgressDialog(JFrame frame)
	{	
		super(frame);
		CancelButtonStatus=true;
		current = 0;
		total = 0;
		CounterLabel = new JLabel("0/0");	
		CounterLabel.setBounds(225, 32, 34, 24);
		CounterLabel.setSize(60,24);
		
		contentPanel.add(CounterLabel);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dell\\Pictures\\TeleTaleem\\TeleTaleem-50x50.jpg"));
		setTitle("Processing Sheets");
		setBounds(100, 100, 275, 163);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//contentPanel.setLayout(null);
		SheetProgressBar = new JProgressBar(0, 100);
		SheetProgressBar.setForeground(new Color(50, 205, 50));
		SheetProgressBar.setBounds(41, 27, 183, 26);
		SheetProgressBar.setValue(0);
		SheetProgressBar.setStringPainted(true);
		contentPanel.add(SheetProgressBar);
		
		SheetNameLabel = new JLabel("Sheet being decoded");
		SheetNameLabel.setBounds(41, 64, 183, 14);
		contentPanel.add(SheetNameLabel);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		okButton=new JButton("Ok");
		okButton.setActionCommand("Ok");
		buttonPane.add(okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		
	}
	
	public boolean GetCancelButtonStatus(){
		return CancelButtonStatus;
	}

	public HashMap<String, String> GetDecodedSheetList()
	{
		return DecodedSheetsList;
	}
	public void SetProgressBar(int value)
	{
		SheetProgressBar.setValue(value);
	}

	public void SetTotalCounterValue(int value)
	{
		total = value;
		CounterLabel.setText(current+"/"+total);
	}
	public void SetNumeratorValue(int value)
	{
		current = value;
		CounterLabel.setText(current+"/"+total);
	}

	
}
