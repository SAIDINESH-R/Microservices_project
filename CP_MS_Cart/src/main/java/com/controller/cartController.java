package com.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.exception.cartNotFoundException;
import com.model.Cart;
import com.repository.cartRepository;
import com.service.cartService;

@RestController
@RequestMapping("/")
@RefreshScope
public class cartController {
	@Autowired
	private cartService cservice;
	
	@Autowired
	private cartRepository crepo;
	
	@Value("${message: Hello Default from cart class}")
	private String message;
	
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

	@PostMapping("/addcart")
	public ResponseEntity<Cart> createcart(@RequestBody Cart cart){
		cservice.createcart(cart);
		return new ResponseEntity<Cart>(cart,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getitems/{cartId}")
	public ResponseEntity<?> getcart(@PathVariable ("cartId") int cartId){
		if(!crepo.existsById(cartId))throw new cartNotFoundException();
		Cart c=cservice.getitem(cartId);
		if(c!=null)
			return new ResponseEntity<Cart>(c,HttpStatus.OK);
		return new ResponseEntity<String>("Sorry!",HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/updatecart/{cartId}")
	public ResponseEntity<?> updatecart(@PathVariable("cartId") int cartId, @RequestBody Cart cart ){
		if(!crepo.existsById(cartId))throw new cartNotFoundException();
		System.out.println(cart);
		String s=cservice.updatecart(cartId,cart);
		return new ResponseEntity<String>(s,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deletecart/{cartId}")
	public ResponseEntity<?> deletecart (@PathVariable("cartId") int cartId ){
		if(!crepo.existsById(cartId))throw new cartNotFoundException();
		String s=cservice.deletecart(cartId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	@GetMapping("/getCart/{uid}")
    public ResponseEntity<Cart> getAllOrders(@PathVariable int uid) {
        Cart c = cservice.getCart(uid);
        return new ResponseEntity<Cart>(c,HttpStatus.OK);
    }
	
}
