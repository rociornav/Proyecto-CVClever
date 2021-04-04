package com.cvclever.api.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * Clase intermedia para recibir requests.
 */
@XmlType(name="", propOrder= {
		"user",
		"template",
		"auth"
})
@XmlRootElement(name="JsonRequest")
public class JsonRequest {
	
	//Attributes
	@XmlElement(name="user", nillable=true)
	protected User user;
	@XmlElement(name="template", nillable=true)
	protected Template template;
	
	@XmlElement(name="auth", nillable=true)
	protected Token token;
	
	//getters & setters
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	
}
