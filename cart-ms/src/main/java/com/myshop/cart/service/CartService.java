package com.myshop.cart.service;

import java.util.List;

import com.myshop.cart.model.CartItem;

public interface CartService {
	public List<CartItem> addToCart(String productId);
	public List<CartItem> removeFromCart(String productId);
	public List<CartItem> updateQuantity(String productId,int quantity);
	public float calculateTotalCost();
	 public List<CartItem> getAllItems();
	

}
