package com.owner.chatapp.users.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.owner.chatapp.network.Client;
import com.owner.chatapp.utils.Userinfo;

public class ClientChatScreen extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private Client client;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
//	public static void main(String[] args) {
//		
//		try {
//			ClientChatScreen frame = new ClientChatScreen();
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	} 


	/**
	 * Create the frame.
	 */
	private void sendit() {
		String message = textArea_1.getText();
		try {
			client.sendMessage(Userinfo.USER_NAME+"- " + message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ClientChatScreen() throws UnknownHostException, IOException {
		textArea_1 = new JTextArea();
		textArea = new JTextArea();
		client = new Client(textArea);
		setTitle(Userinfo.USER_NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 700, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 6, 658, 280);
		contentPane.add(scrollPane);
		
		
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textArea.setBounds(20, 20, 645, 250);
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		
		JButton sendBtn = new JButton("Send Message");
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendit();
				textArea_1.setText("");
			}
		});
		sendBtn.setBounds(560, 308, 117, 29);
		contentPane.add(sendBtn);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(30, 303, 518, 39);
		contentPane.add(scrollPane_1);
		
		textArea_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textArea_1.setBounds(30, 303, 518, 39);
		scrollPane_1.setViewportView(textArea_1);
		
		setVisible(true);
	}
}
