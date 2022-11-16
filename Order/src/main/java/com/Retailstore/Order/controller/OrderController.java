package com.Retailstore.Order.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.Retailstore.Order.entites.LineItems;
import com.Retailstore.Order.entites.Orders;
import com.Retailstore.Order.service.OrderService;
import com.Retailstore.Order.vo.Cart;



@RestController
@RequestMapping("/")
@RefreshScope
public class OrderController {
	
	@Autowired
	OrderService oservice;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${message: Hello Default from order class}")
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
	
	
	@PostMapping("/addorder/cartId={cartId}")
	public ResponseEntity<?> createOrder(@PathVariable ("cartId") int cartId){
		System.out.println("order creating");
		try {
			Cart c = restTemplate.getForObject("http://localhost:8085/getitems/"+cartId,Cart.class);
			System.out.println(c.getItems());		
			List<LineItems> items=new ArrayList<LineItems>();
			for (LineItems i : c.getItems()) {
				items.add(new LineItems(i.getProductId(),i.getProductName(),i.getQuantity(),i.getPrice()));
			}
			System.out.println(items);
			Orders o=new Orders(items);
			//items.setProductName(c.getItems();)
			
		   System.out.println("order  "+o);
			Orders o1= oservice.createOrder(o);
			//restTemplate.delete("http://localhost:8085/deletecart/"+cartId);
			return new ResponseEntity<Orders>(o1,HttpStatus.CREATED);
		}catch(HttpClientErrorException e) {
			return new ResponseEntity<String>("cart not found",HttpStatus.CREATED); 
		}
		
		
		
	}
	
	@GetMapping("/getorder/{orderId}")
	public ResponseEntity<?> getOrder(@PathVariable ("orderId") int orderId){
		Orders o=oservice.getorder(orderId);
		if(o!=null)
			return new ResponseEntity<Orders>(o,HttpStatus.OK);
		return new ResponseEntity<String>("order not found",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/updateorder/{orderId}")
	public ResponseEntity<?> updateorder(@PathVariable("orderId") int orderId, @RequestBody Orders order ){
		System.out.println(order);
		String s=oservice.updateorder(orderId,order);
		return new ResponseEntity<String>(s,HttpStatus.OK);
		
	}
	@DeleteMapping("/deleteorder/{orderId}")
	public ResponseEntity<?> deletecart (@PathVariable("orderId") int orderId ){
		String s=oservice.deleteorder(orderId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
}
