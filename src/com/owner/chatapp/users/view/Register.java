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

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField fullNametxt;
	private JTextField userIdtxt;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	
	private void doRegister() {
		String FullName = fullNametxt.getText();
		String userid = userIdtxt.getText();
		char password[] = passwordField.getPassword();
		
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(FullName, userid, password);
		try {
			
			if(!InputValidation.lengthCheckValidateFullName(fullNametxt.getText())) {
				JOptionPane.showMessageDialog(this, "Invalid Input! Name must have atleast 3 characters.");
			}
			else if(!InputValidation.lengthCheckValidateUserId(userIdtxt.getText())) {
				JOptionPane.showMessageDialog(this, "Invalid Input! UserId must have atleast 6 characters.");
			}
			else {
				int result = userDAO.add(userDTO);
				if(result == 0) {
				JOptionPane.showMessageDialog(null, "User already exist...\nTry using another userId");
				}
				else if(result > 0) {
					JOptionPane.showMessageDialog(this, "Registered Successfully");
					this.setVisible(false);
					this.dispose();
					UserView window = new UserView();
					window.userViewFrame.setVisible(true);
				}
			
				else {
					JOptionPane.showMessageDialog(this, "Registeration Fail");
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
	private void loginPage() {
		this.setVisible(false);
		this.dispose();
		UserView window = new UserView();
		window.userViewFrame.setVisible(true);
		
	}
	
	public Register() {
		setResizable(false);
		setTitle("Registeration Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 360);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Full Name :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblNewLabel.setBounds(27, 107, 92, 22);
		contentPane.add(lblNewLabel);
		
		fullNametxt = new JTextField();
		fullNametxt.setHorizontalAlignment(SwingConstants.CENTER);
		fullNametxt.setBorder(new LineBorder(new Color(0, 0, 0)));
		fullNametxt.setBackground(new Color(255, 255, 255));
		fullNametxt.setBounds(139, 104, 232, 26);
		contentPane.add(fullNametxt);
		fullNametxt.setColumns(10);
		
		JLabel lblUserid = new JLabel("UserId :");
		lblUserid.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserid.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblUserid.setBounds(27, 152, 92, 22);
		contentPane.add(lblUserid);
		
		userIdtxt = new JTextField();
		userIdtxt.setToolTipText("example: abc@123");
		userIdtxt.setHorizontalAlignment(SwingConstants.CENTER);
		userIdtxt.setColumns(10);
		userIdtxt.setBorder(new LineBorder(new Color(0, 0, 0)));
		userIdtxt.setBackground(new Color(255, 255, 255));
		userIdtxt.setBounds(139, 149, 232, 26);
	
		contentPane.add(userIdtxt);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("PT Sans", Font.BOLD, 16));
		lblPassword.setBounds(27, 198, 92, 22);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0)));
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBounds(139, 195, 232, 26);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doRegister();
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnNewButton.setBounds(219, 260, 129, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Looks you are new here...");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(103, 16, 245, 51);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setIcon(new ImageIcon(Register.class.getResource("/assets/register.png")));
		lblNewLabel_2.setBounds(409, 6, 335, 315);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("<- Login Page");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPage();
			}
		});
		btnNewButton_1.setBounds(75, 260, 129, 37);
		contentPane.add(btnNewButton_1);
	}
}
