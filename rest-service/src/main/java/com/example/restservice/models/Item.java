package com.example.restservice.models;

public class Item {
	private int id;
	private String name;
	private double price;
	private String imgPath;
	private int userItemId;
	
	public Item() {
		
	}
	
	public Item(int id, String name, double price, String imgPath) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imgPath = imgPath;
		this.setUserItemId(-1);
	}
	
	public Item(int id, String name, double price, String imgPath, int userItemId) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imgPath = imgPath;
		this.setUserItemId(userItemId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getUserItemId() {
		return userItemId;
	}

	public void setUserItemId(int userItemId) {
		this.userItemId = userItemId;
	}
}
