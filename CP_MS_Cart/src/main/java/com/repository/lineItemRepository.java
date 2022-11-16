package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.LineItem;

@Repository
public interface lineItemRepository extends JpaRepository<LineItem,Long>{

}
