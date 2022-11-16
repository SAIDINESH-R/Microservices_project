package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Customer;
import com.repository.CustomerRepository;


@Service
public class CustomerService {
	@Autowired
	CustomerRepository urepo;
	
	public Customer saveUser (Customer u) {
		return urepo.save(u);
	}
	
	public List<Customer> getAllUser(){
		return urepo.findAll();
	}
	
	public Customer getUser(int uid) {
		return urepo.findByUid(uid);
	}
	
	public void deleteUser(int uid) {
		Customer u= urepo.findByUid(uid);
		urepo.delete(u);
	}
	
	public Customer updateUser(Customer u, int uid) {
		Customer ur =urepo.findByUid(uid);
		ur.setUname(u.getUname());
		ur.setUmail(u.getUmail());
		ur.setBa(u.getBa());
		ur.setSa(u.getSa());
		System.out.println("Updated details are!!" + ur);
		return urepo.save(ur);
	}
}
