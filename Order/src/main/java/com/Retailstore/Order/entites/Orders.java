package com.Retailstore.Order.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int orderId;
	@OneToMany(cascade = CascadeType.ALL,fetch= FetchType.LAZY)
	@JoinColumn(name="orderId",referencedColumnName = "orderId")
	List<LineItems> items=new ArrayList<>();
	
	
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Orders(List<LineItems> items) {
		super();
		this.items = items;
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
