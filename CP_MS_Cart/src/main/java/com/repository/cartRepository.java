package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.model.Cart;

@Repository
public interface cartRepository extends JpaRepository<Cart,Integer> {
	Cart findBycartId(int cartId);
	
	@Query("from Cart where uid=?1")
	Cart findcart(int uid);
}
