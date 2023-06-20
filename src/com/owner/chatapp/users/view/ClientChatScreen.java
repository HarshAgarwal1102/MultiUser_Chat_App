package com.owner.chatapp.users.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.owner.chatapp.network.Client;
import com.owner.chatapp.users.validation.InputValidation;
import com.owner.chatapp.utils.Userinfo;

public class ClientChatScreen extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private Client client;
	private JScrollPane scrollPane_1;

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
		if(!InputValidation.lengthCheckValidateClientChatScreenText(textArea_1.getText())) {
			//JOptionPane.showMessageDialog(this, "Invalid Input! Input must have atleast 1 characters.");
		}
		else {
			try {
				client.sendMessage(Userinfo.USER_NAME+"- " + message);
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void focus() {
		scrollPane_1.setViewportView(textArea_1);
		EventQueue.invokeLater( () -> textArea_1.requestFocusInWindow() );
	}
	
	public ClientChatScreen() throws UnknownHostException, IOException {
		textArea_1 = new JTextArea();
//		textArea_1.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusGained(FocusEvent e) {
//				if(textArea_1.getText().equals("Type a new message")) {
//					textArea_1.setText("");
//					textArea_1.setForeground(new Color(0, 0, 0));
//				}
//			}
//			@Override
//			public void focusLost(FocusEvent e) {
//				if(textArea_1.getText().equals("")) {
//					textArea_1.setText("Type a new message");
//					textArea_1.setForeground(new Color(128, 128, 128));
//				}
//			}
//		});
		textArea = new JTextArea();
		client = new Client(textArea);
		setTitle(Userinfo.USER_NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 700, 417);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu exitMenu = new JMenu("Chat");
		menuBar.add(exitMenu);
		JMenuItem exitChatMenu = new JMenuItem("Exit Chat");
		exitMenu.add(exitChatMenu);
		
		exitChatMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				dispose();
				DashBoard dashboard = new DashBoard(Userinfo.USER_ID);
				dashboard.setVisible(true);
			}
		});
	
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
		textArea.setLineWrap(true);
		
		
		JButton sendBtn = new JButton("Send Message");
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendit();
				textArea_1.setText("");
				focus();
			}
		});
		sendBtn.setBounds(560, 308, 117, 29);
		contentPane.add(sendBtn);
		
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(30, 303, 518, 39);
		contentPane.add(scrollPane_1);
		
		textArea_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textArea_1.setBounds(30, 303, 518, 39);
		textArea_1.setLineWrap(true);
		focus();
		
		setVisible(true);
	}
}
