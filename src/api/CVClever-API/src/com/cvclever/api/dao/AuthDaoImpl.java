package com.cvclever.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import com.cvclever.api.model.Token;
import com.cvclever.api.model.User;
import com.cvclever.api.utils.Utils;


public class AuthDaoImpl implements AuthDao{
	final static Logger logger = Logger.getLogger(AuthDaoImpl.class);

	@Override
	public boolean checkUserExist(User user) {
		String methodName = "checkUserExist";
		
		boolean existUser=false;
		
		Connection con = Utils.getPoolConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		logger.info(methodName+" method has been invoked ["+this.getClass().getName()+"]");
		
		if(con != null) {
			
			String sql="SELECT user_id FROM user WHERE email= ?"; 
			try {
				
				ps = con.prepareStatement(sql);
				ps.setString(1, user.getEmail());
//				ps.setString(2, password);
				
				logger.info("Result from "+methodName+" method ["+this.getClass().getName()+"]");
				
				rs=ps.executeQuery();
				
				if(rs.next()) {//absolute(1)
					user.setId(rs.getString("user_id"));
					if (user.getId() != null && !user.getId().equals("")) existUser = true;
				}
				

			}catch(SQLException e) {
				logger.error("Error to "+methodName+" ["+this.getClass().getName()+"]",e);
				e.printStackTrace();
			}
		}
		return existUser;
	}

	@Override
	public User login(User reqUser) {
		
		String methodName = "login";
		
		User resUser = new User();
		boolean existUser = false;
		
		Connection con = Utils.getPoolConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		logger.info(methodName+" method has been invoked ["+this.getClass().getName()+"]");
		
		if(con != null) {
			
			String sql="SELECT user_id, email, phone FROM user WHERE email= ? AND password= ?"; 
			try {
				
				ps = con.prepareStatement(sql);
				ps.setString(1, reqUser.getEmail());
				ps.setString(2, reqUser.getPassword());
				
				logger.info("Result from "+methodName+" method ["+this.getClass().getName()+"]");
				
				rs=ps.executeQuery();
				
				if(rs.next()) {//absolute(1)
					resUser = new User(rs.getString("user_id"), rs.getString("email"), null, rs.getString("phone"));
					
					if (resUser.getId() != null && !resUser.getId().equals("")) existUser = true;
				}
				

			}catch(SQLException e) {
				logger.error("Error to "+methodName+" ["+this.getClass().getName()+"]",e);
				e.printStackTrace();
			}
		}
		
		return resUser;
	}

	@Override
	public boolean checkToken(Token token) {
		String methodName = "checkToken";
		
		boolean tokenValid = false;
		
		Connection con = Utils.getPoolConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		logger.info(methodName+" method has been invoked ["+this.getClass().getName()+"]");
		
		if(con != null) {
			Date date5min = new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5));
			
			String sql="SELECT * FROM token WHERE token_id= ? AND user_id= ? AND last_use> ?"; 
			try {
				
				ps = con.prepareStatement(sql);
				ps.setString(1, token.getToken_id());
				ps.setString(2, token.getUser_id());
				ps.setDate(3, date5min);
				
				logger.info("Result from "+methodName+" method ["+this.getClass().getName()+"]");
				
				rs=ps.executeQuery();
				
				if (rs.next()) {//absolute(1)
					Token resToken = new Token(rs.getString("token_id"), rs.getString("user_id"), rs.getDate("last_use"));
					
					if (resToken.getToken_id() != null && !resToken.getToken_id().equals("")) tokenValid = true;
				}

			}catch(SQLException e) {
				logger.error("Error to "+methodName+" ["+this.getClass().getName()+"]",e);
				e.printStackTrace();
			}
		}
		return tokenValid;
	}

	@Override
	public boolean createToken(Token token) {
		String methodName = "createToken";
		boolean tokenCreated = false;
		
		Connection con = Utils.getPoolConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		logger.info(methodName+" method has been invoked ["+this.getClass().getName()+"]");
		
		if(con != null) {
			
			String sql="INSERT INTO token (token_id, user_id) VALUES (?,?)"; 
			try {
				
				ps = con.prepareStatement(sql);
				ps.setString(1, token.getToken_id());
				ps.setString(2, token.getUser_id());
				
				logger.info("Result from "+methodName+" method ["+this.getClass().getName()+"]");
				
				int i = ps.executeUpdate();
				
				if(i!=0) tokenCreated = true;

			}catch(SQLException e) {
				logger.error("Error to "+methodName+" ["+this.getClass().getName()+"]",e);
				e.printStackTrace();
			}
		}
		
		return tokenCreated;
	}

	@Override
	public boolean deleteToken(Token token) {
		String methodName = "deleteToken";
		
		boolean tokenDeleted = false;
		
		Connection con = Utils.getPoolConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		logger.info(methodName+" method has been invoked ["+this.getClass().getName()+"]");
		
		if(con != null) {
			
			String sql="DELETE FROM token WHERE token_id= ?"; 
			try {
				
				ps = con.prepareStatement(sql);
				ps.setString(1, token.getToken_id());
				
				logger.info("Result from "+methodName+" method ["+this.getClass().getName()+"]");
				
				int i = ps.executeUpdate();
				
				if(i!=0) tokenDeleted = true;

			}catch(SQLException e) {
				logger.error("Error to "+methodName+" ["+this.getClass().getName()+"]",e);
				e.printStackTrace();
			}
		}
		return tokenDeleted;
	}

	@Override
	public Token getToken(Token token) {
		String methodName = "getToken";
		
		Token resToken = null;
		
		Connection con = Utils.getPoolConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		logger.info(methodName+" method has been invoked ["+this.getClass().getName()+"]");
		
		if(con != null) {
			
			String sql="SELECT * FROM token WHERE token_id= ?"; 
			try {
				
				ps = con.prepareStatement(sql);
				ps.setString(1, token.getToken_id());
				
				logger.info("Result from "+methodName+" method ["+this.getClass().getName()+"]");
				
				rs=ps.executeQuery();
				
				if (rs.next()) {//absolute(1)
					resToken = new Token(rs.getString("token_id"), rs.getString("user_id"), rs.getDate("last_use"));
				}

			}catch(SQLException e) {
				logger.error("Error to "+methodName+" ["+this.getClass().getName()+"]",e);
				e.printStackTrace();
			}
		}
		return resToken;
	}

}
