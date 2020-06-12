package com.example.restservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.models.Item;

@RestController 
public class ItemsController {
	@CrossOrigin(origins = "https://raderp841.github.io")
	@RequestMapping("/getItems")
	public List<Item> getItems()   
	{  
		List<Item> items = new ArrayList<Item>();
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://bac89185c494f2:bab98e2e@us-cdbr-east-05.cleardb.net/heroku_26a5bd854cce29a?reconnect=true");
				Statement statement = conn.createStatement()){
			
			ResultSet result = statement.executeQuery("SELECT * FROM storeItems");
			
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				double price = result.getDouble("price");
				String imgPath = result.getString("imgPath");
				
				items.add(new Item(id, name, price, imgPath));
			}
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return items;
	}
	
	@CrossOrigin(origins = "https://raderp841.github.io")
	@RequestMapping("/getItemsForUser")
	public List<Item> getItemsForUser(@RequestHeader("USERID") int userId)   
	{  
		List<Item> items = new ArrayList<Item>();
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://bac89185c494f2:bab98e2e@us-cdbr-east-05.cleardb.net/heroku_26a5bd854cce29a?reconnect=true");
				Statement statement = conn.createStatement()){
			
			ResultSet result = statement.executeQuery("SELECT u.id as userItemId, s.* FROM storeItems s JOIN userInfoStoreItems u ON u.storeItemId = s.id WHERE u.userInfoId =" + userId);
			
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				double price = result.getDouble("price");
				String imgPath = result.getString("imgPath");
				int userItemId = result.getInt("userItemId");
				
				items.add(new Item(id, name, price, imgPath, userItemId));
			}
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return items;
	}
	
	@CrossOrigin(origins = "https://raderp841.github.io")
	@RequestMapping("/addItem")
	public boolean addItem(@RequestHeader("USERID") int userId, @RequestHeader("ITEMID") int itemId)   
	{  
		boolean success = false;
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://bac89185c494f2:bab98e2e@us-cdbr-east-05.cleardb.net/heroku_26a5bd854cce29a?reconnect=true");
				Statement statement = conn.createStatement()){
			
			 int rowsAffected = statement.executeUpdate("insert into userInfoStoreItems(userInfoId, storeItemId) values("+ userId + ", " + itemId + ")");
			
			 return (rowsAffected > 0);
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return success;
	}
	
	@CrossOrigin(origins = "https://raderp841.github.io")
	@RequestMapping("/removeItem")
	public boolean addItem(@RequestHeader("ITEMID") int itemId)   
	{  
		boolean success = false;
		
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://bac89185c494f2:bab98e2e@us-cdbr-east-05.cleardb.net/heroku_26a5bd854cce29a?reconnect=true");
				Statement statement = conn.createStatement()){
			
			 int rowsAffected = statement.executeUpdate("DELETE FROM userInfoStoreItems WHERE id = " + itemId);
			
			 return (rowsAffected > 0);
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return success;
	}
}
