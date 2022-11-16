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
import org.springframework.web.bind.annotation.RestController;

import com.exception.InventoryNotFoundException;
import com.model.Inventory;
import com.repository.inventoryRepository;
import com.service.inventoryService;

@RestController
@RequestMapping("/")
@RefreshScope
public class inventoryController {
	@Autowired
	inventoryService iservice;
	
	@Autowired
	inventoryRepository irepo;
	
	@Value("${message: Hello Default from Inventory class}")
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
	
	@PostMapping("/create")
	public ResponseEntity<?> createInventory(@Valid @RequestBody Inventory i) {
		System.out.println(i);
		iservice.saveInventory(i);
		return new ResponseEntity<Inventory> (i, HttpStatus.CREATED);
	}
	
	@GetMapping("/getInventory/{iid}")
	public ResponseEntity<Inventory> getInventory(@PathVariable("iid") int iid){
		if(!irepo.existsById(iid))throw new InventoryNotFoundException();
		Inventory inv=iservice.getInventory(iid);
		return new ResponseEntity<Inventory>(inv,HttpStatus.FOUND);
	}
	
	
	@GetMapping("/getinventorybyproduct/{pid}")
	public ResponseEntity<Inventory> getInventorybyPid(@PathVariable("pid") int pid){
		//if(!irepo.existsById(iid))throw new InventoryNotFoundException();
		Inventory inv=iservice.getInventoryByPid(pid);
		return new ResponseEntity<Inventory>(inv,HttpStatus.FOUND);
	}
	
	@GetMapping("/inventorieslist")
	public List<Inventory> getInventories() {
		return iservice.getAllInventories();
	}
	
	@DeleteMapping("/delete/{iid}")
	public ResponseEntity<?> deleteInventory(@PathVariable("iid") int iid) {
		if(!irepo.existsById(iid))throw new InventoryNotFoundException();
		iservice.deleteInventory(iid);
		return new ResponseEntity<String>("Details deleted Successfully!!! ",HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{iid}")
	public ResponseEntity<?> updateInventory(@PathVariable("iid") int iid, @RequestBody Inventory i) {
		if(!irepo.existsById(iid))throw new InventoryNotFoundException();
		iservice.updateInventory(i,iid);
		return new ResponseEntity<String>("Details Updated Successfully!!! ",HttpStatus.OK);
	}
	
}
