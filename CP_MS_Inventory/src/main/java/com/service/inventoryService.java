package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Inventory;
import com.repository.inventoryRepository;

@Service
public class inventoryService {
	@Autowired
	inventoryRepository irepo;

	public Inventory saveInventory(Inventory p) {
		return irepo.save(p);
	}
	
	public Inventory getInventory(int iid) {
		return irepo.findByiid(iid);
	}
	
	public Inventory getInventoryByPid(int pid) {
		return irepo.findBypid(pid);
	}

	public List<Inventory> getAllInventories(){
		return irepo.findAll();
	}
	
	public void deleteInventory(int iid) {
		Inventory p=irepo.findByiid(iid);
		irepo.delete(p);
	}
	
	public Inventory updateInventory(Inventory i, int iid) {
		Inventory inv=irepo.findByiid(iid);
		inv.setPid(i.getPid());
		inv.setQuantity(i.getQuantity());
		System.out.println("Updated details are!!" +inv);
		return irepo.save(inv);
	}
}
