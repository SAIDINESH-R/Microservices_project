package com.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Cart;
import com.repository.cartRepository;
import com.repository.lineItemRepository;

@Service
public class cartService {

	@Autowired
	private cartRepository crepo;
	
	@Autowired lineItemRepository lineItemsRepo;

	public Cart createcart(Cart cart) {
		// TODO Auto-generated method stub
		return crepo.save(cart);
		
	}

	public Cart getitem(int cartId) {
		// TODO Auto-generated method stub
		Cart c1=crepo.findBycartId(cartId);
		if(c1==null) {
			return null;
		}else {
			return c1;
		}	
	}
	
	public Cart getCart(int uid) {
		Cart c=crepo.findcart(uid);
		return c;
	}

	public String updatecart(int cartId, Cart cart) {
		// TODO Auto-generated method stub
		System.out.println(cart);
		Cart c1 = crepo.findBycartId(cartId);
		if(c1==null) {
			return "Cart doesnt exists";
		}
		c1.setItems(cart.getItems());
		crepo.save(c1);
		return "updated successfully";
	}

	public String deletecart(int cartId) {
		// TODO Auto-generated method stub
		Cart c1 = crepo.findBycartId(cartId);
		if(c1==null) {
			return "Cart doesnt exists";
		}else {
			crepo.delete(c1);
			return "deleted successfully";
		}
	}
}
