package com.cvclever.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONObject;


@Path("/utils")
public class HelloServiceImpl implements HelloService {
	
	final static Logger logger = Logger.getLogger(HelloServiceImpl.class);
	
	//RUTA: http://localhost:8080/CVClever-API/api/utils/hello
	/**
	 * Metodo de prueba para comprobar que la api está activa.
	 */
	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON) //le indicamos que va a producir un objeto json
	@Override
	public Response getHello() {
		JSONObject jsonObject = new JSONObject();
//		String hello = "OK";
		jsonObject.put("status", "OK"); //aqui creamos una respuesta  en json, Nota: .put(key, value)
 
		//Devuelve un 200 como respuesta y en entity le da el resultado, lo que devuelva se lo da al cliente. Importante pasar el objeto json a string
		return Response.status(200).entity(jsonObject.toString()).build();
	}

}
