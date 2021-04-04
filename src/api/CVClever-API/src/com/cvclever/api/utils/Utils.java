package com.cvclever.api.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Response;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.json.JSONObject;

import com.cvclever.api.controller.AuthServlet;
import com.cvclever.api.dao.AuthDaoImpl;
import com.cvclever.api.model.Token;

public class Utils {
	
	private static AuthDaoImpl authDaoImpl = new AuthDaoImpl();

	/**
	 * Create the Pool Connection
	 * @return
	 */
	public static Connection getPoolConnection() {
		Connection con = null;
		try {
			con = ((BasicDataSource)
					((new InitialContext())
							.lookup("java:/comp/env/jdbc/mariaDBConnection")))
								.getConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		return con;
	}
	
	public static Response getResponse(JSONObject data, Exception e) {
		JSONObject jsonObject = new JSONObject();
		int statusCode = 200;
		if (e == null) {
			statusCode = 200;
			jsonObject.put("status", "OK");//aqui creamos una respuesta  en json, Nota: .put(key, value)
			jsonObject.put("response", data);
		} else {
			statusCode = 500;
			jsonObject.put("status", "KO");//aqui creamos una respuesta  en json, Nota: .put(key, value)
			jsonObject.put("error", e.getMessage());
		}
 
		//Devuelve un 200 como respuesta y en entity le da el resultado, lo que devuelva se lo da al cliente. Importante pasar el objeto json a string
		return Response.status(statusCode).entity(jsonObject.toString()).build();
	}
	
	public static boolean authorized(Token token) {
		//comprobamos que exista el token y que sea válida
		return (token != null && authDaoImpl.checkToken(token));
				
	}
}
