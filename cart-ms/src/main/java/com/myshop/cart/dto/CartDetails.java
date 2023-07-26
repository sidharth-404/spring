package com.myshop.cart.dto;

import java.util.List;

import com.myshop.cart.model.CartItem;

import lombok.Data;

@Data
public class CartDetails {

	private List<CartItem> itemsList;
	private float totalCartValue;


}
