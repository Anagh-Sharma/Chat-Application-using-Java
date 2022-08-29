package com.anaghdev.chatapp.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class Dashboard extends JFrame {
	private String userid = null;
	
	/**
	 * Create the frame.
	 */
	public Dashboard(String userid, int serial) 
	{
		this.userid = userid;
		setTitle("Chat Application by Anagh");
		getContentPane().setBackground(new Color(153, 204, 255));
		
		getContentPane().setLayout(null);
		JButton Button_Start_Chat = new JButton("Start Chat");
		Button_Start_Chat.setBackground(new Color(204, 255, 255));
		Button_Start_Chat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewChatWindow();
			}
		});
		Button_Start_Chat.setFont(new Font("Calibri", Font.PLAIN, 26));
		Button_Start_Chat.setBounds(314, 264, 188, 75);
		getContentPane().add(Button_Start_Chat);
		
		JLabel Label_4_Info = new JLabel("Your sent text is visible to other users, and visa-versa.");
		Label_4_Info.setOpaque(true);
		Label_4_Info.setHorizontalAlignment(SwingConstants.CENTER);
		Label_4_Info.setFont(new Font("Calibri", Font.PLAIN, 26));
		Label_4_Info.setBackground(new Color(255, 255, 153));
		Label_4_Info.setBounds(102, 179, 605, 75);
		getContentPane().add(Label_4_Info);
		
		JLabel Label_3_Info = new JLabel("You are user number "+serial+" on this server.\r\n");
		Label_3_Info.setHorizontalAlignment(SwingConstants.CENTER);
		Label_3_Info.setBackground(new Color(255, 255, 153));
		Label_3_Info.setFont(new Font("Calibri", Font.PLAIN, 26));
		Label_3_Info.setBounds(165, 106, 477, 75);
		getContentPane().add(Label_3_Info);
		Label_3_Info.setOpaque(true);
		
		JLabel Label_2_Title = new JLabel("Welcome, "+this.userid+".");
		getContentPane().add(Label_2_Title);
		Label_2_Title.setOpaque(true);
		Label_2_Title.setHorizontalAlignment(SwingConstants.CENTER);
		Label_2_Title.setFont(new Font("Calibri", Font.PLAIN, 26));
		Label_2_Title.setBackground(new Color(255, 255, 102));
		Label_2_Title.setBounds(236, 10, 346, 53);
		
		JLabel Label_1_Background = new JLabel("");
		Label_1_Background.setIcon(new ImageIcon(new javax.swing.ImageIcon(Dashboard.class.getResource("/assets/Background_3.jpg")).getImage().getScaledInstance(806, 470, Image.SCALE_SMOOTH)));
		Label_1_Background.setBounds(0, 0, 806, 470);
		getContentPane().add(Label_1_Background);
		
		/*
		 * When a User clicks on "Start Chat" on this Dash board
		 * or in other words executes actionPerformed method that 
		 * triggers the createNewChatWindow method which:
		 * 	- Removes dash board screen and disposes its properties
		 * 	- Creates a new Chat Window
		 *  - The Chat window's constructor initializes a new Client 
		 *    object that is passed the reference for the chat screen's 
		 *    TextArea.
		 *  - The creation of the Client object triggers getMessage method 
		 *    of Client which initializes the ClientMultithread object 
		 *    by passing the following references of the following:
		 *    	- The reference to the TextArea of the Chat Window 
		 *        that was passed to it.
		 *      - The reference of the input Stream of the Client object's 
		 *        socket connection to the server.
		 *  - The getMessage also triggers ClientMultithread object's run()
		 *    method that takes the input from the input stream and the 
		 *    puts it on the TextArea object whose reference was passed to 
		 *    it.  
		 * 	- The messages sent from the chat window are:
		 * 		- Sent to the server that was triggered with 
		 * 		  the creation of the Login-In/Sign-In screen 
		 *        from where this dash board was created.
		 *      - From the server the messages are broadcast 
		 *        to all clients or users on that server.
		 */
		setBounds(100, 100, 820, 507);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		JMenuBar dashboardMenuBar = new JMenuBar();
		setJMenuBar(dashboardMenuBar);
		
		JMenu mnActions = new JMenu("Actions");
		mnActions.setFont(new Font("Calibri", Font.PLAIN, 12));
		dashboardMenuBar.add(mnActions);
		
		JMenuItem Menu_1_Item_1_Exit = new JMenuItem("Exit");
		Menu_1_Item_1_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		mnActions.add(Menu_1_Item_1_Exit);
	}
	
	private void createNewChatWindow()
	{
		try {
			setVisible(false);
			dispose();
			ChatScreen chatScreen = new ChatScreen(userid);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
