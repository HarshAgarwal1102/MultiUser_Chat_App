package com.owner.chatapp.users.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.owner.chatapp.users.dao.UserDAO;
import com.owner.chatapp.users.dto.UserDTO;
import com.owner.chatapp.users.validation.InputValidation;
import com.owner.chatapp.utils.Userinfo;

public class UserDetails extends JFrame{

	private JFrame frame;
	private JPanel contentPane;
	private JTextField fullNametxt;
	private JTextField userIdtxt;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserDetails window = new UserDetails();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public UserDetails() {
		initialize();
	}
	public void updateName() {
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(fullNametxt.getText(), userIdtxt.getText());
		try {
			if(!InputValidation.lengthCheckValidateFullName(fullNametxt.getText())) {
				JOptionPane.showMessageDialog(this, "Invalid Input! Name must have atleast 3 characters.");
			}
			else {
				int result = userDAO.updateName(userDTO);
				if(result > 0) {
					JOptionPane.showMessageDialog(this, "Name Updated Successfully");
				}
				else {
					JOptionPane.showMessageDialog(this, "Name Updation Fail");
				}
			}
			
		}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB issue...");
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Some Generic exception raised...");
			e.printStackTrace(); // where is the exception
		}
	}
	
	public void updateId() {
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(fullNametxt.getText(), userIdtxt.getText());
		try {
			if(!InputValidation.lengthCheckValidateUserId(userIdtxt.getText())) {
				JOptionPane.showMessageDialog(this, "Invalid Input! UserId must have atleast 6 characters.");
			}
			else {
				int result = userDAO.updateUserid(userDTO);
				if(result > 0) {
					JOptionPane.showMessageDialog(this, "UserId Updated Successfully");
				}
				else {
					JOptionPane.showMessageDialog(this, "UserId Updation Fail");
				}
			}
			
		}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB issue...");
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Some Generic exception raised...");
			e.printStackTrace(); // where is the exception
		}
	}
	public void deleteAccount() {
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(fullNametxt.getText(), userIdtxt.getText());
		try {
			
			int result = userDAO.deleteAccountDetails(userDTO);
			if(result > 0) {
				JOptionPane.showMessageDialog(this, "Account Deleted Successfully \nReloading Login Page...");
				frame.setVisible(false);
				frame.dispose();
				UserView window = new UserView();
				window.userViewFrame.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(this, "Account Deletion Fail");
			}
		}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB issue...");
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Some Generic exception raised...");
			e.printStackTrace(); // where is the exception
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("User Details");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 750, 360);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Full Name :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblNewLabel.setBounds(27, 36, 92, 22);
		contentPane.add(lblNewLabel);
		
		fullNametxt = new JTextField();
		fullNametxt.setText(Userinfo.USER_NAME);
		fullNametxt.setHorizontalAlignment(SwingConstants.CENTER);
		fullNametxt.setBorder(new LineBorder(new Color(0, 0, 0)));
		fullNametxt.setBackground(new Color(255, 255, 255));
		fullNametxt.setBounds(139, 33, 232, 26);
		contentPane.add(fullNametxt);
		fullNametxt.setColumns(10);
		
		JLabel lblUserid = new JLabel("UserId :");
		lblUserid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserid.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblUserid.setBounds(27, 115, 92, 22);
		contentPane.add(lblUserid);
		
		userIdtxt = new JTextField();
		userIdtxt.setText(Userinfo.USER_ID);
		userIdtxt.setHorizontalAlignment(SwingConstants.CENTER);
		userIdtxt.setColumns(10);
		userIdtxt.setBorder(new LineBorder(new Color(0, 0, 0)));
		userIdtxt.setBackground(new Color(255, 255, 255));
		userIdtxt.setBounds(139, 112, 232, 26);
	
		contentPane.add(userIdtxt);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblPassword.setBounds(27, 192, 92, 22);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setText("●●●●●●●");
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0)));
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBounds(139, 189, 232, 26);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Delete Account");
		btnNewButton.setForeground(Color.red);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame1 = new JFrame("Delete Account");
				if(JOptionPane.showConfirmDialog(frame1, "Confirm You Want To Delete?", "Chat App", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					deleteAccount();
				}
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnNewButton.setBounds(219, 265, 129, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(UserDetails.class.getResource("/assets/update3.png")));
		lblNewLabel_2.setBounds(383, 6, 361, 315);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Save Details");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Userinfo.USER_NAME.equals(fullNametxt.getText()) && Userinfo.USER_ID.equals(userIdtxt.getText())) {
					frame.setVisible(false);
					frame.dispose();
					DashBoard dashboard = new DashBoard(Userinfo.USER_NAME);
					dashboard.setVisible(true);
				}
				else {
				JOptionPane.showMessageDialog(null, "Details Saved Successfully\nReloading Login Page...");
				frame.setVisible(false);
				frame.dispose();
				UserView window = new UserView();
				window.userViewFrame.setVisible(true);
				}
			}
		});
		btnNewButton_1.setBounds(75, 265, 129, 37);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update Name");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateName();
			}
		});
		btnNewButton_2.setBounds(254, 59, 117, 29);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Update UserId");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateId();
			}
		});
		btnNewButton_2_1.setBounds(254, 138, 117, 29);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("Update Pass..");
		btnNewButton_2_1_1.setBounds(254, 215, 117, 29);
		contentPane.add(btnNewButton_2_1_1);
		
		frame.setVisible(true);
	}
}
