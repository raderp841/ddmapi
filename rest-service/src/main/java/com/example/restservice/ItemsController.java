package com.example.restservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.models.Item;

@RestController 
public class ItemsController {
	@CrossOrigin(origins = "https://raderp841.github.io")
	@RequestMapping("/getItems")
	public List<Item> getItems()   
	{  
		HttpURLConnection connection = null;
		List<Item> items = new ArrayList<Item>();
		
		try{
			URL url = new URL("https://api.jsonbin.io/b/5ed8f6cb1f9e4e5788173781");
			connection = (HttpURLConnection) url.openConnection();
     		connection.setRequestMethod("GET");
     		
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36");		
			
			int status = connection.getResponseCode();
			System.out.println(status);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine = "";
			StringBuffer response = new StringBuffer();
			
			while((inputLine = in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			
			JSONObject myResponse = new JSONObject(response.toString());
			JSONArray output = myResponse.getJSONObject("discountdm").getJSONArray("items");
			
			for(int i = 0; i < output.length(); i++) {
				JSONObject jsonItem = output.getJSONObject(i);
				Item item = new Item();
				item.setId((jsonItem.getInt("id")));
				item.setName(jsonItem.getString("name"));
				item.setPrice(jsonItem.getDouble("price"));
				item.setImgPath(jsonItem.getString("imgPath"));
				items.add(item);
			}	
			
		}catch(IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	} 
}
