package view.dialog.about;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

	private String studentName = "Vinh D Tran";
	private String studentId = "s3500659";

	public AboutDialog(JFrame frame, String title, boolean modal) {
		super(frame, title, modal);
		setLayout(new BorderLayout());

		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-mm-dd HH:mm");
		LocalDateTime today = LocalDateTime.now();

//		add(new JButton("Assignment 2 Card Game"), BorderLayout.NORTH);
//		add(new JButton("Student ID: " + studentId), BorderLayout.LINE_START);
//		add(new JButton("Student Name: " + studentName), BorderLayout.CENTER);
//		add(new JButton(formatter.format(today)), BorderLayout.LINE_END);
		
		
		// Main panel to hold about dialog information
		JPanel mainContentPanel = new JPanel(new GridLayout(0,2));
		add(mainContentPanel);
		
		
		// student name label
		JLabel studentNameLabel = new JLabel("Student Name: ");
		mainContentPanel.add(studentNameLabel);
		
		// student name field
		JTextField studentNameField = new JTextField(studentName);
		studentNameField.setEditable(false);
		mainContentPanel.add(studentNameField);
		
		// student number label
		JLabel studentNumberLabel = new JLabel("Student Number: ");
		mainContentPanel.add(studentNumberLabel);
		
		// student number field
		JTextField studentNumberField = new JTextField(studentId);
		studentNumberField.setEditable(false);
		mainContentPanel.add(studentNumberField);
		
		// current date and time
		JLabel dateTimeLabel = new JLabel("Date-Time: ");
		mainContentPanel.add(dateTimeLabel);
		
		// date and time field
		JTextField dateTimeField = new JTextField(formatter.format(today));
		dateTimeField.setEditable(false);
		mainContentPanel.add(dateTimeField);
		
		
		
		JButton okButton = new JButton("OK");
		add(okButton, BorderLayout.SOUTH);
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		pack();
		setVisible(true);

	}

}
