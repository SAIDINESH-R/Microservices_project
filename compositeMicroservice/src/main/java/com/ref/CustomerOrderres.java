package com.ref;

import java.util.List;

import com.ref.customer.Customer;
import com.ref.order.Orders;

public class CustomerOrderres {
	Customer customer;
	List<Orders> ods;
	public CustomerOrderres() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Orders> getOds() {
		return ods;
	}
	public void setOds(List<Orders> ods) {
		this.ods = ods;
	}
	
	
}
