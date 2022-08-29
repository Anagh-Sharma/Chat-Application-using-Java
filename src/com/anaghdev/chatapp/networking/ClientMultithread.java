package com.anaghdev.chatapp.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextArea;

public class ClientMultithread extends Thread
{
	InputStream streamIn =  null;
	BufferedReader streamInReader = null;
	JTextArea textArea = null;
	
	public ClientMultithread(InputStream streamIn, JTextArea textArea) 
	{
		this.streamIn = streamIn;
		this.textArea = textArea;
	}
	
	public void converse() throws IOException
	{
		String inputLine; // Strings to store input and output.
		
		try 
		{
			while(true)
			{
				inputLine = streamInReader.readLine();
				if(textArea.getText().isEmpty())
					textArea.setText(inputLine);
				else
					textArea.setText(textArea.getText()+"\n"+inputLine);
	        }
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void open() throws IOException
	{  
		streamInReader = new BufferedReader(new InputStreamReader(streamIn));
	}
	
	public void close() throws IOException
	{  
		if (streamInReader != null)
			streamInReader.close();
		if (streamIn != null)
			streamIn.close();
	}
	
	@Override
	public void run()
	{
		try {
			System.out.println("System Message	:	Opening assets");
			open();
			System.out.println("System Message	:	Conversing from server");
			converse();
			System.out.println("System Message	:	Closing assets");
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
