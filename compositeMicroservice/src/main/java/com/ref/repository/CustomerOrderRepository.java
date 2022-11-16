package com.ref.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ref.CustomerOrder;


public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
//	@Query("select * from CustomerOrder where customerId='")
	List<CustomerOrder> findBycustomerId(int customerId);
}
