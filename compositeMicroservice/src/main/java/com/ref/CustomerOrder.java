package com.ref;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Entity
public class CustomerOrder {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	int sno;
	int orderId;
	int customerId;
	
	public CustomerOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "CustomerOrder [sno=" + sno + ", orderId=" + orderId + ", customerId=" + customerId + "]";
	}
	
	
}
