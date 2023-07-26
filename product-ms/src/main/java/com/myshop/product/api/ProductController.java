package com.myshop.product.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.product.model.Product;
import com.myshop.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@GetMapping
	public List<Product> getAll()
	{
		return productService.getAvailableProducts();
	}
	
	@PostMapping
	public Product addProduct(@RequestBody Product product)
	{
		return productService.addProduct(product);
	}
	@GetMapping("/{id}")
	public Product getProductDetails(@PathVariable String id)
	{
		return productService.getProductDetails(id);
	}
 @DeleteMapping("/{id}")
 public void removeProduct(@PathVariable String id)
 {
	 productService.removeProduct(id);
 }
}
