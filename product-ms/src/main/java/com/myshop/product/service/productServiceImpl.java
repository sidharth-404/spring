package com.myshop.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.product.model.Product;
import com.myshop.product.repository.ProductRepository;

@Service
public class productServiceImpl implements ProductService {
	@Autowired
    ProductRepository repo;

	@Override
	public List<Product> getAvailableProducts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Product getProductDetails(String productId) {
		// TODO Auto-generated method stub
		return repo.findById(productId).get();

	}

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		repo.save(product);
        return product;
	}

	@Override
	public void removeProduct(String productId) {
		// TODO Auto-generated method stub
		   repo.deleteById(productId);
		
	}

}
