package com.anaghdev.chatapp.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerMultithread extends Thread{
	private Socket socketClientRemotePort = null;
	Server server = null;
	InputStream streamIn =  null;
	BufferedReader streamInReader = null;
	OutputStream streamOut = null;
	PrintWriter streamOutWriter = null;
	static Scanner scanner = new Scanner(System.in);
	
	public ServerMultithread(Socket socketClientRemotePort, Server server) throws IOException
	{
		this.socketClientRemotePort = socketClientRemotePort;
		this.server = server;
	}
	

	public void converse() throws IOException
	{
		String inputLine, outputLine; // Strings to store input and output.
		
		while(true)
		{
			inputLine = streamInReader.readLine();
			System.out.println("Incoming:	Mr. Client	:	"+inputLine);
			if(inputLine.equals("Bye") || inputLine.equals("bye"))
			{
				System.out.println("System Message	:	Ending Conversion from server");
				break;
			}
			// broadcasting the inputLine message from a client to the server to all clients
			// The input stream of each the client has the broadcasted message
			for(ServerMultithread serverWorker :  server.serverWorkers)
				serverWorker.streamOutWriter.println(inputLine);
        }
		
	}
	
	public void open() throws IOException
	{  
		streamIn = socketClientRemotePort.getInputStream();
		streamInReader = new BufferedReader(new InputStreamReader(streamIn));
		streamOut = socketClientRemotePort.getOutputStream();
		streamOutWriter = new PrintWriter(socketClientRemotePort.getOutputStream(), true);
	}

	public void close() throws IOException
	{  
		if (socketClientRemotePort != null)
			socketClientRemotePort.close();
		if (streamOut != null)
			streamOut.close();
		if (streamIn != null)
			streamIn.close();
	}
	
	@Override
	public void run()
	{
		try 
		{
			System.out.println("System Message	:	Opening assets");
			open();
			System.out.println("System Message	:	Conversing from server");
			converse();
			System.out.println("System Message	:	Closing assets");
			close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}