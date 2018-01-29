package com.javaex.vo;

public class UserVo {
	
	private String no;
	private String name;
	private String password;
	private String email;
	private String gender;
	
	public UserVo() {
	}

	public UserVo(String no, String name, String password, String email, String gender) {
		this.no = no;
		this.name = name;
		this.password = password;
		this.email = email;
		this.gender = gender;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", password=" + password + ", email=" + email + ", gender="
				+ gender + "]";
	}
	
	
}
