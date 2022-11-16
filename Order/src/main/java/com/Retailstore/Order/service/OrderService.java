package com.Retailstore.Order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Retailstore.Order.entites.LineItems;
import com.Retailstore.Order.entites.Orders;
import com.Retailstore.Order.repositories.OrderRepository;




@Service
public class OrderService {

	@Autowired
	OrderRepository orepo;

	public Orders getorder(int orderId) {
		// TODO Auto-generated method stub
		Orders o=orepo.findByorderId(orderId);
		if(o==null) {
			return null;
		}else {
			return o;
		}
	}

	public String updateorder(int orderId, Orders order) {
		// TODO Auto-generated method stub
		Orders o = orepo.findByorderId(orderId);
		if(o==null) {
			return "order doesnt exists";
		}
		o.setItems(order.getItems());
		orepo.save(o);
		return "order updated successfully";
	}

	public String deleteorder(int orderId) {
		// TODO Auto-generated method stub
		Orders o = orepo.findByorderId(orderId);
		if(o==null) {
			return "order doesnt exists";
		}else {
			orepo.delete(o);
			return "order deleted successfully";
		}
	}

	public Orders createOrder(Orders o) {
		// TODO Auto-generated method stub
		System.out.println("seervice order"+o);
		return orepo.save(o);
	}
}
