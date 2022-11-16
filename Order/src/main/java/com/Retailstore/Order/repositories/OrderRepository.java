package com.Retailstore.Order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Retailstore.Order.entites.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{

	Orders findByorderId(int orderId);

}
