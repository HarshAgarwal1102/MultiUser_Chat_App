package com.owner.chatapp.utils;

import java.util.ResourceBundle;

public interface ConfigReader {
	public static final ResourceBundle rb = ResourceBundle.getBundle("config");
	//config.properties
	public static String getValue(String Key) {
		return rb.getString(Key);
	}
}
