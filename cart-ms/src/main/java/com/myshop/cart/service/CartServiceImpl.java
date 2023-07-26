package com.myshop.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myshop.cart.dto.Product;
import com.myshop.cart.model.CartItem;
import com.myshop.cart.repository.CartItemRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CartServiceImpl implements CartService { 
	@Autowired
	private CartItemRepository repo;
	@Autowired
	RestTemplate productServiceClient;


	@Override
	public List<CartItem> addToCart(String productId) {
		// TODO Auto-generated method stub
Optional<CartItem> checkItem = repo.findByProductId(productId);
		
		if(!checkItem.isPresent()) {
			CartItem item = new CartItem();
			item.setProductId(productId);
			item.setQuantity(1);
			item.setTotalPrice(fetchPrice(productId));
			repo.save(item);
		}
		else {
			CartItem item = checkItem.get();
			updateQuantity(productId, item.getQuantity()+1);
		}
		
		return getAllItems();

	}

	@Override
	public List<CartItem> removeFromCart(String productId) {
		// TODO Auto-generated method stub
		Optional<CartItem> checkItem = repo.findByProductId(productId);
		if(checkItem.isPresent()) {
			CartItem item = checkItem.get();
			repo.delete(item);
		}
		
		
		return getAllItems();

	}

	@Override
	public List<CartItem> updateQuantity(String productId, int quantity) {
		// TODO Auto-generated method stub
Optional<CartItem> checkItem = repo.findByProductId(productId);
		
		if(checkItem.isPresent()) {
			CartItem item = checkItem.get();
			item.setQuantity(quantity);
			item.setTotalPrice(item.getTotalPrice()*quantity);
			repo.save(item);
		}
		
		return getAllItems();

	}
	

	@Override
	public float calculateTotalCost() {
		// TODO Auto-generated method stub
		return repo.getTotalCartValue();
	}
	public List<CartItem> getAllItems()
	{
		return repo.findAll();
	}
@CircuitBreaker(fallbackMethod = "fetchPriceFallback", name="cb-product")
private float fetchPrice(String productId) {
		
		Product product = productServiceClient.getForObject("http://product-ms/product/"+productId, Product.class);
		if(product != null)
			 return product.getPrice();
		
		return 0;
	}
private float fetchPriceFallback(String productId, Throwable t) {
    Product product=new Product(productId,"Dummy Product",0,"A Dummy Product");
    System.out.println(t.getMessage());
    System.out.println("Response from fallback");
    System.out.println(product);
    return product.getPrice();
}


}
