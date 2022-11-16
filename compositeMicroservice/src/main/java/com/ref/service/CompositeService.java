package com.ref.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ref.CustomerCart;
import com.ref.CustomerOrder;
import com.ref.repository.CartCustomer;
import com.ref.repository.CustomerOrderRepository;

@Service
public class CompositeService {
	@Autowired
	CartCustomer crepo;
	
	@Autowired
	CustomerOrderRepository corepo;

	public CustomerCart create(CustomerCart cc) {
		// TODO Auto-generated method stub
		return crepo.save(cc);
	}

	public CustomerOrder createorder(CustomerOrder co) {
		// TODO Auto-generated method stub
		return corepo.save(co);
	}

	public List<CustomerOrder> findorders(int customerId) {
		// TODO Auto-generated method stub
		return corepo.findBycustomerId(customerId);
		
	}
	
}
