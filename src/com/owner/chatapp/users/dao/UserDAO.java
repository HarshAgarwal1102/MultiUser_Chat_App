package com.owner.chatapp.users.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.owner.chatapp.users.dto.UserDTO;
import com.owner.chatapp.utils.DBConnection;
import com.owner.chatapp.utils.Encryption;
import com.owner.chatapp.utils.Userinfo;

public class UserDAO {
	//login a user
	public String isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String SQL = "select FullName from users where userid=? and password=?";
		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			pstmt.setString(2, Encryption.passwordEncrypt(new String(userDTO.getPassword())));
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // returns true/false
				//System.out.println(rs.getString(1));
				return rs.getString(1);
			}
			else
				return "";
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}
	}
	
	// adding a user
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception {
		Connection connection = null;
		Statement statement = null; //query
		Connection conn = null;
		
		try { 
			conn = DBConnection.getConnection();
			String queryCheck = "SELECT * from users WHERE userid = '"+userDTO.getUserid()+"'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(queryCheck); // execute the query, and get a java resultset

			// if this useriD already exists, we quit
			if(rs.next()) {
			    conn.close();
			    return 0;
			}
			else {
			// Step-1 Connection create.
			connection = DBConnection.getConnection();
			// Step-2 we do a query.
			statement = connection.createStatement();
			// insert into users (FullName, userid, password) values('Ram Das', 'ram', 'ram1234');
			int record = statement.executeUpdate("insert into users (FullName, userid , password) values('"+userDTO.getFullName()+"','"+userDTO.getUserid()+"', '"+Encryption.passwordEncrypt(new String (userDTO.getPassword()))+"')");
			return record;
			}
		}
		
		finally { // always execute
			if(statement != null) {
				statement.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		
	}
	
	// Updating name of a user
	public int updateName(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception {
		
		Connection connection = null;
		Statement statement = null; //query
		Connection conn = null;
			
		try { 
//			conn = DBConnection.getConnection();
//			String queryCheck = "SELECT * from users WHERE userid = '"+Userinfo.USER_ID+"' and FullName = '"+Userinfo.USER_NAME+"'";
//			Statement st = conn.createStatement();
//			ResultSet rs = st.executeQuery(queryCheck); // execute the query, and get a java resultset
//
//			// if this useriD already exists, we quit
//			if(rs.next()) {
//				 conn.close();
//				 return 0;
//			}
//			else {
				// Step-1 Connection create.
				
				connection = DBConnection.getConnection();
				// Step-2 we do a query.
				statement = connection.createStatement();
				// update users set userid = 'harsh@123' where FullName = 'Harsh Agarwal';
				int record = statement.executeUpdate("update users set FullName ='"+userDTO.getFullName()+"' where userid = '"+userDTO.getUserid()+"'");
				return record;
			
		}
			
		finally { // always execute
			if(statement != null) {
				statement.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
	}
	
	// Updating userid of a user
	public int updateUserid(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception {
		Connection connection = null;
		Statement statement = null; //query
		Connection conn = null;
			
		try { 
			conn = DBConnection.getConnection();
			String queryCheck = "SELECT * from users WHERE userid = '"+userDTO.getUserid()+"'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(queryCheck); // execute the query, and get a java resultset

			// if this useriD already exists, we quit
			if(rs.next()) {
				   conn.close();
				   return 0;
			}
			else {
				// Step-1 Connection create.
				connection = DBConnection.getConnection();
				// Step-2 we do a query.
				statement = connection.createStatement();
				// update users set userid = 'harsh@123' where FullName = 'Harsh Agarwal';
				int record = statement.executeUpdate("update users set userid = '"+userDTO.getUserid()+"' where FullName ='"+Userinfo.USER_NAME+"'");
				return record;
			}
		}
			
		finally { // always execute
			if(statement != null) {
				statement.close();
			}
			if(connection != null) {
				connection.close();
			}
		}	
	}
	
	// Delete account of the user
	public int deleteAccountDetails(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception {
		Connection connection = null;
		Statement statement = null; //query
			
		try { 
			// Step-1 Connection create.
			connection = DBConnection.getConnection();
			// Step-2 we do a query.
			statement = connection.createStatement();
			// delete from users where userId  = 'harsh@123';
			int record = statement.executeUpdate("delete from users where userid = '"+Userinfo.USER_ID+"'");
			return record;
		}
			
		finally { // always execute
			if(statement != null) {
				statement.close();
			}
			if(connection != null) {
				connection.close();
			}
		}	
	}
}