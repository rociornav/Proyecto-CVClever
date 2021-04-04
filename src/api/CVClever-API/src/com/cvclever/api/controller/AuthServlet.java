package com.cvclever.api.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import com.cvclever.api.dao.AuthDaoImpl;
import com.cvclever.api.model.Token;
import com.cvclever.api.model.User;
import com.cvclever.api.utils.Utils;

public class AuthServlet {
	
	private static AuthDaoImpl authDaoImpl = new AuthDaoImpl();
	
	public static JSONObject login(User user) throws Exception {
		
		User resUser = authDaoImpl.login(user);
		JSONObject data = new JSONObject();
		if (resUser.getId() != null) {
			
			Token newToken = null;
			boolean tokenExist = true;
			while (tokenExist) {
				newToken = new Token(RandomStringUtils.random(32, true, true), resUser.getId(), null);
				Token cToken = authDaoImpl.getToken(newToken);
				
				if(cToken == null || !cToken.getToken_id().equals(newToken.getToken_id())) tokenExist= false;
			}
			
			if (newToken != null && authDaoImpl.createToken(newToken)) {
				data.put("token", newToken.getToken_id());
				data.put("user", resUser.toJSON());
			} else {
				data.put("error", "something happens... :( please, try again later");
			}

		} else {
			data.put("error", "credentials not corrects");
		}
 
		return data;
	}
	
	public static JSONObject logout(Token token) throws Exception {
		
		JSONObject data = new JSONObject();
			
		if (Utils.authorized(token)) {
			
			//está logueado y puede desloguearse con autorizacion
			//eliminamos la token del usuario logueado
			boolean tokenDeleted = authDaoImpl.deleteToken(token);
			if(tokenDeleted) {
				//si se ha eliminado la token, se ha deslogueado.
				data.put("success", "Se ha deslogueado correctamente.");
			}else {
				data.put("error", "No ha podido desloguearse");
			}
			
		} else {
			//no está logueado ni tiene autorización
			data.put("error", "Operación no autorizada.");
		}
		
		return data;
		
	}
}
