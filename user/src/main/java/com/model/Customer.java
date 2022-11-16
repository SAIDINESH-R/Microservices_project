package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity
public class Customer {
	@Id
	private int uid;
	private String uname;
	private String umail;
	
	@OneToMany(targetEntity=bAddress.class,cascade=CascadeType.ALL)
	@JoinColumn(name="cust_ID",referencedColumnName="uid")
	List<bAddress> ba = new ArrayList<bAddress>();
	
	@OneToMany(targetEntity=sAddress.class,cascade=CascadeType.ALL)
	@JoinColumn(name="cust_ID",referencedColumnName="uid")
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

	
	
}
