package com.anaghdev.chatapp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.anaghdev.chatapp.dto.UserDTO;
import com.anaghdev.chatapp.utils.PasswordEncryption;

public class UserDAO {

	public boolean signIn(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		String query = "SELECT name FROM users WHERE name=? AND password=?";
		
		try
		{
			String userid = userDTO.getUserid();
			char[] char_password = userDTO.getPassword();
			String password = new String(char_password);
			String encryptedPassword = PasswordEncryption.encryptPassword(password);
			
			conn = ChatDAO.createConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, userid);
			stmt.setString(2, encryptedPassword);
		
			rs = stmt.executeQuery();
			
			/*
			 * If the ResultSet object has some data then, 
			 * the record corresponding to the credentials 
			 * passed to the signIn() function exist. 
			 */
			return rs.next();
		}
		/*
		 * Always executes except when System.exit(int) is executed
		 * Terminates the currently running Java Virtual Machine. 
		 * The argument serves as a status code; by convention, a nonzero 
		 * status code indicates abnormal termination.
		 */
		finally
		{
			// Closing the Connection, Statement and ResultSet objects
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
			if(rs != null)
			{
				rs.close();
			}
		}		
	}
	
	public int signUp(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception
	{
		Connection conn = null;
		Statement stmt = null;
				
		try
		{
			conn = ChatDAO.createConnection();
			stmt = conn.createStatement();
			
			String userid = userDTO.getUserid();
			char[] char_password = userDTO.getPassword();
			String password = new String(char_password);
			String encryptedPassword = PasswordEncryption.encryptPassword(password);
			
			String query = "INSERT INTO users VALUES ('"+userid+"', '"+encryptedPassword+"', 'A')";
			int status = stmt.executeUpdate(query);
			return status;
		}

		finally
		{
			// Closing the Connection and Statement objects
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
		}
		
	}
}
