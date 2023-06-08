package com.owner.chatapp.users.validation;

public class InputValidation {
	
	InputValidation(){
	}
	
	public static boolean lengthCheckValidateFullName(String input) {
		return input.length() >= 3;
	}
	public static boolean lengthCheckValidateUserId(String input) {
		return input.length() >= 6;
	}
	
	public static boolean lengthCheckValidateClientChatScreenText(String input) {
		return input.length() >= 1;
	}


}
