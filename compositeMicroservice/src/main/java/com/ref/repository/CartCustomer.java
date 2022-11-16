package com.ref.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ref.CustomerCart;

public interface CartCustomer extends JpaRepository<CustomerCart, Integer>{

	CustomerCart findBycustomerId(int customerId);

}
