package com.ref.order;

import java.util.ArrayList;
import java.util.List;

import com.ref.customer.LineItems;



public class Orders {
	int orderId;
	List<LineItems> items=new ArrayList<>();
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<LineItems> getItems() {
		return items;
	}
	public void setItems(List<LineItems> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", items=" + items + "]";
	}
	
}
