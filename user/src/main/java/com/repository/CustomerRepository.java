package com.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepositoryImplementation<Customer, Integer> {
	Customer findByUid(int uid);
}
