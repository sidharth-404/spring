package com.myshop.cart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CartItem {
	@Id
	@GeneratedValue
	private int cartId;
	private String productId;
	private int quantity;
	private float totalPrice;

}
