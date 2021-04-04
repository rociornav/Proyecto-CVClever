package com.cvclever.api.rest;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.cvclever.api.model.JsonRequest;
import com.cvclever.api.model.Token;
import com.cvclever.api.model.User;

public interface AuthService {
	
	public Response checkUser(@PathParam("email") String email);
	
	public Response login(JsonRequest jsonRequest);
	
	public Response logout(JsonRequest jsonRequest);
}
