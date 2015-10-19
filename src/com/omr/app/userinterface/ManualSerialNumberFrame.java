package com.omr.app.userinterface;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ManualSerialNumberFrame extends JDialog {

	private static final long serialVersionUID = -6193266438847567014L;
	private JPanel contentPane;
	private String code;
	private JTextField studentIdTextField;
	private JTextField quizIdTextField;
	JPanel EnterSerialNumberPanel;
	JLabel ImageLabel;
	int iterator;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ManualSerialNumberFrame(JFrame parent,String imagePath) {
		 super(parent, "Student Id Quiz Id Dialog", true);
		
		code = null;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 992, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		iterator=0;
		BufferedImage img=null;

		try {
			img=ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon icon=new ImageIcon(img);
		Image image=icon.getImage();
		image = createImage(new FilteredImageSource(image.getSource(),
				new CropImageFilter(5, 5, (int) (icon.getIconWidth()*0.85), (int) (icon.getIconHeight()*0.17))));
		//ImageIcon icon=new ImageIcon(imagePath);
		//java.awt.Image image = icon.getImage();
		/*image = createImage(new FilteredImageSource(image.getSource(),
				new CropImageFilter(5, 5, (int) (icon.getIconWidth()*0.85), (int) (icon.getIconHeight()*0.17))));*/
		
		//icon.setImage(image);
		icon.setImage(image);
		contentPane.setLayout(null);
		ImageLabel=new JLabel(icon);
		ImageLabel.setBounds(5, 0, 986, 343);
		contentPane.add(ImageLabel);
//		this.setVisible(true);
		EnterSerialNumberPanel = new JPanel();
		EnterSerialNumberPanel.setBorder(new TitledBorder(null, "Enter Serial Number", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		EnterSerialNumberPanel.setBounds(5, 348, 986, 55);
		contentPane.add(EnterSerialNumberPanel);
		EnterSerialNumberPanel.setLayout(null);

		JLabel StudentIdLabel = new JLabel("Student Id:");
		StudentIdLabel.setBounds(10, 30, 82, 14);
		EnterSerialNumberPanel.add(StudentIdLabel);

		studentIdTextField = new JTextField();
		studentIdTextField.setBounds(91, 27, 249, 20);
		EnterSerialNumberPanel.add(studentIdTextField);
		studentIdTextField.setColumns(10);
		
		JLabel QuizIdLabel=new JLabel("Quiz Id:");
		QuizIdLabel.setBounds(349, 27, 253, 20);
		EnterSerialNumberPanel.add(QuizIdLabel);
		
		quizIdTextField=new JTextField();
		quizIdTextField.setBounds(400,27,75,20);
		quizIdTextField.setColumns(5);
		EnterSerialNumberPanel.add(quizIdTextField);

		JButton SubmitSerialNumber = new JButton("Submit");
		SubmitSerialNumber.setBounds(500, 27, 89, 23);
		
		EnterSerialNumberPanel.add(SubmitSerialNumber);
        getRootPane().setDefaultButton(SubmitSerialNumber);

		SubmitSerialNumber.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				code = studentIdTextField.getText()+quizIdTextField.getText();
				dispose();
			}
		});
		
		


	}
	public String getCode(){
		return code;
	}
}