package com.ref;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerCart {
	@Id
	int customerId;
	int cartId;
	public CustomerCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	
	
}
