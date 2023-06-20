package com.owner.chatapp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.owner.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList <ServerWorker> workers = new ArrayList<>();
	
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started and waiting for the Client Connection...");
		handleClientRequest();
	}
	
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept(); // HandShaking
			ServerWorker serverWorker = new ServerWorker(clientSocket, this);
			workers.add(serverWorker);
			serverWorker.start();
		}
	}
	
	// Single Client
	/*public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started and waiting for the Client Connection...");
		Socket socket = serverSocket.accept(); // HandShaking
		System.out.println("Client joins the Server...");
		InputStream in = socket.getInputStream();// read bytes from network
		byte arr[] = in.readAllBytes();
		String str = new String(arr); // bytes convert into string
		System.out.println("Message recieved from the Client "+str);
		in.close();
		socket.close();
	}*/
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new Server();
	}

}
