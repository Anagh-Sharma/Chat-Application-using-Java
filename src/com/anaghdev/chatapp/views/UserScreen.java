package com.anaghdev.chatapp.views;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import com.anaghdev.chatapp.dao.UserDAO;
import com.anaghdev.chatapp.dto.UserDTO;
import com.anaghdev.chatapp.networking.Server;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UserScreen extends JFrame 
{
	private JTextField textField_1_Username;
	private JPasswordField passwordField_1_User_Password;
	private Server server = null;
	private int serial = 1;

	public UserScreen() throws IOException {
		
		setTitle("Chat Application by Anagh");
		getContentPane().setBackground(new Color(255, 255, 153));
		
		JLabel Label_1_Title = new JLabel("A Simple Chat Application");
		Label_1_Title.setOpaque(true);
		Label_1_Title.setHorizontalAlignment(SwingConstants.CENTER);
		Label_1_Title.setFont(new Font("Calibri", Font.PLAIN, 26));
		Label_1_Title.setBackground(Color.WHITE);
		Label_1_Title.setBounds(223, 10, 346, 53);
		getContentPane().add(Label_1_Title);
		
		JLabel Label_2_Username = new JLabel("Username");
		Label_2_Username.setOpaque(true);
		Label_2_Username.setHorizontalAlignment(SwingConstants.CENTER);
		Label_2_Username.setFont(new Font("Calibri", Font.PLAIN, 26));
		Label_2_Username.setBackground(Color.WHITE);
		Label_2_Username.setBounds(148, 108, 143, 53);
		getContentPane().add(Label_2_Username);
		
		JLabel Label_3_Password = new JLabel("Password\r\n");
		Label_3_Password.setOpaque(true);
		Label_3_Password.setHorizontalAlignment(SwingConstants.CENTER);
		Label_3_Password.setFont(new Font("Calibri", Font.PLAIN, 26));
		Label_3_Password.setBackground(Color.WHITE);
		Label_3_Password.setBounds(148, 171, 143, 53);
		getContentPane().add(Label_3_Password);
		
		textField_1_Username = new JTextField();
		textField_1_Username.setFont(new Font("Calibri", Font.PLAIN, 26));
		textField_1_Username.setBounds(301, 108, 346, 53);
		getContentPane().add(textField_1_Username);
		textField_1_Username.setColumns(10);
		
		passwordField_1_User_Password = new JPasswordField();
		passwordField_1_User_Password.setFont(new Font("Calibri", Font.PLAIN, 26));
		passwordField_1_User_Password.setBounds(301, 171, 346, 53);
		getContentPane().add(passwordField_1_User_Password);
		
		
		JButton Button_1_Sign_Up = new JButton("Sign Up\r\n");
		/*
		 * - Implementing the actionPerformed method of 
		 *   ActionListener interface using an anonymous 
		 *   class.
		 * - Creation of an anonymous class also instantiates 
		 *   it.
		 * - The created object is passed to the addActionListener 
		 *   method of AbstractButton. As, JButton inherits AbstractButton 
		 *   class, therefore this method is also of JButton objects.
		 */
		Button_1_Sign_Up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUpFromScreen();
			}
		});
		Button_1_Sign_Up.setBorderPainted(false);
		Button_1_Sign_Up.setBackground(new Color(204, 255, 255));
		Button_1_Sign_Up.setFont(new Font("Calibri", Font.PLAIN, 26));
		Button_1_Sign_Up.setBounds(223, 289, 168, 49);
		getContentPane().add(Button_1_Sign_Up);
		
		JButton Button_2_Sign_In = new JButton("Sign In");
		Button_2_Sign_In.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signInFromScreen();				
			}
		});
		Button_2_Sign_In.setBackground(new Color(204, 255, 255));
		Button_2_Sign_In.setOpaque(true);
		Button_2_Sign_In.setBorderPainted(false);
		Button_2_Sign_In.setFont(new Font("Calibri", Font.PLAIN, 26));
		Button_2_Sign_In.setBounds(401, 289, 168, 49);
		getContentPane().add(Button_2_Sign_In);
		
		getContentPane().setLayout(null);
		setVisible(true);
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
		
		//Starting server
		server = new Server();
	}

	private void signUpFromScreen()
	{
		String userid = textField_1_Username.getText();
		char[] password = passwordField_1_User_Password.getPassword();
		// Create UserDTO object
		UserDTO dto = new UserDTO(userid, password);
				
		// Call function UserDAO.signUp with DTO object parameter
		UserDAO dao = new UserDAO();
		try 
		{
			int status = dao.signUp(dto);
			if(status > 0)
			{
				JOptionPane.showMessageDialog(this, "Sign-up status: Successful", "System Message", JOptionPane.INFORMATION_MESSAGE);

			}
			else
			{
				JOptionPane.showMessageDialog(this, "Sign-up status: Unsuccessful", "System Message", JOptionPane.INFORMATION_MESSAGE);
			}
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void signInFromScreen()
	{
		String userid = textField_1_Username.getText();
		char[] password = passwordField_1_User_Password.getPassword();
		// Create UserDTO object
		UserDTO dto = new UserDTO(userid, password);
				
		// Call function UserDAO.signIn with DTO object parameter
		UserDAO dao = new UserDAO();
		try 
		{
			/*
			 * The .next() method call on ResultSet object 
			 * from UserDAO returns true when a query returns 
			 * a value 
			 */
			boolean status = dao.signIn(dto);
			if(status)
			{
				JOptionPane.showMessageDialog(this, "Sign-in status: Successful", "System Message", JOptionPane.INFORMATION_MESSAGE);
				/*Note:	The current Sign-In/Sign-Up screen will remain visible for other users.*/
				Dashboard dashboard = new Dashboard(userid, serial++); // Creating a Dashboard object to display the next window
				dashboard.setVisible(true); // The next window is set visible
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Sign-in status: Unsuccessful", "System Message", JOptionPane.INFORMATION_MESSAGE);
			}
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void exit()
	{
		setVisible(false);
		dispose();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		try {
			UserScreen window = new UserScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
