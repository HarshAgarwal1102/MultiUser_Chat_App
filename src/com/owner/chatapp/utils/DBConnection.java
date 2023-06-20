package com.owner.chatapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.owner.chatapp.utils.ConfigReader.getValue;
import static com.owner.chatapp.utils.KeyConstants.*;

public interface DBConnection {
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		//Connection connection = null;
		// Step-1
		Class.forName(getValue(DRIVER_NAME));
		// Step-2
		Connection con = DriverManager.getConnection(getValue(DB_URL), getValue(DB_USERID), getValue(DB_PWD));
		return con;
	}
}
