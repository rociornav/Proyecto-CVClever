package com.cvclever.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import com.cvclever.api.controller.AuthServlet;
import com.cvclever.api.dao.AuthDaoImpl;
import com.cvclever.api.model.JsonRequest;
import com.cvclever.api.model.Token;
import com.cvclever.api.model.User;
import com.cvclever.api.utils.Utils;

@Path("/auth")
public class AuthServiceImpl implements AuthService{
	
	private AuthDaoImpl authDaoImpl = new AuthDaoImpl();

	@GET
	@Path("/checkUser/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response checkUser(@PathParam("email") String email) {

		JSONObject jsonObject = new JSONObject();
		int statusCode = 200;
		User user = new User();
		user.setEmail(email);
		
		try {
			boolean res = authDaoImpl.checkUserExist(user);
			JSONObject data = new JSONObject();
			data.put("exist", String.valueOf(res));
			
			statusCode = 200;
			jsonObject.put("status", "OK");//aqui creamos una respuesta  en json, Nota: .put(key, value)
			jsonObject.put("response", data);
		} catch(Exception e) {
			statusCode = 500;
			jsonObject.put("status", "KO");//aqui creamos una respuesta  en json, Nota: .put(key, value)
			jsonObject.put("error", e.getMessage());
		}
 
		//Devuelve un 200 como respuesta y en entity le da el resultado, lo que devuelva se lo da al cliente. Importante pasar el objeto json a string
		return Response.status(statusCode).entity(jsonObject.toString()).build();
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response login(JsonRequest jsonRequest) {
		
		// Recieve Body Params
		User user = jsonRequest.getUser();

		JSONObject data = new JSONObject();
		Exception excep = null;
		
		try {
			if (user != null) {
				data = AuthServlet.login(user);
			}			
		} catch(Exception e) {
			excep = e;
		}

		return Utils.getResponse(data, excep);

	}
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response logout(JsonRequest jsonRequest) {
		
//		JSONObject jsonObject = new JSONObject();
//		int statusCode = 200;
//		
//		Token token= jsonRequest.getToken();
//		try {
//			Token resToken = authDaoImpl.getToken(token);
//			JSONObject data = new JSONObject();
//			
//			//comprobamos que exista el token y que sea válida
//			if(resToken != null && authDaoImpl.checkToken(resToken)) {
//				//está logueado y puede desloguearse con autorizacion
//				//eliminamos la token del usuario logueado
//				boolean tokenDeleted = authDaoImpl.deleteToken(resToken);
//				
//				if(tokenDeleted) {
//					//si se ha eliminado la token, se ha deslogueado.
//					data.put("success", "Se ha deslogueado correctamente.");
//				}else {
//					data.put("error", "No ha podido desloguearse");
//				}
////				
//			}else {
//				//no está logueado ni tiene autorización
//				data.put("error", "Operación no autorizada.");
//			}
//			
//			statusCode = 200;
//			jsonObject.put("status", "OK");//aqui creamos una respuesta  en json, Nota: .put(key, value)
//			jsonObject.put("response", data);
//		}catch(Exception e) {
//			statusCode = 500;
//			jsonObject.put("status", "KO");//aqui creamos una respuesta  en json, Nota: .put(key, value)
//			jsonObject.put("error", e.getMessage());
//		}
//		return Response.status(statusCode).entity(jsonObject.toString()).build();
		
		// Recieve Body Params
		Token token = jsonRequest.getToken();

		JSONObject data = new JSONObject();
		Exception excep = null;
		
		try {
			data = AuthServlet.logout(token);
		} catch(Exception e) {
			excep = e;
		}

		return Utils.getResponse(data, excep);
	}
	
	
	

}
