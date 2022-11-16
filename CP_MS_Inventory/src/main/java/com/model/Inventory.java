package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inventory {
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	int iid;
	int pid;
	int quantity;
	
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Inventory(int iid, int pid, int quantity) {
		super();
		this.iid = iid;
		this.pid = pid;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Inventory [iid=" + iid + ", pid=" + pid + ", quantity=" + quantity + "]";
	}
	
	
	
}
