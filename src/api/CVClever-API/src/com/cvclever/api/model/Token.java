package com.cvclever.api.model;

import java.sql.Date;

public class Token {
	
	//Attributes
	private String token_id;
	private String user_id;
	private Date last_use;
	
	//Constructor
	public Token() { }
	public Token(String token_id, String user_id, Date last_use) {
		super();
		this.token_id = token_id;
		this.user_id = user_id;
		this.last_use = last_use;
	}

	//Getters & Setters
	public String getToken_id() {
		return token_id;
	}
	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getLast_use() {
		return last_use;
	}
	public void setLast_use(Date last_use) {
		this.last_use = last_use;
	}
	
	
}
