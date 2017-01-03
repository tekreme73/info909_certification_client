package fr.usmb.isc.m2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Window.Type;

public class MessageFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MessageFrame() {
		setTitle("Sending message");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelText = new JPanel();
		contentPane.add(panelText, BorderLayout.CENTER);
		
		JTextArea textAreaSend = new JTextArea();
		textAreaSend.setRows(10);
		textAreaSend.setColumns(30);
		panelText.add(textAreaSend);
		
		JPanel panelSend = new JPanel();
		contentPane.add(panelSend, BorderLayout.SOUTH);
		
		JButton buttonSend = new JButton("Send message");
		panelSend.add(buttonSend);
	}

}
