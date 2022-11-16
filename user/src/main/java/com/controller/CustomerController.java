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

import com.model.Customer;
import com.exception.CustomerNotfoundException;
import com.repository.CustomerRepository;
import com.service.CustomerService;

@RestController
@RequestMapping("/")
@RefreshScope
public class CustomerController {

	@Autowired
	CustomerRepository urepo;
	
	@Autowired
	CustomerService uservice;
	
	@Value("${server.port}")
	String serverPort;
	
	@Value("${message: Hello Default from user class}")
	private String message;
	
	@GetMapping("/welcomegreet")
	public String getWelcome() {
		return "Welcome Greeting! from Port Number "+serverPort;
	}
	
	@GetMapping("/message")
	public String getMessage() {
		return this.message;
	}

	
	@GetMapping("/getuser/{uid}")
	public ResponseEntity<Customer> getUser(@PathVariable int uid) {
		if(!urepo.existsById(uid))throw new CustomerNotfoundException();
		Customer u = uservice.getUser(uid);
		return new ResponseEntity<Customer>(u, HttpStatus.FOUND);
	}

	
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody Customer u) {
		System.out.println(u);
		uservice.saveUser(u);
		return new ResponseEntity<Customer>(u, HttpStatus.CREATED);
	}
	
	@GetMapping("/userslist")
	public List<Customer> getUsers() {
		return uservice.getAllUser();
	}
	
	@PutMapping("/update/{uid}")
	public ResponseEntity<?> updateUser(@PathVariable("uid") int uid, @RequestBody Customer c) {
		if(!urepo.existsById(uid))throw new CustomerNotfoundException();
		uservice.updateUser(c, uid);
		return new ResponseEntity<String>("Details Updated Successfully!!! ", HttpStatus.OK);
	}

	@DeleteMapping("/delete/{uid}")
	public ResponseEntity<?> deleteUser(@PathVariable("uid") int uid) {
		if(!urepo.existsById(uid))throw new CustomerNotfoundException();
		uservice.deleteUser(uid);
		return new ResponseEntity<String>("Details deleted Successfully!!! ", HttpStatus.OK);
	}
	
	
}
