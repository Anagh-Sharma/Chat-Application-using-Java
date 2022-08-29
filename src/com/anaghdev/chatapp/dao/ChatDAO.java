package com.anaghdev.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.anaghdev.chatapp.utils.ConfigReader;

public interface ChatDAO {
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName(ConfigReader.getValue("DRIVER"));
		final String DB_URL = ConfigReader.getValue("DB_URL");
		final String USER_ID = ConfigReader.getValue("USER_ID");
		final String PASS = ConfigReader.getValue("PASS");
		/*
		 * DriverManager : 
		 * The basic service for managing a set of JDBC drivers. 
		 */
		Connection conn = DriverManager.getConnection(DB_URL, USER_ID, PASS); 
		
		return conn;
	}
}
