package com.anaghdev.chatapp.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.anaghdev.chatapp.networking.Client;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class ChatScreen extends JFrame {
	private JTextField Type_Msg_textField = null;
	JTextArea Msg_View_textArea = null;
	private Client client = null;
	private String userid = null;
	
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ChatScreen(String userid) throws UnknownHostException, IOException 
	{
		this.userid = userid;
		Msg_View_textArea = new JTextArea();
		client = new Client(Msg_View_textArea);
		
		setTitle("Chat Application by Anagh");
		getContentPane().setBackground(new Color(255, 255, 153));
		
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 786, 313);
		getContentPane().add(scrollPane);
		setVisible(true);
		setBounds(100, 100, 820, 507);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		Msg_View_textArea.setFont(new Font("Calibri", Font.PLAIN, 26));
		Msg_View_textArea.setBounds(10, 10, 786, 366);
		Msg_View_textArea.setEditable(false);
		scrollPane.setViewportView(Msg_View_textArea);
		
		Type_Msg_textField = new JTextField();
		Type_Msg_textField.setBackground(new Color(224, 255, 255));
		Type_Msg_textField.setFont(new Font("Calibri", Font.PLAIN, 26));
		Type_Msg_textField.setBounds(10, 386, 561, 53);
		getContentPane().add(Type_Msg_textField);
		Type_Msg_textField.setColumns(10);
		
		/*
		 * When a User clicks on "Send" on this Chat screen
		 * or in other words executes actionPerformed method that 
		 * triggers the sendMessage method which:
		 * 	- Stores the text typed in textField in a String object
		 *  - Passes the String to the Client object's converse 
		 *    method.
		 */
		JButton Send_Button = new JButton("Send");
		Send_Button.setBackground(new Color(135, 206, 250));
		Send_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		Send_Button.setFont(new Font("Calibri", Font.PLAIN, 26));
		Send_Button.setBounds(581, 386, 215, 53);
		getContentPane().add(Send_Button);	
		
		JLabel lblWelcomeUser = new JLabel("Window of user    :    "+this.userid);
		lblWelcomeUser.setBackground(new Color(224, 255, 255));
		lblWelcomeUser.setOpaque(true);
		lblWelcomeUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeUser.setFont(new Font("Calibri", Font.PLAIN, 26));
		lblWelcomeUser.setBounds(10, 10, 786, 43);
		getContentPane().add(lblWelcomeUser);
		
		// Menu Bar
		JMenuBar chatViewMenuBar = new JMenuBar();
		setJMenuBar(chatViewMenuBar);
		
		JMenu mnActions = new JMenu("Actions");
		mnActions.setFont(new Font("Calibri", Font.PLAIN, 12));
		chatViewMenuBar.add(mnActions);
		
		// Exit Button
		JMenuItem Menu_1_Item_1_Exit = new JMenuItem("Exit");
		Menu_1_Item_1_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		mnActions.add(Menu_1_Item_1_Exit);
	}
	
	private void sendMessage()
	{
		String message = Type_Msg_textField.getText();
		Type_Msg_textField.setText(null); // Clear the current message
		try {
			client.converse(userid+"	:	"+message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void exit()
	{
		setVisible(false);
		dispose();
	}
}
