package com.owner.chatapp.users.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DashBoard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public void logOut() {
		setVisible(false);
		dispose();
		UserView window = new UserView();
		window.userViewFrame.setVisible(true);
	}
	public DashBoard(String msg) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle(msg);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu userMenu = new JMenu("Profile");
		menuBar.add(userMenu);
		
		JMenuItem detailsMenuItem = new JMenuItem("Your Details");
		userMenu.add(detailsMenuItem);
		detailsMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				dispose();
				new UserDetails();
			}
		});
		JMenuItem logOutMenuItem = new JMenuItem("Log Out");
		userMenu.add(logOutMenuItem);
		logOutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame1 = new JFrame("Log Out");
				if(JOptionPane.showConfirmDialog(frame1, "Confirm You Want To Log Out?", "Chat App", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					logOut();
				}
			}
		});
		
		JMenu chatMenu = new JMenu("Chat");
		menuBar.add(chatMenu);
		
		JMenuItem StartChat = new JMenuItem("Start Chat");
		StartChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setVisible(false);
					dispose();
					new ClientChatScreen();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		chatMenu.add(StartChat);
		
		JMenu exitMenu = new JMenu("App");
		menuBar.add(exitMenu);
		
		JMenuItem exitApp = new JMenuItem("Exit App");
		exitApp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame1 = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame1, "Confirm You Want To Exit?", "Chat App", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		exitMenu.add(exitApp);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 5, 791, 500);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(DashBoard.class.getResource("/assets/chit_chat.png")));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hi.. Welcome to our Chat App");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(99, 6, 599, 52);
		contentPane.add(lblNewLabel_1);
	}
}
