package com.Retailstore.Order.vo;

import java.util.ArrayList;
import java.util.List;

import com.Retailstore.Order.entites.LineItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	int cartId;
	List<LineItems> items = new ArrayList<LineItems>();
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public List<LineItems> getItems() {
		return items;
	}
	public void setItems(List<LineItems> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", items=" + items + "]";
	}
	
	
	
}
