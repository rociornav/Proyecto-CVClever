package com.cvclever.api.model;

import javax.xml.bind.annotation.XmlRootElement;
import org.json.JSONObject;

@XmlRootElement
public class User {
	
	// Attributes
	private String id;
	private String email;
	private String password;
	private String phone;
	

	// Constructors
	public User(String id, String email, String password, String phone) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}
	public User() {
		super();
	}


	//Getters y Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	// Convert Object to JSONObject
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		// id
		json.put("id", id);
		// email
		json.put("email", email);
		// password
		json.put("password", password);
		// phone
		json.put("phone", phone);

		return json;
	}
	
}
