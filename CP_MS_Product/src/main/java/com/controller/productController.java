package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exception.ProductNotfoundException;
import com.model.Product;
import com.repository.productRepository;
import com.service.productService;



@RestController
@RequestMapping("/")
@RefreshScope
public class productController {
	
	@Autowired
	productService pservice;
	
	@Autowired
	productRepository prepo;
	
	@Value("${message: Hello Default from product class}")
	private String message;
	
	@Value("${user.role: Default Role}")
	private String role;

	
	@Value("${server.port}")
	String serverPort;
	
	@GetMapping("/welcomegreet")
	public String getWelcome() {
		return "Welcome Greeting! from Port Number "+serverPort;
	}
	
	@GetMapping("/message")
	public String getMessage() {
		return this.message;
	}
	
	@RequestMapping(
			value = "/myrole/{username}", 
			method = RequestMethod.GET
			)
	public String myrole(@PathVariable("username") String userName) {
		return "Hello you are  a " + userName + " your Role is  " + role;
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@Valid @RequestBody Product p) {
		System.out.println(p);
		pservice.saveProduct(p);
		return new ResponseEntity<Product> (p, HttpStatus.CREATED);
	}
	
	@GetMapping("/getProduct/{pid}")
	public ResponseEntity<Product> getProduct(@PathVariable("pid") int pid){
		if(!prepo.existsById(pid))throw new ProductNotfoundException();
		Product pro=pservice.getProduct(pid);
		return new ResponseEntity<Product>(pro,HttpStatus.FOUND);
	}
	
	@GetMapping("/productslist")
	public List<Product> getProducts() {
		return pservice.getAllProducts();
	}
	
	@DeleteMapping("/delete/{pid}")
	public ResponseEntity<?> deleteProduct(@PathVariable("pid") int pid) {
		if(!prepo.existsById(pid))throw new ProductNotfoundException();
		pservice.deleteProduct(pid);
		return new ResponseEntity<String>("Details deleted Successfully!!! ",HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{pid}")
	public ResponseEntity<?> updateProduct(@PathVariable("pid") int pid, @RequestBody Product a) {
		if(!prepo.existsById(pid))throw new ProductNotfoundException();
		pservice.updateProduct(a,pid);
		return new ResponseEntity<String>("Details Updated Successfully!!! ",HttpStatus.OK);
	}
	
}
