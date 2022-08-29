package com.anaghdev.chatapp.networking;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import com.anaghdev.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
    
    Vector<ServerMultithread> serverWorkers = new Vector<>();
    
	public Server() throws IOException
	{	
		try
		{
			int portNumber = (Integer.parseInt(ConfigReader.getValue("PORTNO")));
			/*
			 * Creating a server socket using the specified port number.
			 */
			System.out.println("Message	:	Binding to port : " + portNumber);
			serverSocket = new ServerSocket(portNumber);
			System.out.println("Message	:	Socket created	: "+ serverSocket);

			int clientSerial = 1;
			while (true) {
				System.out.println("Message	:	Server waiting for a client"); 
			    // Accept a connection
				Socket socketClientRemotePort = serverSocket.accept();
				System.out.println("Message	:	Socket created: " + socketClientRemotePort);
				System.out.println("Message	:	Client number: " + clientSerial++);
			    // Create a thread to deal with the client
			    ServerMultithread serverWorker = new ServerMultithread(socketClientRemotePort, this);
			    serverWorkers.add(serverWorker);
			    serverWorker.start();
			}
			
		}
		
	    catch(IOException ioe)
	    {  
	    	System.out.println(ioe); 
	    }
	}
	
	public static void main(String[] args) throws IOException
	{
		Server server = new Server();
	}
}
















