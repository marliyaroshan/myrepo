package com.springboot.example.springbootdemoproject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.example.springbootdemoproject.Exception.ProductNotfoundException;
import com.springboot.example.springbootdemoproject.model.Product;

@RestController
public class ProductService {
	private static Map<Integer, Product> productRepo = new HashMap<>();
	static {
		Product firstProduct = new Product(1, "honey");
		Product secondProduct = new Product(2, "chocolates");
		productRepo.put(firstProduct.getProductId(), firstProduct);
		productRepo.put(secondProduct.getProductId(), secondProduct);
	}

	// function to get the product list
	@GetMapping(value = "/productlist")
	public ResponseEntity<Collection<Product>> getProducts() {
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}

	// function to add new product to the existing object
	@RequestMapping(value = "/createproduct", method = RequestMethod.POST)
	public ResponseEntity<Object> addProduct(@RequestBody Product product) {
		productRepo.put(product.getProductId(), product);
		return new ResponseEntity<>("Product successfully added", HttpStatus.CREATED);
	}

	// function to update the existing product name to the existing object
	@RequestMapping(value = "/updateproduct", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@RequestParam(value = "productid") int productId,
			@RequestParam(value = "productname") String productName) {
		if (!productRepo.containsKey(productId))
			throw new ProductNotfoundException(productId + " doesn't exists");
		Product product = new Product(productId, productName);
		productRepo.put(productId, product);
		return new ResponseEntity<>("Product successfully update", HttpStatus.OK);
	}

	// function to delete a product from the list
	@RequestMapping(value = "/deleteproduct", method = RequestMethod.PUT)
	public ResponseEntity<Object> deleteProduct(@RequestParam(value = "productid") int productId) {
		if (!productRepo.containsKey(productId))
			throw new ProductNotfoundException(productId + " cannot be deleted as it doesn't exists");
		productRepo.remove(productId);
		return new ResponseEntity<>("Product successfully deleted", HttpStatus.OK);
	}
}
