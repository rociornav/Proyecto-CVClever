package com.cvclever.api.dao;

import com.cvclever.api.model.Token;
import com.cvclever.api.model.User;

public interface AuthDao {
	/**
	 * Check if the user exists in the database.
	 * @param user
	 * @return
	 */
	public boolean checkUserExist(User user);
	
	/**
	 * Check user credentials to log in.
	 * @param user
	 * @return
	 */
	public User login(User reqUser);
	
	/**
	 * Get the token object from database.
	 * @param token
	 * @return
	 */
	public Token getToken(Token token);
	
	/**
	 * Check if the token is valid for the entered user.
	 * @param token
	 * @return
	 */
	public boolean checkToken(Token token);
	
	/**
	 * Create a token for the entered user.
	 * @param token
	 * @return
	 */
	public boolean createToken(Token token);
	
	/**
	 * Delete token from database
	 * @param token
	 * @return
	 */
	public boolean deleteToken(Token token);
}
