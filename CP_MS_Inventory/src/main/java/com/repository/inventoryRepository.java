package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Inventory;



public interface inventoryRepository extends JpaRepository<Inventory , Integer>{
	Inventory findByiid(int iid);
	
	Inventory findBypid (int pid);

}
