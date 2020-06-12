package com.example.restservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.models.User;

@RestController 
public class UserController {
	@CrossOrigin(origins = "https://raderp841.github.io")
	@RequestMapping("/loginUser")
	public User getUser(@RequestHeader("USERNAME") String username, @RequestHeader("PASSWORD") String password)   
	{  
		User user = null;
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://bac89185c494f2:bab98e2e@us-cdbr-east-05.cleardb.net/heroku_26a5bd854cce29a?reconnect=true");
				Statement statement = conn.createStatement()){
			
			ResultSet result = statement.executeQuery("SELECT * FROM userInfo WHERE userName='" + username+ "' AND password='" + password + "'");
			
			while(result.next()) {
				int id = result.getInt("id");
				String uName = result.getString("userName");
				
				user = new User(id, uName);
			}
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return user;
	} 
	
	@CrossOrigin(origins = "https://raderp841.github.io")
	@RequestMapping("/registerUser")
	public boolean registerUser(@RequestHeader("USERNAME") String username, @RequestHeader("PASSWORD") String password)   
	{  
		boolean success = false;
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://bac89185c494f2:bab98e2e@us-cdbr-east-05.cleardb.net/heroku_26a5bd854cce29a?reconnect=true");
				Statement statement = conn.createStatement()){
			
			 int rowsAffected = statement.executeUpdate("INSERT INTO userInfo(userName, password) VALUES('" + username+ "', '" + password + "')");
			
			 return (rowsAffected > 0);
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return success;
	} 

}

