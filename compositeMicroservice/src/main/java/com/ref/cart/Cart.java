package com.ref.cart;

import java.util.ArrayList;
import java.util.List;



public class Cart {
	int cartId;
	List<LineItem> items = new ArrayList<LineItem>();
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public List<LineItem> getItems() {
		return items;
	}
	public void setItems(List<LineItem> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", items=" + items + "]";
	}
	
	
}
