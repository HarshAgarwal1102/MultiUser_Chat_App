package com.owner.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import com.owner.chatapp.utils.ConfigReader;

public class Client {
	
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		// for fetching IP address
		InetAddress localHost = InetAddress.getLocalHost();
		socket = new Socket(localHost, PORT);
	
		
		out = socket.getOutputStream();
		
		in = socket.getInputStream();
		this.textArea = textArea;
		readMessages();
//		System.out.println("Client comes...");
//		System.out.println("Enter the message you want to send to thee Server...");
//		Scanner scanner = new Scanner(System.in);
//		String message = scanner.nextLine();
//		OutputStream out = socket.getOutputStream();
//		out.write(message.getBytes());
//		System.out.println("Message sent to the Server");
//		scanner.close();
//		out.close();
//		socket.close();
	}
	public void sendMessage(String message) throws IOException {
		message = message + "\n";
		out.write(message.getBytes());
	}
	public void readMessages() {
		worker = new ClientWorker(in, textArea); // calling a read thread
		worker.start();
	}
	
//	public static void main(String[] args) throws UnknownHostException, IOException {
//		// TODO Auto-generated method stub
//		Client client = new Client();
//	}

}
