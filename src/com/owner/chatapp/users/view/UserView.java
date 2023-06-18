// this is used to run on cli
//java -cp "/Users/rachitagarwal/eclipse-workspace/ChatApp/mysql-connector-j-8.0.33.jar:." com.owner.chatapp.users.view.UserView

package com.owner.chatapp.users.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.owner.chatapp.users.dao.UserDAO;
import com.owner.chatapp.users.dto.UserDTO;
import com.owner.chatapp.utils.Userinfo;

public class UserView{

	public JFrame userViewFrame;
	private JTextField userIdField;
	private JButton btnLogin;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		UserView window = new UserView();
		window.userViewFrame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public UserView() {
		initialize();
	}
	
	public void doLogin() {
		String userid = userIdField.getText();
		char password[] = passwordField.getPassword();
		
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(userid, password);
		
		try {
			String FullName = userDAO.isLogin(userDTO);
			if(FullName.length()!=0) {
//				String msg = "Welcome "+FullName;
				JOptionPane.showMessageDialog(userViewFrame, "Welcome "+FullName);
				Userinfo.USER_NAME = FullName;
				Userinfo.USER_ID = userid;
				userViewFrame.setVisible(false);
				userViewFrame.dispose();
				DashBoard dashBoard = new DashBoard(userid);
				dashBoard.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(userViewFrame, "Invalid UserId / Password.");
			}
		} 
		catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
			
			e.printStackTrace();
		} 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		userViewFrame = new JFrame();
		userViewFrame.setBackground(new Color(255, 255, 255));
		userViewFrame.setResizable(false);
		userViewFrame.setTitle("Chat Application");
		userViewFrame.getContentPane().setBackground(new Color(240, 248, 255));
		userViewFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login / Register");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblNewLabel.setBounds(110, 32, 209, 44);
		userViewFrame.getContentPane().add(lblNewLabel);
		
		JLabel userIdlbl = new JLabel("UserId :");
		userIdlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		userIdlbl.setFont(new Font("PT Sans", Font.BOLD, 16));
		userIdlbl.setBounds(40, 128, 83, 16);
		userViewFrame.getContentPane().add(userIdlbl);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblPassword.setBounds(40, 177, 83, 16);
		userViewFrame.getContentPane().add(lblPassword);
		
		userIdField = new JTextField();
		userIdField.setToolTipText("Enter your username here..");
		userIdField.setName("");
		userIdField.setActionCommand("");
		userIdField.setHorizontalAlignment(SwingConstants.CENTER);
		userIdField.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		userIdField.setColumns(10);
		userIdField.setBorder(new LineBorder(Color.BLACK, 1, true));
		userIdField.setBounds(147, 122, 227, 26);
		userViewFrame.getContentPane().add(userIdField);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnLogin.setBounds(91, 245, 125, 35);
		userViewFrame.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userViewFrame.setVisible(false);
				userViewFrame.dispose();
				Register register = new Register();
				register.setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnRegister.setBounds(232, 245, 125, 35);
		userViewFrame.getContentPane().add(btnRegister);
		
		JLabel userlbl = new JLabel("");
		userlbl.setHorizontalAlignment(SwingConstants.CENTER);
		userlbl.setIcon(new ImageIcon(UserView.class.getResource("/assets/UserLogin.png")));
		userlbl.setBounds(456, 27, 250, 275);
		userViewFrame.getContentPane().add(userlbl);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter your PassKey");
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		passwordField.setBounds(147, 171, 227, 26);
		userViewFrame.getContentPane().add(passwordField);
		userViewFrame.setBounds(100, 100, 750, 360);
		userViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//userViewFrame.setVisible(true);
	}
}
