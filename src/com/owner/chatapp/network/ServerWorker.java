package com.owner.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerWorker extends Thread{
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket, Server server) throws IOException {
		this.server = server;
		this.clientSocket = clientSocket;
		in = clientSocket.getInputStream(); // client data read
		out = clientSocket.getOutputStream(); // client data write
		System.out.println("New Client comes...");
	}
	@Override
	public void run() {
		// Read data from Client and broadcast the data to all
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while(true) {
				line = br.readLine();
				if(line.equalsIgnoreCase("quit")) {
					break;
				}
				// broadcast it to all clients
				for(ServerWorker serverWorker : server.workers) {
					String lines = line + "\n";
					serverWorker.out.write(lines.getBytes());
					lines.replace("\n", "");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(br!=null) {
					br.close();
				}
				if(in!=null) {
					in.close();
				}
				if(out!=null) {
					out.close();
				}
				if(clientSocket!=null) {
					clientSocket.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
