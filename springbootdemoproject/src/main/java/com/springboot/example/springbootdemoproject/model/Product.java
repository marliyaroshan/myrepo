package com.springboot.example.springbootdemoproject.model;

public class Product {
	public Product() {
	}

	public Product(int productId, String productName) {
		this.productId = productId;
		this.productName = productName;
	}

	private int productId;
	private String productName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
