package com.anaghdev.chatapp.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.JTextArea;

import com.anaghdev.chatapp.utils.ConfigReader;

public class Client 
{	
	Socket socketServerRemotePort = null;
	InputStream streamIn =  null;
	BufferedReader streamInReader = null;
	OutputStream streamOut = null;
	/*
	 * Creates a new PrintWriter from an existing OutputStream. 
	 * This convenience constructor creates the necessary 
	 * intermediateOutputStreamWriter, which will convert characters 
	 * into bytes using the default character encoding.
	 */
	PrintWriter streamOutWriter = null;
	ClientMultithread clientWorker = null;
	JTextArea textArea = null;
	
	public Client(JTextArea textArea) throws UnknownHostException, IOException
	{
		this.textArea = textArea;
		
		// Host name identifies the machine the server is hosted on
		String hostName = ConfigReader.getValue("SERVER_IP");
		// The port number identifies the server
		int portNumber = (Integer.parseInt(ConfigReader.getValue("PORTNO")));
		
		/*
		 * - Creating a socket, thereby getting a connection to the server.
		 */
		System.out.println("Message	:	Binding to port : " + portNumber);
		socketServerRemotePort = new Socket(hostName, portNumber);
		System.out.println("Message	:	Socket created: " + socketServerRemotePort);
		open();
		System.out.println("Message	:	Connected with Server and Streams established.");
		
		// Passing testArea to ClientMultithread
		readMessage();
	}
	
	/*
	 * The converse method simply writes the message in byte form 
	 * in the output stream to the server.
	 * The server broadcasts the message to the input streams all 
	 * the users on the server.
	 * 
	 */
	public void converse(String message) throws IOException
	{
		streamOut.write((message + "\n").getBytes());
	}
	
	public void readMessage()
	{
		clientWorker = new ClientMultithread(streamIn, textArea);
		clientWorker.start();
	}
	
	public void open() throws IOException
	{  
		/*
		 * Returns an output stream for this socket. 
		 * If this socket has an associated channel then the resulting 
		 * output stream delegates all of its operations to the channel.
		 */
		streamIn = socketServerRemotePort.getInputStream();
		streamInReader = new BufferedReader(new InputStreamReader(streamIn));
		streamOut = socketServerRemotePort.getOutputStream();
		streamOutWriter = new PrintWriter(socketServerRemotePort.getOutputStream(), true);
	}
	
	public void close() throws IOException
	{  
		if (socketServerRemotePort != null)
			socketServerRemotePort.close();
		if (streamOut != null)
			streamOut.close();
		if (streamIn != null)
			streamIn.close();
	}

//	public static void main(String[] args) throws UnknownHostException, IOException
//	{
//		Client client = new Client();
//	}
}