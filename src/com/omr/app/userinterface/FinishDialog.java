package com.omr.app.userinterface;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.omg.CORBA.portable.ApplicationException;

import com.omr.app.Omr;

public class FinishDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private int SuccessCounter,QrCodeFailCounter,FailCounter;
	JPanel contentPanel;

	JLabel ProcessFailedAttemptsTextLabel;
	JLabel ProcessQrFailedAttemptsTextLabel;
	public FinishDialog(int SuccessCounter,int QrCodeFailCounter,int FailCounter,final String QrCodePath) {
		setBounds(100, 100, 450, 199);
		final File file=new File(QrCodePath);
		System.out.println("File can read:"+file.canRead());


		setTitle("Scanning result");
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Overview of scanning", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(19)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(24, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		JLabel SuccessLabel = new JLabel("Sucessfully uploaded results:"+SuccessCounter);

		JLabel lblNewLabel = new JLabel("Wrong Student Id Quiz Id Failed:"+QrCodeFailCounter);

		JLabel WrongResultsLabel = new JLabel("Result not succesfully uploaded:"+FailCounter);

		JButton ProcessQRFailedButton = new JButton("Process Failed");
		ProcessQRFailedButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final String[] strArray = new String[] {file.getAbsolutePath(),""};
				strArray[0]= file.getAbsolutePath().replace("\\", "/");
				strArray[0]=strArray[0].replace(".", "");
				strArray[0]=strArray[0].replace("//","/");
				strArray[1]="yes";
				System.out.println("File absolute path:"+strArray[0]);
				String absolute=null;
		        restartApplication(strArray);	
				
				/*try
				{
					  URL url = ClassLoader.getSystemResource("src/omr-v1.0-edms.jar");
					    //if (url != null) {
					    //File file = new File(url.toURI());
					   File file = new File("src/omr-v1.0-edms.jar");
					    
					    absolute=file.getAbsolutePath();
					    absolute=absolute.replace("\\","/");
					    absolute=absolute.replace("//", "/");
					    System.out.println(absolute);
					    }
					
				}	
				catch(Exception e)
				{
					e.getMessage();
				}
				ProcessBuilder pb = new ProcessBuilder("java", "-jar",absolute,strArray[0],strArray[1]);
				
				try {
					Omr frame=new Omr();
					frame.main(strArray);
					Process process = new ProcessBuilder("omr-v1.0-edms.jar",strArray[0],strArray[1]).start();

					  Process p = pb.start();
					  LogStreamReader lsr = new LogStreamReader(p.getInputStream());
			          Thread thread = new Thread(lsr, "LogStreamReader");
			          thread.start();
					 // System.exit(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/

			}
		});

		JButton ProcessResultFailedButton = new JButton("Process Failed");
		ProcessResultFailedButton.setEnabled(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(SuccessLabel, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(WrongResultsLabel))
								.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(ProcessResultFailedButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(ProcessQRFailedButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(7)
						.addComponent(SuccessLabel)
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(ProcessQRFailedButton))
								.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(WrongResultsLabel)
										.addComponent(ProcessResultFailedButton))
										.addContainerGap())
				);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
	public void restartApplication(String [] strArray)
	{
	  final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
	  File currentJar = null;
	try {
		currentJar = new File(Omr.class.getProtectionDomain().getCodeSource().getLocation().toURI());
	} catch (URISyntaxException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	  /* is it a jar file? */
	  if(!currentJar.getName().endsWith(".jar"))
	    return;

	  /* Build command: java -jar application.jar */
	  final ArrayList<String> command = new ArrayList<String>();
	  command.add(javaBin);
	  command.add("-jar");
	  command.add(currentJar.getPath());
	  System.out.println("current Jar:"+currentJar);
	  command.add(strArray[0]);
	  command.add(strArray[1]);

	  final ProcessBuilder builder = new ProcessBuilder(command);
	  try {
		builder.start();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  System.exit(0);
	}

}
