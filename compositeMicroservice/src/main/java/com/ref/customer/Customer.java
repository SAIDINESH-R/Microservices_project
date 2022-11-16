package com.ref.customer;

import java.util.ArrayList;
import java.util.List;


public class Customer {
	private int uid;
	private String uname;
	private String umail;
	
	List<bAddress> ba = new ArrayList<bAddress>();

	List<sAddress> sa= new ArrayList<sAddress>();

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUmail() {
		return umail;
	}

	public void setUmail(String umail) {
		this.umail = umail;
	}

	public List<bAddress> getBa() {
		return ba;
	}

	public void setBa(List<bAddress> ba) {
		this.ba = ba;
	}

	public List<sAddress> getSa() {
		return sa;
	}

	public void setSa(List<sAddress> sa) {
		this.sa = sa;
	}

	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
