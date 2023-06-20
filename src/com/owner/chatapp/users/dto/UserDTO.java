package com.owner.chatapp.users.dto;

	public class UserDTO {
		private String FullName;
		private String userid;
		private char[] password;
		
		public UserDTO() {
			
		}
		
		public UserDTO(String FullName ,String userid, char[] password) {
			super();
			this.FullName = FullName;
			this.userid = userid;
			this.password = password;
		}
		public UserDTO(String FullName ,String userid) {
			super();
			this.FullName = FullName;
			this.userid = userid;
		}
		public UserDTO(String userid, char[] password) {
			super();
			
			this.userid = userid;
			this.password = password;
		}

		public String getFullName() {
			return FullName;
		}

		public void setFullName(String fullName) {
			FullName = fullName;
		}

		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}

		public char[] getPassword() {
			return password;
		}

		public void setPassword(char[] password) {
			this.password = password;
		}
		
}
