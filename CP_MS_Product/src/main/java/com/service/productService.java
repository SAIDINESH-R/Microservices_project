package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Product;
import com.repository.productRepository;

@Service
public class productService  {
	@Autowired
	productRepository prepo;

	public Product saveProduct(Product p) {
		return prepo.save(p);
	}
	
	public Product getProduct(int pid) {
		return prepo.findByPid(pid);
	}

	public List<Product> getAllProducts(){
		return prepo.findAll();
	}
	
	public void deleteProduct(int pid) {
		Product p=prepo.findByPid(pid);
		prepo.delete(p);
	}
	
	public Product updateProduct(Product p, int pid) {
		Product pro=prepo.findByPid(pid);
		pro.setPname(p.getPname());
		pro.setDescription(p.getDescription());
		pro.setPrice(p.getPrice());
		System.out.println("Updated details are!!" +pro);
		return prepo.save(pro);
	}
}
